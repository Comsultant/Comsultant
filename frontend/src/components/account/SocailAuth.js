import React from "react";
import { useDispatch } from "react-redux";
import { useLocation, useNavigate, useSearchParams } from 'react-router-dom'
import { googleLoginRequest, kakaoLoginRequest, naverLoginRequest } from "@/services/accountService";
import { message } from "antd";

const SocailAuth = () => {
  
  const popMessage = (content, isSuccess) => {
    if(isSuccess){
      message.success(content);
    }else{
      message.error(content);
    }
  };

  const socialRequest = async (service) => {

    switch (service) {
      case "google":
        result = await googleLoginRequest({ service, code });
        break;
      case "kakao":
        result = await kakaoLoginRequest({ service, code });
        break;
      case "naver":
        result = await naverLoginRequest({ service, state, code });
        break;
    }
    console.log(result);
    const message = result?.payload?.data?.message;
    console.log(message);
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

  const url = useLocation();
  const [param, setParam] = useSearchParams();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const service = url.pathname.split("/")[2];
  
  const code = param.get("code");
  const state = param.get("state");

  let result;
  socialRequest(service);
  

  return <></>;
}

export default SocailAuth;