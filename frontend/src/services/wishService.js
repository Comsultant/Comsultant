import { request, axiosAuth } from "./axios";

/* 요청 URL*/
const WISH_URL = "/api/wish";

// 찜 생성
export const postWishRequest = async (dataToSubmit) => {
  try {
    const payload = await axiosAuth.post(`${WISH_URL}/${dataToSubmit}`);
    return payload;
  } catch (err) {
    return err;
  }
};

//찜리스트 조회
export const deleteWishRequest = async (dataToSubmit) => {
  try {
    const payload = await axiosAuth.delete(`${WISH_URL}/${dataToSubmit}`);
    return payload;
  } catch (err) {
    return err;
  }
};
