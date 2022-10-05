import { getProductCommentRequest } from "@/services/productService";
import React, { useEffect, useState } from "react";
import { Avatar, Button, Comment, Form, Input, List } from "antd";
import moment from "moment";
import style from "@/styles/ProductComment.module.scss";
import { useSelector } from "react-redux";
import { deleteCommentRequest, postCommentRequest, putCommentRequest } from "@/services/CommentService";
import { Empty } from "antd";
import { UserOutlined } from "@ant-design/icons";

const { TextArea } = Input;

const CommentList = ({ comments }) => (
  <List
    dataSource={comments}
    header={`${comments.length} ${comments.length > 1 ? "replies" : "reply"}`}
    itemLayout="horizontal"
    renderItem={props => <Comment {...props} />}
  />
);

const Editor = ({ onChange, onSubmit, submitting, content }) => (
  <>
    <Form.Item>
      <TextArea rows={4} onChange={onChange} value={content} />
    </Form.Item>
    <Form.Item>
      <Button
        htmlType="submit"
        loading={submitting}
        onClick={onSubmit}
        type="primary"
      >
        작성
      </Button>
    </Form.Item>
  </>
);

const ProductComment = ({ idx, type }) => {
  const [comments, setComments] = useState([]);
  const [currPage, setCurrPage] = useState(1);
  const [totalPage, setTotalPage] = useState(1);
  const [submitting, setSubmitting] = useState(false);
  const [content, setContent] = useState("");
  const [editContent, setEditContent] = useState("");
  const [editIdx, setEditIdx] = useState(-1);
  const [editCommentIdx, setEditCommentIdx] = useState(-1);

  const isLogin = useSelector(state => state.account.isLogin);
  const nickname = useSelector(state => state.account.nickname);

  const handleSubmit = () => {
    if (!content) return;
    setSubmitting(true);
    setTimeout(() => {
      setSubmitting(false);
      setContent("");
      setComments([
        ...comments,
        {
          avatar: "https://joeschmoe.io/api/v1/random",
          content: <p>{content}</p>,
        },
      ]);
    }, 500);
  };

  const handleChange = e => {
    setContent(e.target.value);
  };

  const onEditClicked = (i, value, commentIdx) => {
    setEditContent(value);
    setEditIdx(i);
    setEditCommentIdx(commentIdx);
  }

  const onDeleteClicked = (idx, commentIdx) => {
    setComments(comments.filter((curr, i) => i != idx));
    const dataToSubmit = {
      idx: commentIdx,
    }
    deleteCommentRequest(dataToSubmit);
  }

  const onHandleSubmit = async () => {
    if (!content) return;

    const dataToSubmit = {
      idx,
      content: content.trim(),
    };
    const result = await postCommentRequest(dataToSubmit);
    if (result?.data?.message === "success") {
      const newObj = {
      commentDto: {
        idx: result?.data?.responseDto?.idx,
        nickName: nickname,
        content: content.trim(),
      },
      productImg: 0,
      productName: null,
    }
    setComments([newObj, ...comments]);
    setContent("");
    } else {
      return;
    }

    
  };

  const onHandleEditSubmit = () => {
    const dataToSubmit = {
      idx: editCommentIdx,
      content: editContent.trim()
    };
    putCommentRequest(dataToSubmit);
    let copyArr = [...comments];
    if (editIdx != -1) {
      copyArr[editIdx] = { ...copyArr[editIdx], commentDto : { ...copyArr[editIdx].commentDto, content: editContent.trim() } };
    }
    setComments([...copyArr]);
    setEditIdx(-1);
    setEditContent('');
  }

  const elapsedTime = (date) => {
    const start = new Date(date);
    const end = new Date(); // 현재 날짜
    
    const offset = start.getTimezoneOffset() / 60;
    const hours = start.getHours();
    start.setHours(hours - offset);

    const diff = (end - start); // 경과 시간
    const times = [
      {time: "분", milliSeconds: 1000 * 60},
      {time: "시간", milliSeconds: 1000 * 60 * 60},
      {time: "일", milliSeconds: 1000 * 60 * 60 * 24},
      {time: "개월", milliSeconds: 1000 * 60 * 60 * 24 * 30},
      {time: "년", milliSeconds: 1000 * 60 * 60 * 24 * 365},
    ].reverse();
    
    // 년 단위부터 알맞는 단위 찾기
    for (const value of times) {
      const betweenTime = Math.floor(diff / value.milliSeconds);
      
      // 큰 단위는 0보다 작은 소수 단위 나옴
      if (betweenTime > 0) {
        return `${betweenTime}${value.time} 전`;
      }
    }
    
    // 모든 단위가 맞지 않을 시
    return "방금 전";
  }

  useEffect(() => {
    const fetchData = async () => {
      const dataToSubmit = {
        idx,
        page: currPage,
        desc: true,
      };
      const payload = await getProductCommentRequest(dataToSubmit);
      if (payload?.data?.message === "success") {
        setTotalPage(payload?.data?.responseDto?.totalPage);
        setComments(payload?.data?.responseDto?.commentDetailDtoList);
      }
      return payload;
    };

    fetchData();
  }, []);

  return (
    <div className={style.container}>
      {comments.length > 0 ? (
        <>
          <div className={style['replies']}>
          {comments.length} replies
          </div>
          {editIdx != -1 ?
                  <div className={style['comment-input-box']}>
  
                    <TextArea
                      className={style["comment-input"]}
                      value={editContent}
                      onChange={e => setEditContent(e.target.value)}
                    />
                    <button
                      className={style["submit-button"]}
                      onSubmit={onHandleEditSubmit}
                      onClick={onHandleEditSubmit}
                    >
                      수정
                    </button>
                  </div>
                  : null}
          {comments.map((comment, idx) => {
            return (
              <div key={idx}>
              {editIdx != idx ?                 
                <div className={style['comment-box']}>
                <div className={style['avatar']}>
                  <Avatar size={32} icon={<UserOutlined />}/>
                </div>
                <div style={{ width: '100%' }}>
                  <div className={style['nickname']}>{comment.commentDto.nickName}</div>
                  <div className={style['content']}>{comment.commentDto.content}</div>
                  <div className={style['time']}>{elapsedTime(comment.commentDto.createDate)}
                    {isLogin && nickname == comment.commentDto.nickName ?
                      <div>
                        <span className={style['edit-button']} onClick={()=>onEditClicked(idx, comment.commentDto.content, comment.commentDto.idx)}>수정</span>
                        <span> / </span>
                        <span className={style['delete-button']} onClick={()=>onDeleteClicked(idx, comment.commentDto.idx)}>삭제</span>
                      </div>
                      : null}
                    
                  </div>
                </div>
              </div>                
               : null}
              
              </div>
            );
          })}
        </>
      ) : (
        <Empty />
      )}

      {isLogin ? (
        <>
          <div className={style['comment-input-box']}>
            
            <TextArea
              className={style["comment-input"]}
              value={content}
              onChange={e => setContent(e.target.value)}
            />
            <button
              className={style["submit-button"]}
              onSubmit={onHandleSubmit}
              onClick={onHandleSubmit}
            >
              작성
            </button>
          </div>
        </>
      ) : null}
    </div>
  );
};

export default ProductComment;
