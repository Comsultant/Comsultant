import React, { useState } from "react";
import style from "@/styles/Recommend.module.scss";

import RecommendFilter from "./RecommendFilter";
import ProductSelector from "./ProductSelector";
import RecommendList from "./RecommendList";

const Recommend = () => {

  const defaultMaxPrice = 5000000;

  const [isRecommendPressed, setIsRecommendPressed] = useState(false);
  const [minPrice, setMinPrice] = useState(0);
  const [maxPrice, setMaxPrice] = useState(defaultMaxPrice);

  return (
    <div className={style["container"]}>
      <RecommendFilter />
      <br/>
      <ProductSelector
        isRecommendPressed={isRecommendPressed}
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
