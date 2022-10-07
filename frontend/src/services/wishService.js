import { request, axiosAuth } from "./axios";

/* 요청 URL*/
const WISH_URL = "/api/wish";

export const getWishListRequest = async (desc, page) => {
  try {
    const payload = await axiosAuth.get(`${WISH_URL}?desc=${desc}&page=${page}`);
    return payload;
  } catch (err) {
    return err;
  }
};

// 찜 생성
export const postWishRequest = async (dataToSubmit) => {
  try {
    const payload = await axiosAuth.post(`${WISH_URL}/${dataToSubmit}`);
    return payload;
  } catch (err) {
    return err;
  }
};

//찜리스트 삭제
export const deleteWishRequest = async (dataToSubmit) => {
  try {
    const payload = await axiosAuth.delete(`${WISH_URL}/${dataToSubmit}`);
    return payload;
  } catch (err) {
    return err;
  }
};
