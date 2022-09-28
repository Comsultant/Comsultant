import React from "react";
import { Tabs } from "antd";
import style from "@/styles/DescFilter.module.scss"

const DescFilter = ({currDescNum, setCurrDescNum}) => {

  const descFilterList = ["인기상품순", "신상품순", "낮은 가격순", "높은 가격순"];

  const onTabChange = (e) => {
    setCurrDescNum(e)
  }

  return (
    <>
      <Tabs
        defaultActiveKey="1"
        onChange={onTabChange}
        type="card"
        size="small"
        items={new Array(descFilterList.length).fill(null).map((_, i) => {
          const id = descFilterList[i];
          return {
            label: ` ${id}`,
            key: i,
          };
        })}
      />
    </>
    // <div className={style["container"]}>
    //   {descFilterList.map((item, idx) => {
    //     return <div key={idx} className={style['item']}>{item}</div>;
    //   })}
    // </div>
  );
};

export default DescFilter;
