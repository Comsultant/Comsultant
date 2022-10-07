import React, { useState, useEffect } from "react";
import style from "@/styles/Recommend.module.scss";
import { LeftOutlined, RightOutlined  } from '@ant-design/icons';
import { Button, Tooltip } from 'antd';
import RecommendFilter from "./RecommendFilter";
import ProductSelector from "./ProductSelector";
import RecommendList from "./RecommendList";
import { getRecommendBuilder } from "@/services/recommendService";
import Loading from "../Loading";
const Recommend = () => {
  const [filterItem, setFilterItem] = useState(
    {
      prods: {},
      cpu_cnt: 1,
      ram_cnt: 1,
      hdd_cnt: 0,
      ssd_cnt: 1,
      psu_cnt: 1,
      cooler_cnt: 1,
      cases_cnt: 1,
      mainboard_cnt: 1,
      vga_cnt: 1,
      use: "game",
      program: "",
      lowPrice: 0,
      highPrice: 5000000
    }
  )
  const [builderPage, setBuilderPage] = useState("0");
  const [finalPage, setFinalPage] = useState("0");
  const [recommendBuilderList, setRecommendBuilderList] = useState([])
  const [isLoading, setIsLoading] = useState(false);

  const getRecommendList = async () => {
    setRecommendBuilderList([]);
    const result = await getRecommendBuilder(filterItem);
    if(result?.data?.message === "success") {
      for(let l of result.data.responseList) {
        l.use = filterItem.use;
        l.program = filterItem.program;
      }
      setRecommendBuilderList(result.data.responseList)
      setBuilderPage(0);
      setFinalPage(result.data.responseList.length);
    }
  }

  const clickNextBuilder = () => {
    setBuilderPage(builderPage => builderPage + 1)
  }

  const clickPrevBuilder = () => {
    setBuilderPage(builderPage => builderPage - 1)
  }

  useEffect(() => {
  }, [filterItem])

  return (
    <div className={style["container"]}>
      <RecommendFilter 
        filterItem = {filterItem}
        setFilterItem = {setFilterItem}
      />
      <br/>
      <ProductSelector
        filterItem = {filterItem}
        setFilterItem = {setFilterItem}
        getRecommendList={getRecommendList}
        isLoading={isLoading}
        setIsLoading={setIsLoading}
      />
      <br />
      <>
        {isLoading ? <div><Loading /></div> : null}
        {recommendBuilderList.length > 0 ? 
        <RecommendList
          item = {recommendBuilderList[builderPage]}
          filterItem={filterItem}
          /> :           
          !isLoading ?
        <div className={style['no-result-box']}>
          <span>추천할수 있는 제품이 없습니다.</span><br/>
          <span>다음과 같은 경우 제품 추천 결과가 없을 수 있습니다.</span><br/>
          <span> • 내가 선택한 부품의 제품이 내가 선택한 용도의 최소 사양보다 낮은 경우</span><br/>
          <span> • 내가 설정한 가격이 너무 낮은 경우</span>
        </div>
            :
            null
      }
      </>
      <div className={style['recommend-page-div']}>
        <Tooltip title="이전">
          <Button onClick={clickPrevBuilder} icon={<LeftOutlined />} disabled = {builderPage == 0 || finalPage == 0 } />
        </Tooltip>
        {/* <Button type="primary" shape="circle">
          {builderPage}
        </Button> */}
        <Tooltip title="다음">
          <Button width= "300px" onClick={clickNextBuilder} icon={<RightOutlined />} disabled={builderPage >= finalPage-1} />
        </Tooltip>
      </div>
      
    </div>
  );
};

export default Recommend;
