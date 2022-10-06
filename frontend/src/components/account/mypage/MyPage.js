import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { Tabs } from "antd";
import style from "@/styles/Search.module.scss";
import MyBuilder from "./MyBuilder";
import MyComment from "./MyComment";
import MyProfile from "./MyProfile";
import MyWish from "./MyWish";
import { getAccountInfoRequest } from "@/services/accountService.js"

const MyPage = () => {

  //변수

  const navigate = useNavigate();
  
  const [currTypeTab, setCurrTypeTab] = useState(0);
  const [email, setEmail] = useState("");
  const [nickName, setNickName] = useState("");
  const [birthYear, setBirthYear] = useState("");
  const tabs = [
    { label: "마이 페이지", key: "0" },
    { label: "댓글", key: "1" },
    { label: "찜한 목록", key: "2" },
    { label: "견적", key: "3" },
  ];
  const isLogin = useSelector((state) => state.account.isLogin);
  
  //함수
  
  const showTab = () => {
    if (currTypeTab == 0) {
      return (
        <MyProfile
          email={email}
          nickName={nickName}
          setNickName={setNickName}
          birthYear={birthYear}
          setBirthYear={setBirthYear}
        />
      )
    } else if (currTypeTab == 1) {
      return (
        <MyComment
        email={email}
        nickName={nickName}
        birthYear={birthYear}
      />
      )
    } else if (currTypeTab == 2) {
      return (
        <MyWish
        email={email}
        nickName={nickName}
        birthYear={birthYear}
      />
      )
    } else if (currTypeTab == 3) {
      return (
        <MyBuilder
        email={email}
        nickName={nickName}
        birthYear={birthYear}
      />
      )
    }
  };
  
  //useEffect

  useEffect(() => {
    if(!isLogin){
      navigate("/");
    }
  }, []);
  
  useEffect(() => {
    const fetchData = async () => {
      const result = await getAccountInfoRequest();
      if (result?.data?.message === "success") {
        setEmail(result?.data?.responseDto?.email);
        setNickName(result?.data?.responseDto?.nickname);
        setBirthYear(result?.data?.responseDto?.birthYear);
      }
    }
    fetchData();
  }, [])



  return (
    <div className={style.container}>
      <div className={style["product-tab"]}>
        <Tabs
          defaultActiveKey="0"
          items={tabs}
          size={"large"}
          onChange={key => { setCurrTypeTab(key); }}
          className={style['tabs']}
        />
      </div>
      <div>{showTab()}</div>
      </div>
  );
};

export default MyPage;
