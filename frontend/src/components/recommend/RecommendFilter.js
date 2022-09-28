import React from "react";
import { useState } from "react";
import { Slider, Checkbox } from "antd";
import style from "@/styles/RecommendFilter.module.scss";
import classNames from "classnames";

const RecommendFilter = () => {

  const [purpose, setPurpose] = useState("게임용");
  const [program, setProgram] = useState("");
  const filterList = [
    {
      purpose : "게임용",
      programList : ["기본", "최하사양", "저사양", "중사양", "고사양", "최상사양"],
    },
    {
      purpose: "방송용",
      programList: ["기본"]
    },
    {
      purpose: "사무용",
      programList: ["기본"]
    },
    {
      purpose: "디자인 작업용",
      programList: ["어도비 일러스트", "어도비 포토샵", "어도비 인디자인", "클립 스튜디오"]
    },
    {
      purpose: "영상 작업용",
      programList: ["어도비 프리미어 프로", "모바비", "베가스 프로", "어도비 애프터이펙트"]
    },
    {
      purpose: "3D그래픽 작업용",
      programList: ["기본", "Autodesk 3ds Max", "CAD", "Blender","ZBrush","Rhinoceros","Cinema 4D","Autodesk Maya","스케치업","Houdini"]
    } 
  ];


  return (
    <>
      <div className={style.filter}>
        <div className={style.purpose}>
          <div>
            <span>용도</span>
            <select onChange={(e) => setPurpose(e.target.value)} className={style["select-input-blue"]}>
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
            <span >사용 프로그램</span>
            <select onChange={(val)=> setProgram(val)} className={classNames(`${style["select-input"]}`,`${style['usage-program']}`)}>
              {filterList.filter((curr)=>curr.purpose === purpose)[0].programList.map((program, idx)=>{
                return(
                <option key={idx} value={program}>
                  {program}
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
              defaultValue={[0, 5000000]}
              max={10000000}
              step={100000}
              className={style.slider}
              tooltip={{
                open: true,
                // getPopupContainer: (triggerNode) => {
                // }
              }}
            />
          </div>
          <div className={style["builder-box"]}>
            <button className={style.button}>견적 저장하기</button>
            <select className={style["select-input"]}>
              <option>견적 불러오기</option>
            </select>
          </div>
        </div>
      </div>
    </>
  );
};

export default RecommendFilter;
