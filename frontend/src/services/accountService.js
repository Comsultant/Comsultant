import { request, axiosAuth } from "./axios";
import { store } from "..";


/* 요청 URL*/
export const ACCOUNT_URL = "/api/account";

// 회원가입
export const registRequest = async (dataToSubmit) => {
  try {
    const payload = await request.post(ACCOUNT_URL, dataToSubmit);
    return payload;
  } catch (err) {
    return err;
  }
};

// 이메일 인증번호 전송
export const sendAuthNumberEmail = (dataToSubmit) => {
  try {
    // const payload = axiosAuth.post(`${ACCOUNT_URL}/verify-email`, dataToSubmit);
    // return payload;
    request.post(`${ACCOUNT_URL}/verify-email`, dataToSubmit);
  } catch (err) {
    return err;
  }
};

// 이메일 인증번호 확인
export const verifyAuthNumber = async (dataToSubmit) => {
  try {
    console.log(dataToSubmit);
    // param : code, body : email
    const payload = await request.get(`${ACCOUNT_URL}/verify-email/${dataToSubmit.code}?email=${dataToSubmit.email}`);
    return payload;
  } catch (err) {
    return err;
  }
};

// 이메일이 중복체크
export const checkEmailRequest = async (dataToSubmit) => {
  try {
    const payload = await request.get(`${ACCOUNT_URL}/email/${dataToSubmit}`);
    return payload;
  } catch (err) {
    return err;
  }
};

// 닉네임 중복체크
export const checkNickNameRequest = async (dataToSubmit) => {
  try { 
    const payload = await request.get(`${ACCOUNT_URL}/name/${dataToSubmit}`);
    return payload;
  }catch (err){
    return err;
  }
};
