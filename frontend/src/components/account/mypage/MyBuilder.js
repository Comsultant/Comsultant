import React, { useState } from "react";
import style from "@/styles/Search.module.scss";
import MyWishList from "./MyWishList";

const MyBuilder = () => {
  const [commentDetailDtoList, setCommentDetailDtoList] = useState([]);
  const [currPage, setCurrPage] = useState(1);
  const [totalPage, setTotalPage] = useState(0);
  const [currDesc, setCurrDesc] = useState(false);

  return (
    <div className={style.container}>
      내견적입니다.
    </div>
  );
};

export default MyBuilder;
