import { request, axiosAuth } from "./axios";

const BUILDER_URL = "/api/builder";

//회원 견적 전체 조회
export const getPageBuilderRequest = async (page) => {
  try {
    const payload = await axiosAuth.get(`${BUILDER_URL}/?page=${page}`);
    return payload;
  } catch (err) {
    return err;
  }
};

//회원 견적 전체 조회
export const getAllBuilderRequest = async () => {
  try {
    const payload = await axiosAuth.get(`${BUILDER_URL}/all`);
    return payload;
  } catch (err) {
    return err;
  }
};

//회원 견적 생성
export const postBuilderRequest = async (dataToSubmit) => {
  try {
    const payload = await axiosAuth.post(`${BUILDER_URL}`, dataToSubmit);
    return payload;
  } catch (err) {
    return err;
  }
};

//회원 견적 상세 조회
export const getBuilderDetailRequest = async (dataToSubmit) => {
  try {
    const payload = await axiosAuth.get(`${BUILDER_URL}/${dataToSubmit}`);
    return payload;
  } catch (err) {
    return err;
  }
};

//회원 견적 삭제
export const deleteBuilderRequest = async (dataToSubmit) => {
  try {
    const payload = await axiosAuth.delete(`${BUILDER_URL}/${dataToSubmit}`);
    return payload;
  } catch (err) {
    return err;
  }
};

// 부품 호환성 체크
export const postBuilderCheckRequest = async (dataToSubmit) => {
  try {
    const payload = await request.post(`${BUILDER_URL}/check`, dataToSubmit);
    return payload;
  } catch (err) {
    return err;
  }
};