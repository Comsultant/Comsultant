import React, { useEffect, useState } from "react";
import CarouselComponent from "./CarouselComponent";
import style from "@/styles/Home.module.scss"

const Home = () => {
  const carouselContents = ["/assets/monitor.png", "/assets/keyboard.jpg"];

  return (
    <>
      <div className={style.carousel}>
        <CarouselComponent contents={carouselContents} />
      </div>
      <div>
        hello
      </div>
    </>
  );
};

export default Home;
