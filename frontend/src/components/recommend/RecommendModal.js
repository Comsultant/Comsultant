import React, { useEffect, useState } from "react";
import style from "@/styles/RecommendModal.module.scss"
import classNames from "classnames";
import { Checkbox } from "antd";
import ProductFilter from "./ProductFilter";
import { filter } from "lodash";
import DescFilter from "./DescFilter";
import { SearchOutlined } from "@ant-design/icons";
import ProductListComponent from "./ProductListComponent";

const RecommendModal = ({ currProduct, type }) => {

  const [selectProduct, setSelectProduct] = useState("");
  const [searchValue, setSearchValue] = useState("");
  const [currDescNum, setCurrDescNum] = useState(0);
  const [filterList, setFilterList] = useState([]);
  const [filterDetailList, setFilterDetailList] = useState([]);
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
  useEffect(()=>{
    //필터링 데이터, 인기상품순 상품 데이터 받아오기
    switch(type){
      case 'CPU':
        setFilterList(["제조회사", "코어 수"]);
        setFilterDetailList([["인텔","AMD"], ["2코어", "4코어", "8코어"]]);
        break;
      default:
        break;
    }
  },[])
  useEffect(()=> {
    // 순서 변경한 상품 데이터 받아오기
  },[currDescNum])
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
              <span>상세보기</span>
            </div>
          </div>
        </div>
      </div>  
      <div className={style['mid']}>
        <ProductFilter 
          filterList={filterList} 
          filterDetailList={filterDetailList} 
        />
      </div>
      <div className={style['bottom']}>
          <div className={style['bottom-header']}>
            <DescFilter 
              currDescNum={currDescNum}
              setCurrDescNum={setCurrDescNum}
            />
            <div className={style['search-bar']}>
              <input 
                onChange={(e)=>{setSearchValue(e.target.value)}}
              />
              <SearchOutlined className={style['search-icon']}/>
            </div>
          </div>
          <ProductListComponent productList={productList}/>
      </div>
    </div>
  );
};

export default RecommendModal;
