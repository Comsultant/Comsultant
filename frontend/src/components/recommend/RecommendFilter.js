import React, { useEffect, useCallback, useRef } from "react";
import { useState } from "react";
import { Input, InputNumber, Slider } from "antd";
import style from "@/styles/RecommendFilter.module.scss";
import classNames from "classnames";
import "@/styles/RecommendFilter.scss"
import PriceFormatter from "@/tools/PriceFormatter";
import { debounce, set } from "lodash";
const RecommendFilter = ({filterItem, setFilterItem}) => {
  const defaultMaxPrice = 5000000;
  
  const [purpose, setPurpose] = useState("게임용");
  const [program, setProgram] = useState("기본");
  const [minPrice, setMinPrice] = useState(0);
  const [maxPrice, setMaxPrice] = useState(defaultMaxPrice);



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
    setMinPrice(e[0]);
    setMaxPrice(e[1]);
  }

  useEffect(() => {
    const timer = setTimeout(() => {
      setFilterItem({...filterItem,  lowPrice: minPrice, highPrice: maxPrice})
    }, 100);

    return () => {
      clearTimeout(timer);
    }
  }, [minPrice, maxPrice])

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

  const onChangeMinPrice = (v) => {
    setMinPrice(v);
  }
  const onChangeMaxPrice = (v) => {
    setMaxPrice(v);
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
        <br/>
        <span className={style["recommend-filter-title"]}>가격</span>
        <div className={style["second-box"]}>
          <div className={style["price-box"]}>
            <InputNumber
              className={style['input-min-price']}
              min={0}
              max={maxPrice}
              value={minPrice}
              step={10000}
              onChange={onChangeMinPrice}
              formatter={value => `${value}원`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
            />


            <Slider
              onChange={onPriceChange}
              range={{ draggableTrack: true }}
              defaultValue={[0, defaultMaxPrice]}
              value={[minPrice, maxPrice]}
              min={0}
              max={10000000}
              step={10000}
              className={style.slider}
              tooltip={{
                open: true,
                formatter: PriceFormatter,
              }}
              trackStyle={{"backgroundColor": "#377BB9", "height" : "8px"}}
              handleStyle={{"borderColor": "black", "width" : "25px", "height": "16px", "borderRadius" : "5px"}}
            />

            <InputNumber
              className={style['input-max-price']}
              min={minPrice}
              max={10000000}
              value={maxPrice}
              step={10000}
              onChange={onChangeMaxPrice}
              formatter={value => `${value}원`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
            />

          </div>
        </div>
      </div>
    </>
  );
};

export default RecommendFilter;
