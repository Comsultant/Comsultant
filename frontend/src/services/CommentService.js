import { request, axiosAuth } from "./axios";

const COMMENT_URL = "/api/comment";

//본인 댓글 조회
export const getAccountCommentRequest = async (desc, page) => {
  try {
    const payload = await axiosAuth.get(`${COMMENT_URL}/?desc=${desc}&page=${page}`);
    return payload;
  } catch (err) {
    return err;
  }
};

// 댓글 작성
export const postCommentRequest = async (dataToSubmit) => {
  const idx = dataToSubmit.idx;
  const content = {
    content: dataToSubmit.content,
  }
  try {
    const payload = await axiosAuth.post(`${COMMENT_URL}/${idx}`, content);
    return payload;
  } catch (err) {
    return err;
  }
};

// 댓글 삭제
export const deleteCommentRequest = async (dataToSubmit) => {
  const idx = dataToSubmit.idx;
  try {
    const payload = await axiosAuth.delete(`${COMMENT_URL}/${idx}`);
    return payload;
  } catch (err) {
    return err;
  }
};

//댓글 수정
export const putCommentRequest = async (dataToSubmit) => {
  const idx = dataToSubmit.idx;
  const content = {
    content: dataToSubmit.content,
  }
  try {
    const payload = await axiosAuth.put(`${COMMENT_URL}/${idx}`, content);
    return payload;
  } catch (err) {
    return err;
  }
};