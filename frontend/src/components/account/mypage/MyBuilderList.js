import React, { useState, useEffect } from "react";
import style from "@/styles/MyBuilderList.module.scss";
import { getPageBuilderRequest, deleteBuilderRequest } from "@/services/builderService";
import { Button, Pagination } from "antd";
import PriceFormatter from "@/tools/PriceFormatter";

const MyBuilderList = ({ myBuilderList, setMyBuilderList, currPage, setCurrPage, totalPage, setTotalPage }) => {

  const [category] = useState(["", "CPU", "RAM", "HDD", "SSD", "PSU", "COOLER", "CASE", "MAINBOARD", "VGA"]);
  
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

  const deleteItem = async (dataToSubmit) => {
    const c = confirm("삭제하시겠습니까?")
    if(!c) {
      return; 
    }

    const result = await deleteBuilderRequest(dataToSubmit);
    if (result?.data?.message === "success") {
      const result2 = await getPageBuilderRequest(currPage);
      if (result2?.data?.message === "success") {
        if (result2?.data?.responseDto?.myBuilderDetailDtoList?.length == 0) {
          setCurrPage(currPage - 1);
          const result3 = await getPageBuilderRequest(currPage);
          if (result3?.data?.message === "success") {
            setTotalPage(result3?.data?.responseDto?.totalPage);
            setMyBuilderList(result3?.data?.responseDto?.myBuilderDetailDtoList);
          }
        } else {
          setTotalPage(result2?.data?.responseDto?.totalPage);
          setMyBuilderList(result2?.data?.responseDto?.myBuilderDetailDtoList);
        }
      }
    }      
  }
  
  useEffect(() => {
    const fetchData = async () => {
      const result = await getPageBuilderRequest(1);
      if (result?.data?.message === "success") {
        setTotalPage(result?.data?.responseDto?.totalPage);
        setMyBuilderList(result?.data?.responseDto?.myBuilderDetailDtoList);
      }
    }
    setCurrPage(1);
    // fetchData();
  }, [])
  
  useEffect(() => {
    const fetchData = async () => {
      const result = await getPageBuilderRequest(currPage);
      if (result?.data?.message === "success") {
        setTotalPage(result?.data?.responseDto?.totalPage);
        setMyBuilderList(result?.data?.responseDto?.myBuilderDetailDtoList);
      }
    }
    fetchData();
  }, [currPage])

  return (
    <>
    {myBuilderList !== undefined ? myBuilderList.map((myBuilder, idx)=>{
      return (
        <div key={idx} className={style["builder-box"]}>
          <h2>{myBuilder.myBuilderDto.name}</h2>
          <table className={style["builder-table"]}>
            <colgroup>
              <col style={{ width: "15%" }} />
              <col style={{ width: "65%" }} />
              <col style={{ width: "15%" }} />
              <col style={{ width: "5%" }} />
            </colgroup>
            <thead className={style["builder-table-head"]}>
              <tr>
                <td>종류</td>
                <td>이름</td>
                <td>개당가격</td>
                <td>개수</td>
              </tr>
            </thead>
            <tbody className={style["builder-table-body"]}>
              <>
                {myBuilder.builderProductDetailDtos !== undefined ? myBuilder.builderProductDetailDtos.map((product, index) => {
                  return (
                    <tr key={index} className={style["builder-table-body-item"]} onClick={() => { window.open(`/product/info?idx=${product.productIdx}&type=${setCategory(product.category)}`) }}>
                      <td>{category[product.category]}</td>
                      <td>{product.productName}</td>
                      <td>{product.price!==0 ? PriceFormatter(product.price) : "-"}</td>
                      <td>{product.cnt}</td>
                    </tr>
                  );
                }) : null
                }
              </>
            </tbody>
          </table>
          <Button className={style["delete-button"]} onClick={()=>deleteItem(myBuilder.myBuilderDto.idx)}>삭제</Button>
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

export default MyBuilderList;
