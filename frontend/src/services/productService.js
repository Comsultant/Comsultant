import { request, axiosAuth } from "./axios";

/* 요청 URL*/
const PRODUCT_URL = "/api/product";

// 필터링 항목 조회
export const getProductFilterRequest = async (dataToSubmit) => {
  try {
    const payload = await request.get(`${PRODUCT_URL}/filter/${dataToSubmit}`);
    return payload;
  } catch (err) {
    return err;
  }
};

// cpu 상품 가져오기
export const getCpuRequest = async (dataToSubmit) => {
  try {
    const page = dataToSubmit.page;
    const payload = await request.post(`${PRODUCT_URL}/cpu?page=${page}`, dataToSubmit.body);
    return payload;
  } catch (err) {
    return err;
  }
};
