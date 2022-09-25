import React from "react";
import style from "@/styles/Recommend.module.scss";
import { Slider, Checkbox } from "antd";
import { InfoCircleOutlined, PlusSquareOutlined } from "@ant-design/icons";

const Recommend = () => {
  return (
    <div className={style["container"]}>
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
      <div className={style["content-box"]}>
        <div>
          <InfoCircleOutlined />
          <Checkbox checked={true} />
          <span>CPU</span>
          <PlusSquareOutlined />
        </div>
        <div>
          <InfoCircleOutlined />
          <Checkbox checked={true} />
          <span>M/B</span>
          <PlusSquareOutlined />
        </div>
        <div>
          <InfoCircleOutlined />
          <Checkbox checked={true} />
          <span>그래픽카드</span>
          <PlusSquareOutlined />
        </div>
        <div>
          <InfoCircleOutlined />
          <Checkbox checked={true} />
          <span>RAM</span>
          <PlusSquareOutlined />
        </div>
        <div>
          <InfoCircleOutlined />
          <Checkbox checked={true} />
          <span>POWER</span>
          <PlusSquareOutlined />
        </div>
        <div>
          <InfoCircleOutlined />
          <Checkbox checked={true} />
          <span>SSD</span>
          <PlusSquareOutlined />
        </div>
        <div>
          <InfoCircleOutlined />
          <Checkbox />
          <span>HDD</span>
          <PlusSquareOutlined />
        </div>
      </div>
    </div>
  );
};

export default Recommend;
