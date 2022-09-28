import React, {Component} from "react";
import { Icon } from "antd";
import { Carousel } from "react-responsive-carousel";
import style from "@/styles/CarouselComponent.module.scss";
import "react-responsive-carousel/lib/styles/carousel.min.css";


const CarouselComponent = ({contents}) => {

  const title = ["부품 검색", "맞춤 견적"];
  const desc = ["컴설턴트에서 다양한 부품을 검색해보세요.","컴설턴트가 완벽한 시스템을 구성해 드립니다."];
  return (
    <Carousel
      showArrows={true}
      showThumbs={false}
      dynamicHeight={true}
      infiniteLoop={true}
      autoPlay={true}
      showStatus={false}
      interval={4000}
      transitionTime={500}
      className={style.carousel}
    >
      {contents.map((content, idx) => {
        return (
          <div
            key={idx}
            style={{ backgroundImage: `url(${content})` }}
            className={style['content-box']}
          >
            <div className={style.content}>
                <p className={style['content-title']}>{title[idx]}</p>
                <p className={style['content-desc']}>{desc[idx]}</p>
                <button className={style['content-button']}>자세히 알아보기</button>
            </div>
          </div>
          );
      })}
    </Carousel>
  );
};

export default CarouselComponent;
