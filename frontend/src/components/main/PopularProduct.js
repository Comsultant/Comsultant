import React from "react";
import style from "@/styles/RecommendBuilder.module.scss"

const RecommendBuilder = () => {
  const builderList = [
  {
    img : "/assets/monitor.png",
    price : "1,240,000 원",
    cpu: "intel i5",
    vga: "RTX 2060",
    mb: "MainBoard",
    ram: "ram",
    cooloer: "cooler",
    case: "case",
  },
  {
    img : "/assets/monitor.png",
    price : "1,240,000 원",
    cpu: "intel i5",
    vga: "RTX 2060",
    mb: "MainBoard",
    ram: "ram",
    cooloer: "cooler",
    case: "case",
  },
  {
    img : "/assets/monitor.png",
    price : "1,240,000 원",
    cpu: "intel i5",
    vga: "RTX 2060",
    mb: "MainBoard",
    ram: "ram",
    cooloer: "cooler",
    case: "case",
  },
  {
    img : "/assets/monitor.png",
    price : "1,240,000 원",
    cpu: "intel i5",
    vga: "RTX 2060",
    mb: "MainBoard",
    ram: "ram",
    cooloer: "cooler",
    case: "case",
  }];

  return (
    <div className={style['popular-builder-box']}>
      <div className={style['title-box']}>
        <p>인기 상품</p>
        <span> >> 다른 상품 보러가기</span>
      </div>
      <div className={style['builder-container']}>
        {builderList.map((builder, idx) => {
          return (
            <div className={style['builder-content']}>
              <div className={style['builder-img']}>
                <img src={builder.img} alt=""/>
              </div>
              <div>
                <p>{builder.price}</p>
                <p>{builder.cpu}</p>
                <p>{builder.vga}</p>
                <p>{builder.mb}</p>
                <p>{builder.ram}</p>
                <p>{builder.cooler}</p>
                <p>{builder.case}</p>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default RecommendBuilder;