import { SET_RECOMMEND } from "./type";

export const initState = {};

export default function (state = initState, action) {
  switch (action.type){
    case SET_RECOMMEND:
      return action.payload;
    default:
      return state;
  }
}