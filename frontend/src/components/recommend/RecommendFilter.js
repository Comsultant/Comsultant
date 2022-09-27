import React from "react";
import { Slider, Checkbox } from "antd";
import style from "@/styles/RecommendFilter.module.scss";

const RecommendFilter = () => {
  return (
    <>
      <div className={style.filter}>
        <div className={style.purpose}>
          <div>
            <span>용도</span>
            <select className={style["select-input-blue"]}>
              <option>사무용</option>
            </select>
          </div>
          <div>
            <span>사용 프로그램</span>
            <select className={style["select-input"]}>
              <option>Adobe Photoshop</option>
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
