import React, { useEffect } from "react";
import style from "@/styles/MyPage.module.scss"
import { Pagination } from "antd";
import { getWishListRequest } from "@/services/wishService";

const MyWishList = (
  {
    wishDtoList, setWishDtoList, currPage, setCurrPage, currDesc, setCurrDesc, totalPage, setTotalPage
  }
) => {

  useEffect(() => {
    const fetchData = async () => {
      const result = await getWishListRequest(currDesc, 1);
      if (result?.data?.message === "success") {
        setTotalPage(result?.data?.responseDto?.totalPage);
        setWishDtoList(result?.data?.responseDto?.wishDtoList);
      }
    }
    setCurrPage(1);
    fetchData();
  }, [currDesc])

  useEffect(() => {
    const fetchData = async () => {
      const result = await getWishListRequest(currDesc, currPage);
      if (result?.data?.message === "success") {
        setTotalPage(result?.data?.responseDto?.totalPage);
        setWishDtoList(result?.data?.responseDto?.wishDtoList);
      }
    }
    fetchData();
  }, [currDesc, currPage])

  return(
    <>
      {wishDtoList !== undefined ? wishDtoList.map((wish, idx)=>{
            return(
              <div key={idx} className={style['product-item']}>
                <div
                  className={style['left-item-tab-open']}
                  onClick={() => { window.open(`/product/info?idx=${wish.productIdx}&type=${wish.category}`) }}
                >
                  <div className={style['product-img']}>
                    <img src={`https://j7a602.p.ssafy.io/static/images/${wish.productIdx}/0.jpg`} alt=""/>
                  </div>
                  <div>
                    <div className={style['product-name']}>
                      <span>{wish.productName}</span>
                    </div>
                    <div className={style['product-detail']}>
                      <div>
                        찜한 날짜 {wish.createDate.split('T')[0]}
                      </div>
                    </div>
                  </div> 
                </div>
                <div className={style['right-item']}>
                  <div className={style['right-button-box']}>
                    <div>
                      <button className={style['put-button']} onClick={() => { window.open(`/product/info?idx=${wish.productIdx}&type=${wish.category}`) }}>상세보기</button>
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

export default MyWishList;
