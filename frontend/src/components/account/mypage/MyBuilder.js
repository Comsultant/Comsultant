import React, { useState } from "react";
import style from "@/styles/MyBuilder.module.scss";
import MyBuilderList from "./MyBuilderList";

const MyBuilder = () => {
  const [myBuilderList, setMyBuilderList] = useState([]);
  const [currPage, setCurrPage] = useState(1);
  const [totalPage, setTotalPage] = useState(0);
  
  return (
    <div className={style.container}>
      <MyBuilderList
        myBuilderList={myBuilderList}
        setMyBuilderList={setMyBuilderList}
        currPage={currPage}
        setCurrPage={setCurrPage}
        totalPage={totalPage}
        setTotalPage={setTotalPage}
      />
    </div>
  );
};

export default MyBuilder;
