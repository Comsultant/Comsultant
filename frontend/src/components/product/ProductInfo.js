import { getProductInfoRequest } from "@/services/productService";
import React, { useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import { useState } from "react";
import style from "@/styles/ProductInfo.module.scss"
import { HeartOutlined, HeartFilled } from "@ant-design/icons";
import ProductInfoDetail from "./ProductInfoDetail";
import { Image } from "antd";

const ProductInfo = () => {
  const [param, setParam] = useSearchParams();
  const [data, setData] = useState();
  const idx = param.get('idx');
  const type = param.get('type');

  useEffect(() => {
    const fetchData = async () => {
      const payload = await getProductInfoRequest(idx);
      if (payload?.data?.message === "success") {
        setData(payload?.data?.responseDto);
      }
    }
    fetchData();
  },[])
  
  useEffect(() => {
    // console.log(data);
  },[data])
  return (
    <div className={style['container']}>
      <div className={style['top']}>
        <div className={style['top-img']}>
          <Image
            width={300}
            src={`https://j7a602.p.ssafy.io/static/images/${idx}/0.jpg`}
          />
        </div>
        <div className={style['top-info']}>
          <span className={style['title']}>{data?.name}</span>
          <div className={style['product-info']}>
            <div className={style['price-graph']}> 
              시세정보
            </div>
            <div>
              <span>200,000원</span>
              <div><span onClick={()=>window.open(`https://search.shopping.naver.com/search/all?query=${data?.name}`)}>네이버 쇼핑검색</span> <HeartOutlined /></div>
              <div>
                <select>
                  <option>견적에 추가하기</option>
                </select>
              </div>
            </div>
          </div>
          
        </div>
      </div>

      <div className={style['bottom-info']}>
        <ProductInfoDetail idx={idx} product={data} type={type} />
      </div>
    </div>
  );
}

export default ProductInfo;