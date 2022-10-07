import { LOGIN, LOGOUT, TOKEN_DELETE, SET_TOKEN } from "./type";

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
    case LOGOUT:
      return {
        ...state,
        isLogin: false,
        nickname: "",
        auth: "",
      }
    case TOKEN_DELETE:
      return initState; 
    case SET_TOKEN:
      return {
        ...state,
          token: action.payload.accessToken,
      }  
    default:
      return state;
  }
}