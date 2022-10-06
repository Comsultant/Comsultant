import { request, axiosAuth } from "./axios";

/* 요청 URL*/
const RECOMMEND_URL = "/api/recommend";
const BUILDER_URL = "/api/builder"

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

// 비회원 견적 캡쳐
export const captureBuilderRequest = async (option) => {
  try {
    const payload = await request.post(`${BUILDER_URL}/capture`, option);
    return payload
  } catch (err) {
    return err;
  }
}

// 회원 견적 캡쳐
export const captureBuilderForUserRequest = async (option) => {
  try {
    option.kafka = false;
    option.capture = true;
    const payload = await axiosAuth.post(`${BUILDER_URL}/capture`, option);
    return payload
  } catch (err) {
    return err;
  }
}

// 회원 견적 저장
export const saveRecommendBuilder = async (option) => {
  try {
    const payload = await axiosAuth.post(`${BUILDER_URL}`, option);
    return payload
  } catch (err) {
    return err;
  }
}
