import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import BuilderProductSelector from "./BuilderProductSelector";
import style from "@/styles/RecommendDetail.module.scss";
import { Modal, Input } from 'antd';
const RecommendDetail = () => {
  const builder = useSelector((state) => state.builder);
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
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };

  const makeRequestBody = () => {
    // console.log(filterItem)
    // console.log(builder)
    let builderProducts = [];
    Object.keys(filterItem.prods).map((key) => {
      builderProducts.push({productIdx: key, cnt: filterItem.prods[key]})
    })

    const reqBody = {
      builderProducts: builderProducts,
      use: "work",
      program: "default"
    }
    
    return reqBody;
  }

  const saveBuilder = async () => {
    const body = makeRequestBody()
    if(builderName.trim().length == 0) {
      alert("이름을 입력해주세요");
      return;
    }
    
    console.log(builderName.trim())
    console.log(body)

    // 요청보낸다

    // 보낸 후에 builderName 초기화시키고 모달 닫는다.
  }

  const captureBuilder = async () => {
    const body = makeRequestBody()
    // 요청보낸다
  }

  const onChangeBuilderName = (e) => {
    setBuilderName(e.target.value)
  }



  return (
    <div className={style["detail-main"]}>
      <Modal title="Basic Modal" open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
        <Input
        addonBefore="견적 이름"
        placeholder="견적 이름을 입력해 주세요"
        allowClear
        onChange={onChangeBuilderName}
        style={{ width: 304 }}
        />
        <button onClick={saveBuilder} >
          저장하기
        </button>
      </Modal>
      <div className={style["detail-top-div"]}>
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
        <button onClick={showModal} >
          회원용 저장하기
        </button>
        <button onClick={captureBuilder} >
          회원용 캡쳐하기
        </button>
      </div>
    </div>
  );
};

export default RecommendDetail;
