import React, { useEffect } from "react";
import style from "@/styles/ProductListComponent.module.scss"
import { Pagination } from "antd";
import { getCpuRequest } from "@/services/productService";

const SearchProductListComponent = (
  {
    productList, setProductList, currPage, setCurrPage, currDescNum, setCurrDescNum, filter
  }
) => {
  

  const getProductList = async(dataToSubmit) => {
    const result = await getCpuRequest(dataToSubmit);
    return result;
  }

  useEffect(() => {
    const dataToSubmit = {
      page: currPage,
      body: {
        "corp": ["인텔"],
        "intelCpu": ["펜티엄", "셀러론"],
        "amdCpu": ["0"],
        "socket": ["인텔(소켓775)", "인텔(소켓1155)"],
        "core": ["2코어"],
        "name": "4",
        "price": []
      },
    };
    const result = getProductList(dataToSubmit);
    // console.log(result?.payload);
    if (result !== undefined) {
      // setProductList(result);
    }
  },[currPage, currDescNum])
  return(
    <>
      {productList.map((product, idx)=>{
            return(
              <div key={idx} className={style['product-item']}>
                <div className={style['left-item']}>
                  <div className={style['product-img']}>
                    <img src={product.img} alt=""/>
                  </div>
                  <div>
                    <div>
                      <span>{product.name}</span>
                      <span>{product.price}</span>
                    </div>
                    <div>
                      <span>{product.detail}</span>
                    </div>
                  </div> 
                </div>
                <div className={style['product-detail-button']}>
                  <button>상세보기</button>
                </div>
              </div>
            );
          })}
          <div className={style['pagination']}>
            <Pagination 
            size="small"
            total={500}
            showTotal={false}
            showQuickJumper={false}
            showSizeChanger={false}
            onChange={(page)=>setCurrPage(page)}
            />
          </div>
    </>
  );
};

export default SearchProductListComponent;
