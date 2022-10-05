import React, { useState, useEffect } from "react";
import style from "@/styles/Recommend.module.scss";

import RecommendFilter from "./RecommendFilter";
import ProductSelector from "./ProductSelector";
import RecommendList from "./RecommendList";

const Recommend = () => {


  const [isRecommendPressed, setIsRecommendPressed] = useState(false);

  const [filterItem, setFilterItem] = useState(
    {
      prods: {},
      cpu_cnt: 1,
      ram_cnt: 2,
      hdd_cnt: 0,
      ssd_cnt: 1,
      psu_cnt: 1,
      cooler_cnt: 0,
      cases_cnt: 1,
      mainboard_cnt: 1,
      vga_cnt: 1,
      use: "work",
      program: "default",
      lowPrice: 0,
      highPrice: 5000000
    }
  )

  useEffect(() => {
    console.log("filter updated")
  }, [filterItem])



  return (
    <div className={style["container"]}>
      <RecommendFilter 
        filterItem = {filterItem}
        setFilterItem = {setFilterItem}
      />
      <br/>
      <ProductSelector
        isRecommendPressed={isRecommendPressed}
        filterItem = {filterItem}
        setIsRecommendPressed={setIsRecommendPressed}
      />
      <br />
      <RecommendList
        isRecommendPressed={isRecommendPressed}
      />
    </div>
  );
};

export default Recommend;
