import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import BuilderProductSelector from "./BuilderProductSelector";
import style from "@/styles/RecommendDetail.module.scss";
import { captureBuilderRequest, captureBuilderForUserRequest, saveRecommendBuilder } from "@/services/recommendService";
import { Modal, Input } from 'antd';
import html2canvas from "html2canvas";
const RecommendDetail = () => {
  const builder = useSelector((state) => state.builder);
  const isLogin = useSelector((state) => state.account.isLogin);

  const products = [[],[],[],[],[],[],[],[],[]]

  // 키 값이 인덱스인거
  // builder.prodDetail.map((item, idx) => {
  //   Object.keys(item).map((prod) => {
  //     products[idx].push({[prod.split("_")[0]] : item[prod]})
  //   })
  // })

  // jSON 형식 맞춘거
  builder.prodDetail.map((item, idx) => {
    Object.keys(item).map((prod) => {
      products[idx].push({id: prod.split("_")[0], count : item[prod], price: 0, name: prod.split("_")[1]})
    })
  })

  const [filterItem, setFilterItem] = useState({});
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [builderName, setBuilderName] = useState("");

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    setBuilderName("");
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };

  const makeRequestBody = () => {
    let builderProducts = [];
    Object.keys(filterItem.prods).map((key) => {
      builderProducts.push({productIdx: key, cnt: filterItem.prods[key]})
    })

    const reqBody = {
      builderProducts: builderProducts,
      use: builder.use,
      program: builder.program == "" ? "default" : builder.program
    }
    
    return reqBody;
  }

  // 회원용 저장하기
  const saveBuilder = async () => {
    const body = makeRequestBody()
    if(builderName.trim().length == 0) {
      alert("이름을 입력해주세요");
      return;
    }

    body.name = builderName.trim()

    // 요청보낸다
    const result = await saveRecommendBuilder(body);
    if(result?.data?.message === "success") {
      alert('저장 성공')
    } else {
      alert('저장 실패')
    }

    // 보낸 후에 builderName 초기화시키고 모달 닫는다.
    handleOk();
  }

  const imageSave = (url, name) => {
    let link = document.createElement('a');
    link.href = url;
    link.download = name;
    link.click();
  }

  const captureBuilder = () => {
    const body = makeRequestBody()

    // No Wait
    captureBuilderRequest(body);
    html2canvas(document.getElementById('builder-info')).then(
      canvas => {
        imageSave(canvas.toDataURL('image/png'), "builder.png");
      })

  }

  // 회원용 캡처
  const captureBuilderforUser = async () => {
    const body = makeRequestBody()

    captureBuilderForUserRequest(body);
    html2canvas(document.getElementById('builder-info')).then(
      canvas => {
        imageSave(canvas.toDataURL('image/png'), "builder.png");
      })
  }

  const onChangeBuilderName = (e) => {
    setBuilderName(e.target.value)
  }



  return (
    <div className={style["detail-main"]}>
      <Modal title="견적 저장하기" 
        open={isModalOpen} 
        onOk={saveBuilder} 
        onCancel={handleCancel}
        okText="저장하기"
        cancelText="취소하기"
        style={{
          top: "30%"
        }}
      >
        <Input
        addonBefore="견적 이름"
        placeholder="견적 이름을 입력해 주세요"
        allowClear
        onChange={onChangeBuilderName}
        className={style["builder-name-input"]}
        />
      </Modal>
      <div className={style["detail-top-div"]} id="builder-info">
        <BuilderProductSelector
          filterItem={filterItem}
          setFilterItem={setFilterItem}
          builder={builder}
          products={products}
        />
      </div>
      <div className={style["detail-button-div"]}>
        {/* 
          비회원일 때는 비회원 캡처만 필요! 이건 간단

          회원일때는 2개가 나옴. 캡처와 저장
          캡쳐는 이해했음
          저장은 견적 이름하고 같이 보내주면 되는걸로 이해.

          비회원 캡처와 회원 캡처는 다른거임 같은거임? 
        
        */}

        {isLogin ? 
          <div>
            <button onClick={showModal} >
              견적 저장하기
            </button>
            <button onClick={captureBuilderforUser} >
              이미지로 저장하기
            </button>
          </div> : 
          
          <div>
            <button onClick={captureBuilder} >
              이미지로 저장하기
            </button>
          </div>
        }
      </div>
    </div>
  );
};

export default RecommendDetail;
