import { request, axiosAuth } from "./axios";

const BUILDER_URL = "/api/builder";

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