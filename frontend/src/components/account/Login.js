import React, { useRef, useState } from "react";
import { Button, Form, Input, Tooltip } from "antd";
import styled from "styled-components";
import style from "@/styles/Login.module.scss";

const LoginButton = styled(Button)`
  width: 100%;
  max-width: 350px;
  background-color: lightgray;
  color: black;
  border-radius: 5px;
  border-style: none;
  &:hover {
    color: black;
    background-color: rgba(211, 211, 211, 0.726);
  }
`;

const LoginInput = styled(Input)`
  border-style: none;
  border-left: solid rgb(190, 190, 190) 1px;
  border-radius: 0px;
  &:hover {
    border-color: rgb(190, 190, 190);
    outline: none;
  }
  &:focus {
    border-color: rgb(190, 190, 190);
    box-shadow: none;
  }
`;

const LoginInputPassword = styled(Input.Password)`
  border-style: none;
  border-left: solid rgb(190, 190, 190) 1px;
  border-radius: 0px;
  box-shadow: none;
  &:hover {
    border-color: black;
    outline: none;
  }
  &:focus {
    border-color: black;
    box-shadow: none;
  }
`;

const Login = () => {
  return (
    <div className={style["login-container"]}>
      <div className={style.title}>
        <span>Login</span>
      </div>
      <Form
        name="login"
        initialValues={{ remember: true }}
        autoComplete="off"
        layout="vertical"
        style={{ marginLeft: "auto", marginRight: "auto" }}
        className={style["login-form"]}
      >
        <Form.Item>
          <div className={style['input-box']}>
            <img src="/assets/icons/email.png" className={style["input-img"]} />
            <LoginInput placeholder="이메일" />
          </div>
        </Form.Item>
        <Form.Item>
          <div className={style['input-box']}>
            <img
              src="/assets/icons/password.png"
              className={style["input-img"]}
            />
            <LoginInputPassword
              placeholder="비밀번호"
              style={{ borderColor: "rgb(190, 190, 190)" }}
            />
          </div>
        </Form.Item>

        <Form.Item>
          <LoginButton>Login</LoginButton>
          {/* <Button type="primary" className={style['login-button']}>로그인</Button> */}
        </Form.Item>
      </Form>
      <div className={style["button-list"]}>
        <div>
          <span>회원가입</span>
          |
          <span>비밀번호 찾기</span>
        </div>
        <div className={style["social-button-list"]}>
          <div className={style["social-button"]}>
            <img src="/assets/buttons/google_button.png" alt="" />
          </div>
          <div className={style["social-button"]}>
            <img src="/assets/buttons/kakao_button.png" alt="" />
          </div>
          <div className={style["social-button"]}>
            <img src="/assets/buttons/naver_button.png" alt="" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
