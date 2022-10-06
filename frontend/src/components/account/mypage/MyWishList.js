import React, { useEffect } from "react";
// import style from "@/styles/MyPage.module.scss"
import style from "@/styles/MyWishList.module.scss"
import { Pagination } from "antd";
import { getWishListRequest } from "@/services/wishService";

const MyWishList = (
  {
    wishDtoList, setWishDtoList, currPage, setCurrPage, currDesc, setCurrDesc, totalPage, setTotalPage
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
              <div key={idx} className={style['product-item']} onClick={() => { window.open(`/product/info?idx=${wish.productIdx}&type=${setCategory(wish.category)}`) }}>
                <div className={style['product-img']}>
                  <img src={`https://j7a602.p.ssafy.io/static/images/${wish.productIdx}/0.jpg`} alt=""/>
                </div>
                <div style={{
                  paddingTop: "15px"
                }}>
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
