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
      <div className={style["product-list"]}>
              <MyWishList
                commentDetailDtoList={commentDetailDtoList}
                setCommentDetailDtoList={setCommentDetailDtoList}
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

export default MyBuilder;
