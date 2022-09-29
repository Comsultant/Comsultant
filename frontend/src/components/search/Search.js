import React, { useState, useEffect } from "react";
import style from "@/styles/Search.module.scss"
import ProductFilter from "../recommend/ProductFilter";
import DescFilter from "../recommend/DescFilter";
import { SearchOutlined } from "@ant-design/icons";

const Search = () => {
  const [currTypeTab, setCurrTypeTab] = useState(0);
  const [filterList, setFilterList] = useState([]);
  const [filterDetailList, setFilterDetailList] = useState([]);
  const [currDescNum, setCurrDescNum] = useState(0);


  const typeList = ["CPU", "그래픽카드", "RAM", "케이스", "기타"]

  useEffect(()=>{
    //필터링 데이터, 인기상품순 상품 데이터 받아오기
    switch(currTypeTab){
      case 0:
        setFilterList(["제조회사", "코어 수"]);
        setFilterDetailList([["인텔","AMD"], ["2코어", "4코어", "8코어"]]);
        break;
      default:
        break;
    }
  }, [])

  useEffect(()=> {
    // 순서 변경한 상품 데이터 받아오기

  },[currDescNum])
  
  return (
    <div className={style.container}>
      <div className={style['product-tab']}>
        {typeList.map((type, idx) => {
          return (
            <span key={idx} onClick={()=>setCurrTypeTab(idx)}>
              {type}
            </span>
          );
        })}
      </div>
      <div className={style['product-filter']}>
        <ProductFilter
          filterList={filterList} 
          filterDetailList={filterDetailList}
        />
      </div>
      <Slider
          range={{ draggableTrack: true }}
          onChange={onPriceChange}
          defaultValue={[0, defaultMaxPrice]}
          max={10000000}
          step={100000}
          className={style.slider}
          tooltip={{
            open: true,
            formatter,
          }}
          trackStyle={{"backgroundColor": "#377BB9", "height" : "8px"}}
          handleStyle={{"borderColor": "black", "width" : "25px", "height": "16px", "borderRadius" : "5px"}}
        />
      <div className={style['main-content']}>
        <div className={style['desc-filter']}>
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
      </div>
    </div>
  );
}

export default Search;

