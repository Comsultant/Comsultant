import React, { useEffect, useState } from "react";
import style from "@/styles/RecommendModal.module.scss"
import RecommendSearch from "./RecommendSearch";

const RecommendModal = ({type, pickProduct, setPickProduct}) => {
  return (
    <div>
      <div className={style['top']}>
        <div className={style['top-item']}>
          <div className={style.title}>현재</div>
          <div className={style['top-content']}>
            <div className={style['top-img']}>
              <img src="/assets/monitor.png" alt="" />
            </div>
            <div className={style['detail-button']}>
              <span>상세보기</span>
            </div>
          </div>
        </div>
        
        <div className={style['top-item']}>
          <span className={style.title}>선택</span>
          <div className={style['top-content']}>
            <div className={style['top-img']}>
              <img src="/assets/monitor.png" alt="" />
            </div>
            <div className={style['detail-button']}>
              <span>{pickProduct.name}</span>
            </div>
          </div>
        </div>
      </div>
      <RecommendSearch
        pickProduct={pickProduct}
        setPickProduct={setPickProduct}
        type = {type}
      />
    </div>
  );
};

export default RecommendModal;
