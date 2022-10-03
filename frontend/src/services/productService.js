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

// 상품 카테고리별 목록 가져오기
export const getProductRequest = async (dataToSubmit) => {
  try {
    const page = dataToSubmit.page;
    const desc = dataToSubmit.desc;
    const type = dataToSubmit.type;
    const payload = await request.post(`${PRODUCT_URL}/${type}?page=${page}&desc=${desc}`, dataToSubmit.body);
    return payload;
  } catch (err) {
    return err;
  }
};

// 부품 상세정보 조회
export const getProductInfoRequest = async (dataToSubmit) => {
  try {
    const payload = await request.get(`${PRODUCT_URL}/${dataToSubmit}`);
    return payload;
  } catch (err) {
    return err;
  }
};

// 부품 댓글 조회
export const getProductCommentRequest = async (dataToSubmit) => {
  const idx = dataToSubmit.idx;
  const page = dataToSubmit.page;
  const desc = dataToSubmit.desc;
  try {
    const payload = await request.get(`${PRODUCT_URL}/comment/${idx}?desc=${desc}&page=${page}`);
    return payload;
  } catch (err) {
    return err;
  }
};