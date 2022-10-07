import React, { useState } from "react";
import style from "@/styles/MyComment.module.scss";
import MyCommentList from "./MyCommentList";
import { Space, Button } from "antd";
const MyComment = () => {
  const [commentDetailDtoList, setCommentDetailDtoList] = useState([]);
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
      <Space className={style["my-comment-option"]}>
        <Button onClick={changeDescTrue}> 최신순</Button>
        <Button onClick={changeDescFalse}> 오래된순</Button>
      </Space>
      <div className={style["product-list"]}>
        <MyCommentList
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

export default MyComment;
