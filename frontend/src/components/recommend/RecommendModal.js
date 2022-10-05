import React, { useEffect, useState } from "react";
import style from "@/styles/RecommendModal.module.scss";
import RecommendSearch from "./RecommendSearch";
import { ArrowRightOutlined } from "@ant-design/icons";

const RecommendModal = ({ type, pickProduct, setPickProduct }) => {
  const productType = {"cpu" : 0, "vga" : 2, "ram" : 3, "hdd": 6, "ssd": 5, "psu": 4, "cooler": 8, "cases": 7, "mainboard": 1};

  return (
    <div>
      <RecommendSearch
        pickProduct={pickProduct}
        setPickProduct={setPickProduct}
        type={type}
      />
      <div className={style["hide-bottom"]}></div>
      <div className={style["top"]}>
        <div className={style["top-item"]}>
          <span className={style["top-item-title"]}>선택된 제품 </span>
          {pickProduct.id != "" ? 
            <div className={style["top-content"]}>
              <div className={style["top-img"]}>
                <img
                  src={`https://j7a602.p.ssafy.io/static/images/${pickProduct.id}/0.jpg`}
                  alt=""
                />
              </div>
              <div className={style["detail-button"]}>
                <span><a target="_blank" href={`/product/info?idx=${pickProduct.id}&type=${productType[type]}`}>{pickProduct.name}</a></span>
              </div>
            </div>
           : 
            <div className={style["top-content"]}>
              <div className={style["top-img"]}>
                <img src="/assets/monitor.png" alt="" />
              </div>
              <div className={style["detail-button"]}>
                <span>없음</span>
              </div>
            </div>
          }
        </div>
      </div>
    </div>
  );
};

export default RecommendModal;
