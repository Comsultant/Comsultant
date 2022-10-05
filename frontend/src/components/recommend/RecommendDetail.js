import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import BuilderProductSelector from "./BuilderProductSelector";
import style from "@/styles/RecommendDetail.module.scss";
import { Button, Radio } from 'antd';
const RecommendDetail = () => {
  const builder = useSelector((state) => state.builder);
  const products = [[],[],[],[],[],[],[],[],[]]

  // 키 값이 인덱스인거
  // builder.prodDetail.map((item, idx) => {
  //   Object.keys(item).map((prod) => {
  //     products[idx].push({[prod.split("_")[0]] : item[prod]})
  //   })
  // })

  // jSON 형식 맞춘거
  builder.prodDetail.map((item, idx) => {
    Object.keys(item).map((prod) => {
      products[idx].push({id: prod.split("_")[0], count : item[prod], price: 0, name: prod.split("_")[1]})
    })
  })

  const [filterItem, setFilterItem] = useState({});

  const pushRecommend = async () => {
    console.log(filterItem)
    // console.log(builder)
    let builderProducts = [];
    Object.keys(filterItem.prods).map((key) => {
      builderProducts.push({productIdx: key, cnt: filterItem.prods[key]})
    })

    const reqBody = {
      builderProducts: builderProducts,
      use: "work",
      program: "default"
    }
    console.log(reqBody)
  }

  const getRecommendList = () => {
    console.log("null");
  };

  return (
    <div className={style["detail-main"]}>
      <div className={style["detail-top-div"]}>
        <BuilderProductSelector
          filterItem={filterItem}
          setFilterItem={setFilterItem}
          builder={builder}
          products={products}
        />
      </div>
      <div className={style["detail-button-div"]}>
        <button onClick={pushRecommend} >
          추천
        </button>
      </div>
    </div>
  );
};

export default RecommendDetail;
