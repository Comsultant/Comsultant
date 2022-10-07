import React, { useEffect, useState } from "react";
import CarouselComponent from "./CarouselComponent";
import PopluarBuilder from "./main/PopularBuilder";
import RecommendBuilder from "./main/PopularProduct";
import style from "@/styles/Home.module.scss"

const Home = () => {
  const carouselContents = ["/assets/keyboard.jpg", "/assets/cpu.jpg"];

  return (
    <>
      <div className={style.carousel}>
        <CarouselComponent contents={carouselContents} />
      </div>
      <div className={style['main-container']}>
        <PopluarBuilder />
        <div className={style.content}>
          <RecommendBuilder />
        </div>
      </div>
    </>
  );
};

export default Home;
