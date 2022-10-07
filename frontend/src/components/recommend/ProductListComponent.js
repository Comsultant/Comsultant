import React from "react";
import style from "@/styles/ProductListComponent.module.scss"
import { Pagination } from "antd";

const ProductListComponent = ({productList}) => {
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
            />
          </div>
    </>
  );
};

export default ProductListComponent;
