import React, { useEffect } from "react";
import style from "@/styles/SearchProductListComponent.module.scss"
import styleD from "@/styles/ProductDetail.module.scss"
import { Pagination } from "antd";
import { getAccountCommentRequest } from "@/services/CommentService";

const MyCommentList = (
  {
    commentDetailDtoList, setCommentDetailDtoList, currPage, setCurrPage, currDesc, setCurrDesc, totalPage, setTotalPage
  }
) => {

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
                  className={style['left-item']}
                  onClick={() => { window.open(`/product/info?idx=${comment.commentDto.productIdx}&type=${comment.category}`) }}
                >
                  <div className={style['product-img']}>
                    <img src={`https://j7a602.p.ssafy.io/static/images/${comment.commentDto.productIdx}/0.jpg`} alt=""/>
                  </div>
                  <div>
                    <div className={style['product-name']}>
                      <span>{comment.productName}</span>
                    </div>
                    <div className={styleD['product-detail']}>
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
                      <button className={style['put-button']} onClick={() => { window.open(`/product/info?idx=${comment.commentDto.productIdx}&type=${comment.category}`) }}>상세보기</button>
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
