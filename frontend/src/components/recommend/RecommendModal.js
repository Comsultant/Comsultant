import React, { useState } from "react";
import style from "@/styles/RecommendModal.module.scss"
import classNames from "classnames";
import { Checkbox } from "antd";

const RecommendModal = ({ currProduct }) => {

  const [selectProduct, setSelectProduct] = useState();

  const filterList = ["코어", "쓰레드"];
  const productList = [
    {
      id:"123",
      img:"/assets/monitor.png",
      name: "상품명1",
      price: "가격",
      detail: "상품정보",
    }, 
    {
      id:"1234",
      img:"/assets/monitor.png",
      name: "상품명2",
      price: "가격",
      detail: "상품정보",
    }, 
    {
      id:"1235",
      img:"/assets/monitor.png",
      name: "상품명3",
      price: "가격",
      detail: "상품정보",
    }, 
    {
      id:"1236",
      img:"/assets/monitor.png",
      name: "상품명4",
      price: "가격",
      detail: "상품정보",
    }, 
    {
      id:"1237",
      img:"/assets/monitor.png",
      name: "상품명5",
      price: "가격",
      detail: "상품정보",
    }, 
  ];
  return (
    <>
      <div className={style['top']}>
        <div className={classNames(`${style['top-item']}`,`${style['left-item']}`)}>
          <span className={style.title}>현재</span>
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
              <span>상세보기</span>
            </div>
          </div>
        </div>
      </div>  
      <div className={style['mid']}>
        <div className={style['left-item']}>
          <span className={style.title}>상세</span>
        </div>
        <div>
          {filterList.map((filter)=>{
            return(
              <>
                <Checkbox />
                {filter}
              </>
            );
          })}
        </div>
      </div>
      <div className={style['bottom']}>
          <div className={style['search-bar']}>
            <input />
          </div>
          {productList.map((product, idx)=>{
            return(
              <div key={idx} className={style['product-item']}>
                <div className={style['product-img']}>
                  <img src={product.img} alt=""/>
                </div>
                <div>
                  <div>
                    <span>{product.name}</span>
                  </div>
                  <div>
                    <span>{product.detail}</span>
                  </div>
                </div> 
                <div className={style['product-detail-button']}>
                  <button>상세보기</button>
                </div>
              </div>
            );
          })}
      </div>
    </>
  );
};

export default RecommendModal;
