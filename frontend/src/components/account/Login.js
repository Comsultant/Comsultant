import React, { useEffect, useRef, useState } from "react";
import { Button, Form, Input, Tooltip, message } from "antd";
import styled from "styled-components";
import style from "@/styles/Login.module.scss";
import { useNavigate } from "react-router-dom";
import { loginRequest } from "@/services/accountService";
import { useDispatch, useSelector } from "react-redux";


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

  const navigate = useNavigate();
  const dispatch = useDispatch();
  
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const isLogin = useSelector((state) => state.account.isLogin);

  const emailInput = useRef();
  const passwordInput = useRef();

  const popMessage = (content, isSuccess) => {
    if(isSuccess){
      message.success(content);
    }else{
      message.error(content);
    }
  };

  const onHandleSubmit = async() => {
    if(!email){
      popMessage("이메일을 입력하세요", false);
      emailInput.current.focus();
      return;
    }
    if(!password){
      popMessage("비밀번호를 입력하세요", false);
      passwordInput.current.focus();
      return;
    }

    const account = {
      email,
      password,
    } 

    const result = await loginRequest(account);
    const message = result?.payload?.data?.message;
    if(message === "success"){
      dispatch(result);
      popMessage("로그인 성공!", true);
      navigate("/");
    }else if(message === "User Not Found"){
      popMessage("존재하지 않는 이메일 계정입니다!", false);
    }else if(message === "Wrong Password"){
      popMessage("비밀번호를 확인해주세요!", false);
    }else{
      popMessage("알 수 없는 오류 발생...", false);
    }

  }

  useEffect(() => {
    if(isLogin){
      navigate("/");
    }
  },[]);


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
        <Form.Item
          name="email"
          
          // rules={[
          //   {
          //     required: true,
          //     message: "이메일을 입력해주세요!"
          //   }
          // ]}
        >
          <div className={style['input-box']}>
            <img src="/assets/icons/email.png" className={style["input-img"]} />
            <LoginInput 
            placeholder="이메일"
            ref={emailInput} 
            value={email} 
            onChange={({ target: { value } }) => setEmail(value.trim())}
            />
          </div>
        </Form.Item>
        <Form.Item
          name="password"
        >
          <div className={style['input-box']}>
            <img
              src="/assets/icons/password.png"
              className={style["input-img"]}
            />
            <LoginInputPassword
              placeholder="비밀번호"
              ref={passwordInput}
              value={password}
              onChange={({ target: { value } }) => setPassword(value.trim())}
              style={{ borderColor: "rgb(190, 190, 190)" }}
            />
          </div>
        </Form.Item>

        <Form.Item>
          <button 
          className={style['login-button']}
          onClick={onHandleSubmit}
          >
            Login
          </button>
        </Form.Item>
      </Form>
      <div className={style["button-list"]}>
        <div>
          <span onClick={() => navigate('/account/regist')}>회원가입</span>
          |
          <span onClick={() => navigate('/account/password')}>비밀번호 찾기</span>
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
