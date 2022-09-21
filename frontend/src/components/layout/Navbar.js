import React from "react";
import { Link, useNavigate } from "react-router-dom";
import style from "@/styles/Navbar.module.scss"
import { useDispatch } from "react-redux";
import { logoutRequest } from "@/services/accountService";
import { LOGOUT } from "@/reducer/type";
import { message } from "antd";

const Navbar = () => {

  const pages=[
    [`부품 검색`, `/products/search`],
    [`맞춤 견적`, `/recommend`],
    [`마이페이지`, `/account/mypage`],
    [`회원가입`, `/account/regist`],
    [`로그인`, `/account/login`],
  ];

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const onLogOutClicked = () => {
    logoutRequest();
    dispatch({
      type: LOGOUT,
    });
    message.error("로그아웃 완료!");
    navigate("/");
  }

  return (
    <div className={style['nav-bar']}>
      <div className={style.logo}>
        <Link to={`/`}>
          <img src="/../assets/logo.png" className={style['logo-image']} alt="logo"/>
        </Link> 
      </div>
      <div className={style['right-menu']}>
        {pages.map((page, idx) => (
          <Link
            to={page[1]}
            key={idx}
            className={style['nav-menu-item']}
          >
            {page[0]}
          </Link>
        ))}
        <span className={style['logout-button']} onClick={onLogOutClicked}>
          로그아웃
        </span>
      </div>
    </div>
  );
};

export default Navbar;
