import React, { useEffect } from "react";
import style from "@/styles/MyPage.module.scss"
import { Pagination } from "antd";
import { getAccountCommentRequest } from "@/services/CommentService";

const MyCommentList = (
  {
    commentDetailDtoList, setCommentDetailDtoList, currPage, setCurrPage, currDesc, setCurrDesc, totalPage, setTotalPage
  }
) => {

  const setCategory = (val) => {
    if (val == 1) {
      return 0;
    } else if (val == 2) {
      return 3;
    } else if (val == 3) {
      return 6;
    } else if (val == 4) {
      return 5;
    } else if (val == 5) {
      return 4;
    } else if (val == 6) {
      return 8;
    } else if (val == 7) {
      return 7;
    } else if (val == 8) {
      return 1;
    } else if (val == 9) {
      return 2;
    }
  }

  useEffect(() => {
    const fetchData = async () => {
      const result = await getAccountCommentRequest(currDesc, 1);
      if (result?.data?.message === "success") {
        setTotalPage(result?.data?.responseDto?.totalPage);
        setCommentDetailDtoList(result?.data?.responseDto?.commentDetailDtoList);
      }
    }
    setCurrPage(1);
    fetchData();
  }, [currDesc])

  useEffect(() => {
    const fetchData = async () => {
      const result = await getAccountCommentRequest(currDesc, currPage);
      if (result?.data?.message === "success") {
        setTotalPage(result?.data?.responseDto?.totalPage);
        setCommentDetailDtoList(result?.data?.responseDto?.commentDetailDtoList);
      }
    }
    fetchData();
  }, [currDesc, currPage])

  return(
    <>
      {commentDetailDtoList !== undefined ? commentDetailDtoList.map((comment, idx)=>{
            return(
              <div key={idx} className={style['product-item']}>
                <div
                  className={style['left-item-tab-open']}
                  onClick={() => { window.open(`/product/info?idx=${comment.commentDto.productIdx}&type=${setCategory(comment.category)}`) }}
                >
                  <div className={style['product-img']}>
                    <img src={`https://j7a602.p.ssafy.io/static/images/${comment.commentDto.productIdx}/0.jpg`} alt=""/>
                  </div>
                  <div>
                    <div className={style['product-name']}>
                      <span>{comment.productName}</span>
                    </div>
                    <div className={style['product-detail']}>
                      <div>
                        내용 : {comment.commentDto.content}
                      </div>
                      <div>
                        작성일 {comment.commentDto.createDate.split('T')[0]}
                      </div>
                    </div>
                  </div> 
                </div>
                <div className={style['right-item']}>
                  <div className={style['right-button-box']}>
                    <div>
                      <button className={style['put-button']} onClick={() => { window.open(`/product/info?idx=${comment.commentDto.productIdx}&type=${setCategory(comment.category)}`) }}>상세보기</button>
                    </div>
                  </div>
                </div>
              </div>
            );
      }):null
        }
          <div className={style['pagination']}>
            <Pagination 
            size="small"
            current={currPage}
            total={totalPage*10}
            showTotal={false}
            showQuickJumper={false}
            showSizeChanger={false}
            onChange={(page)=>setCurrPage(page)}
            />
          </div>
    </>
  );
};

export default MyCommentList;
