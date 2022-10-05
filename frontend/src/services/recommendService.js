import { request, axiosAuth } from "./axios";

/* 요청 URL*/
const RECOMMEND_URL = "/api/recommend";

// 상품 카테고리별 목록 가져오기
export const getPopularRecommendRequest = async () => {
  try {
    const payload = await request.get(`${RECOMMEND_URL}/popular`);
    return payload;
  } catch (err) {
    return err;
  }
};

// 추천 받기
export const getRecommendBuilder = async (option) => {
  try {
    const payload = await request.post(`${RECOMMEND_URL}`, option);
    return payload
  } catch (err) {
    return err;
  }
}
