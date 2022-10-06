import React, { useEffect } from "react";
import { useState } from "react";
import { Slider, Checkbox, Modal, Input } from "antd";
import style from "@/styles/RecommendFilter.module.scss";
import { useDispatch, useSelector } from "react-redux";
import classNames from "classnames";
import "@/styles/RecommendFilter.scss"
import { saveRecommendBuilder } from "@/services/recommendService";
import PriceFormatter from "@/tools/PriceFormatter";

const RecommendFilter = ({filterItem, setFilterItem}) => {
  const defaultMaxPrice = 5000000;
  const isLogin = useSelector((state) => state.account.isLogin);
  
  const [purpose, setPurpose] = useState("게임용");
  const [program, setProgram] = useState("기본");
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

  const filterList = [
    {
      purpose : "게임용",
      programList : ["기본", "최하사양", "저사양", "중사양", "고사양", "최상사양"],
      purposeMapping: "game",
      programListMapping: ["", 1, 2, 3, 4, 5]
    },
    {
      purpose: "방송용",
      programList: ["기본"],
      purposeMapping: "broadcast",
      programListMapping: ["default"]
    },
    {
      purpose: "사무용",
      programList: ["기본"],
      purposeMapping: "work",
      programListMapping: ["default"]
    },
    {
      purpose: "디자인 작업용",
      programList: ["기본", "어도비 일러스트", "어도비 포토샵", "어도비 인디자인", "클립 스튜디오"],
      purposeMapping: "design",
      programListMapping: ["", "adobe illustrator", "adobe photoshop", "adobe indesign", "clip studio"]
    },
    {
      purpose: "영상 작업용",
      programList: ["기본", "어도비 프리미어 프로", "모바비", "베가스 프로", "어도비 애프터이펙트"],
      purposeMapping: "video",
      programListMapping: ["default", "adobe premier pro", "movavi", "vegas pro", "adobe aftereffect"]
    },
    {
      purpose: "3D그래픽 작업용",
      programList: ["기본", "Autodesk 3ds Max", "CAD", "Blender","ZBrush","Rhinoceros","Cinema 4D","Autodesk Maya","스케치업","Houdini", "루미온", "V-RAY"],
      purposeMapping: "graphics",
      programListMapping: ["default", "autodesk 3ds Max", "cad", "blender","zBrush","rhinoceros","cinema 4D","autodesk maya","sketchUp",
        "houdini", "lumion", "v-ray"]
    } 
  ];

  // const formatter = (value) => `${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ",")+`원`;

  const onPriceChange = (e) => {
    console.log(e[0])
    setFilterItem({...filterItem,  lowPrice: e[0], highPrice: e[1]})
  }

  const onUseChange = (e) => {
    // console.log("key: ", e.target.value)
    // console.log("value: ", filterList[e.target.selectedIndex].purposeMapping )
    const useValue = filterList[e.target.selectedIndex].purposeMapping
    const defaultProgram = filterList[e.target.selectedIndex].programListMapping[0]
    setFilterItem({
      ...filterItem, use: useValue, program: defaultProgram 
    })
    setPurpose(e.target.value)
    setProgram("기본")
  }
  
  const onProgramChange = (e) => {
    let mappedProgram = "";
    filterList.map((item) => {
      if(item.purpose == purpose) {
        mappedProgram = item.programListMapping[e.target.selectedIndex]
      }
    })
    setFilterItem({
      ...filterItem, program: mappedProgram
    })
    setProgram(e.target.value)
  }

  const makeRequestBody = () => {
    // console.log(filterItem)
    // console.log(builder)
    let builderProducts = [];
    Object.keys(filterItem.prods).map((key) => {
      builderProducts.push({productIdx: key, cnt: filterItem.prods[key]})
    })

    const reqBody = {
      builderProducts: builderProducts,
    }
    
    return reqBody;
  }

  const saveBuilder = async () => {
    if(builderName.trim().length == 0) {
      alert("이름을 입력해주세요");
      return;
    }
    const body = makeRequestBody()

    if(body.builderProducts.length == 0) {
      alert("제품을 추가해 주세요")
      return ;
    }

    body.name = builderName.trim()

    // 요청보낸다
    const result = await saveRecommendBuilder(body);
    console.log(result)
    if(result?.data?.message === "success") {
      alert('저장 성공')
    } else {
      alert('저장 실패')
    }

    // 보낸 후에 builderName 초기화시키고 모달 닫는다.
    handleOk();
  }

  const onChangeBuilderName = (e) => {
    setBuilderName(e.target.value)
  }


  return (
    <>
      <div className={style.filter}>
        <div className={style.purpose}>
          <div>
            <span className={style["recommend-filter-title"]}>용도</span>
            <select onChange={onUseChange} className={style["select-input-blue"]}>
              {filterList.map((filter, idx)=>{
                return(
                  <option key={idx} value={filter.purpose}>
                    {filter.purpose}
                  </option>
                );
              })}
            </select>
          </div>
          <div>
            <span className={style["recommend-filter-title"]}>사용 프로그램</span>
            <select onChange={onProgramChange} value={program} className={classNames(`${style["select-input"]}`,`${style['usage-program']}`)}>
              {filterList.filter((curr)=>curr.purpose === purpose)[0].programList.map((pr, idx)=>{
                return(
                <option key={idx} value={pr} >
                  {pr}
                </option>
                );
              })}
            </select>
          </div>
        </div>
        <div className={style["second-box"]}>
          <div className={style["price-box"]}>
            <span>가격</span>
            <Slider
              range={{ draggableTrack: true }}
              onAfterChange={onPriceChange}
              defaultValue={[0, defaultMaxPrice]}
              max={10000000}
              step={100000}
              className={style.slider}
              tooltip={{
                open: true,
                formatter: PriceFormatter,
              }}
              trackStyle={{"backgroundColor": "#377BB9", "height" : "8px"}}
              handleStyle={{"borderColor": "black", "width" : "25px", "height": "16px", "borderRadius" : "5px"}}
            />
          </div>
        </div>
        {isLogin ?
          <div className={style["builder-box"]}>
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
            <button className={style.button} onClick={showModal}>견적 저장하기</button>
            <select className={style["select-input"]}>
              <option>견적 불러오기</option>
            </select>
          </div> : null
        }

      </div>
    </>
  );
};

export default RecommendFilter;
