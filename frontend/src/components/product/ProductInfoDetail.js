import React from "react";
import { Tabs } from "antd";
import ProductComment from "./ProductComment";
import ProductDetailComponent from "./ProductInfoDetailComponent";
import "@/styles/ProductInfoDetail.scss"

const ProductInfoDetail = ({idx, product, type}) => {

  const items = [
    { label: '상세정보', key: 'detail', children: <ProductDetailComponent idx={idx} product={product} type={type} /> }, 
    { label: '댓글', key: 'comment', children: <ProductComment idx={idx} tpye={type} /> },
  ];

  return (
    <>
      <Tabs items={items} className={'tabs'} size={"large"}/>
    </>
  );
}

export default ProductInfoDetail;