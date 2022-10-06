import React, { useState } from "react";
import style from "@/styles/Search.module.scss";

const MyBuilder = () => {
  const [commentDetailDtoList, setCommentDetailDtoList] = useState([]);
  const [currPage, setCurrPage] = useState(1);
  const [totalPage, setTotalPage] = useState(0);
  const [currDesc, setCurrDesc] = useState(false);

  return (
    <div className={style.container}>
            <>
        {recommendBuilderList.length > 0 ? 
        <RecommendList
          item = {recommendBuilderList[builderPage]}
        /> :           
        <div className={style['no-result-box']}>
          <span>추천할수 있는 제품이 없습니다.</span><br/>
          <span>다음과 같은 경우 제품 추천 결과가 없을 수 있습니다.</span><br/>
          <span> • 내가 선택한 부품의 제품이 내가 선택한 용도의 최소 사양보다 낮은 경우</span><br/>
          <span> • 내가 설정한 가격이 너무 낮은 경우</span>
        </div>
      
      }
      </>
    </div>
  );
};

export default MyBuilder;
