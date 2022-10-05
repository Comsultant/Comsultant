import React, { useEffect } from "react";
import style from "@/styles/SearchProductListComponent.module.scss"
import { Pagination } from "antd";
import ProductNumMapper from "@/tools/ProductNumMapper";
import { getProductRequest } from "@/services/productService";
import ProductDetail from "@/components/search/ProductDetail";
import { HeartOutlined } from "@ant-design/icons";

const RecommendProductList = (
  {
    productList, setProductList, currPage, setCurrPage, currDescNum, setCurrDescNum, filterBody, setFilterBody, totalPage, setTotalPage, currTypeTab
    ,pickProduct, setPickProduct
  }
) => {
  
  useEffect(() => {
    let type = ProductNumMapper[currTypeTab];
    const dataToSubmit = {
      page: 1,
      desc: currDescNum,
      type,
      body: filterBody
    }
    const fetchData = async () => {
      const result = await getProductRequest(dataToSubmit);
      if (result?.data?.message === "success") {
        setTotalPage(result?.data?.responseDto?.totalPage);
        setProductList(result?.data?.responseDto?.productDtoList);
      }
    }
    setCurrPage(1);
    fetchData();
  }, [filterBody, currDescNum])

  useEffect(() => {
    let type = ProductNumMapper[currTypeTab];
    const dataToSubmit = {
      page: currPage,
      desc: currDescNum,
      type,
      body: filterBody
    }
    const fetchData = async () => {
      const result = await getProductRequest(dataToSubmit);
      if (result?.data?.message === "success") {
        setTotalPage(result?.data?.responseDto?.totalPage);
        setProductList(result?.data?.responseDto?.productDtoList);
      }
    }
    fetchData();
  }, [currPage, currDescNum])

  const pickingProduct = (e) => {
    const productIdx=e.target.getAttribute("pid");
    const pruductName = e.target.getAttribute("pname")
    const productPrice = e.target.getAttribute("price") 
    console.log(pickProduct)
    setPickProduct({
      ...pickProduct, id: productIdx, name: pruductName, price: productPrice
    })
  }

  return(
    <>
      {productList !== undefined ? productList.map((product, idx)=>{
            return(
              <div key={idx} className={style['product-item']}>
                <div
                  className={style['left-item']}
                  onClick={() => { window.open(`/product/info?idx=${product.idx}&type=${currTypeTab}`) }}
                >
                  <div className={style['product-img']}>
                    <img src={`https://j7a602.p.ssafy.io/static/images/${product.idx}/0.jpg`} alt=""/>
                  </div>
                  <div>
                    <div className={style['product-name']}>
                      <span>{product.name}</span>
                    </div>
                    <div>
                      <ProductDetail key={idx} currTypeTab={currTypeTab} product={product} />                    
                    </div>
                  </div> 
                </div>
                <div className={style['right-item']}>
                  <div>
                    <span className={style.price}>{product.price.toLocaleString()} 원</span>
                  </div>
                  <div className={style['right-button-box']}>
                    <div>
                      <button onClick={pickingProduct} pid={product.idx} pname={product.name} price={product.price}>선택하기</button>
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

export default RecommendProductList;
