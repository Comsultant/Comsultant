import { LOGIN } from "./type";

export const initState = {
  isLogin: false,
  nickname: "",
  token: "",
};

export default function (state = initState, action) {
  switch (action.type){
    case LOGIN:
      return {
        ...state,
        isLogin: true,
        nickname: action.payload.data.responseDto.nickname,
        token: action.payload.data.responseDto.accessToken,
      }
  }
  return state;
}