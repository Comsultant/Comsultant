import React, { useState } from "react";
// import style from "@/styles/Search.module.scss";
import style from "@/styles/MyWish.module.scss";
import MyWishList from "./MyWishList";
import { Button, Space } from "antd";

const MyWish = () => {
  const [wishDtoList, setWishDtoList] = useState([]);
  const [currPage, setCurrPage] = useState(1);
  const [totalPage, setTotalPage] = useState(0);
  const [currDesc, setCurrDesc] = useState(true);

  const changeDescTrue = () => {
    setCurrDesc(true);
  }
  const changeDescFalse = () => {
    setCurrDesc(false);
  }

  return (
    <div className={style.container}>
      <Space className={style["my-wish-option"]}>
        <Button onClick={changeDescTrue}> 최신순</Button>
        <Button onClick={changeDescFalse}> 오래된순</Button>
      </Space>
      <div className={style["product-list"]}>
        <MyWishList
          wishDtoList={wishDtoList}
          setWishDtoList={setWishDtoList}
          currPage={currPage}
          totalPage={totalPage}
          setTotalPage={setTotalPage}
          setCurrPage={setCurrPage}
          currDesc={currDesc}
          setCurrDesc={setCurrDesc}
        />
      </div>
    </div>
  );
};

export default MyWish;
