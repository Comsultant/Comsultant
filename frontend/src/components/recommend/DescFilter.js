import React from "react";
import { Tabs } from "antd";
import style from "@/styles/DescFilter.module.scss"

const DescFilter = ({currDescNum, setCurrDescNum}) => {

  const descFilterList = ["신상품순", "낮은 가격순", "높은 가격순"];

  const onTabChange = (e) => {
    setCurrDescNum(e)
  }

  const isSelected = (type) => {
    return currDescNum == type ? style['selected'] : style['not-selected']
  }

  return (
    <>
      <div className={style['tab-box']}>
        <button onClick={()=>setCurrDescNum('0')} className={isSelected(0)}>신상품순</button>
        <button onClick={()=>setCurrDescNum('1')} className={isSelected(1)}>낮은 가격순</button>
        <button onClick={()=>setCurrDescNum('2')} className={isSelected(2)}>높은가격순</button>
      </div>
      {/* <Tabs
        defaultActiveKey="1"
        className={style['item']}
        onChange={onTabChange}
        type="card"
        size="small"
        // tabBarStyle = {{}}
        tabBarStyle={{borderTop: 'solid 1px rgb(190, 190, 190)',
          borderRight: 'solid 1px rgb(190, 190, 190)',
          borderLeft: 'solid 1px rgb(190, 190, 190)',}}
        items={new Array(descFilterList.length).fill(null).map((_, i) => {
          const id = descFilterList[i];
          return {
            label: ` ${id}`,
            key: i,
          };
        })}
      /> */}
    </>
    // <div className={style["container"]}>
    //   {descFilterList.map((item, idx) => {
    //     return <div key={idx} className={style['item']}>{item}</div>;
    //   })}
    // </div>
  );
};

export default DescFilter;
