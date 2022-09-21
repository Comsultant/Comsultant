import axios from "axios";
import { store } from "..";
import { message } from "antd";
import { SET_TOKEN } from "@/reducer/type";

/**
 * 인증 필요없는 Axios
 */
export const request = axios.create({
  baseURL: process.env.REACT_APP_SERVER_URL,
  // withCredentials: true,
});

request.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    console.log(error);
    return Promise.reject(error);
  }
);

/**
 * 인증 필요한 Axios
 */
export const axiosAuth = axios.create({
  baseURL: process.env.REACT_APP_SERVER_URL,
  timeout: 10000,
  withCredentials: true,
});

axiosAuth.interceptors.request.use(
  function (config) {
    const accessToken = store.getState().account.token;
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);


axiosAuth.interceptors.response.use(
  function (response) {
    return response;
  },

  async function (error) {
    const result = error.config;
    console.log(error);

    // 로그아웃에 대한 거면 그냥 Pass 시킨다.
    if (result.url === '/api/auth' && result.method === 'delete') {
      return Promise.reject(error);
    }

    // accessToken 재발급
    if (error.response.status === 401 && result.retry !== true) {
      result.retry = true;
      const res = await getToken();

      if(res?.data.message === "success"){
        const accessToken = res.data.responseDto.accessToken;
        store.dispatch({ type: SET_TOKEN, payload: { accessToken } });
        return await axiosAuth(result);
      }else{ // accessToken 재발급 실패
        message.error("refreshToken 만료...다시 로그인 후 시도해 주세요.");
        store.dispatch({type: LOGOUT});
        window.location.replace("/account/login");
      }
    }else {
      message.error("다시 로그인 후 시도해 주세요.");
      store.dispatch({type: LOGOUT});
      window.location.replace("/account/login");
    }
    return Promise.reject(error);
  }
);