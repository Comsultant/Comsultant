import { getProductInfoRequest, getProductPriceRequest } from "@/services/productService";
import React, { useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import { useState } from "react";
import style from "@/styles/ProductInfo.module.scss"
import { HeartOutlined, HeartFilled } from "@ant-design/icons";
import ProductInfoDetail from "./ProductInfoDetail";
import { Image, message } from "antd";
import ProductPriceChart from "./ProductPriceChart";
import { deleteWishRequest, postWishRequest } from "@/services/wishService.js";
import { getAllBuilderRequest, postBuilderRequest } from "@/services/builderService";
import { useSelector } from "react-redux";

const ProductInfo = () => {
  const [param, setParam] = useSearchParams();
  const [data, setData] = useState();
  const [period, setPeriod] = useState(9);
  const [priceData, setPriceData] = useState([]);
  const [builderList, setBuilderList] = useState([]);
  const [currBuilder, setCurrBuilder] = useState(-1);
  const [isWishClicked, setIsWishClicked] = useState(false);

  const idx = param.get('idx');
  const type = param.get('type');

  const isLogin = useSelector(state => state.account.isLogin);

  const onWishClicked = async(productIdx) => {
    const result = await postWishRequest(productIdx);
    message.success("찜 목록에 추가되었습니다.");
    setIsWishClicked(true);
  }
  
  const onWishCancelClicked = async(productIdx) => {
    const result = await deleteWishRequest(productIdx);
    message.error("찜 목록에서 제거되었습니다.")
    setIsWishClicked(false);
  }

  const onBuilderSelectChange = (e) => {
    setCurrBuilder(e.target.value);
  }

  const onBuilderAddClicked = async() => {
    // 부품 선택 견적에 추가
    if(currBuilder == -1){
      message.error("견적을 선택해주세요");
      return;
    }
    let targetIdx = -1;
    builderList.map((builder, i)=>{
      if(builder.myBuilderDto.idx == currBuilder){
        targetIdx = i;
      }
    })
    let originArr = builderList[targetIdx]?.builderProductDetailDtos;
    let flag = false;
    // 이미 해당 부품이 있는 경우 cnt + 1 하기
    originArr.map((product, i) => {
      if(product.productIdx == idx){
        flag = true;
        originArr[i] = {...originArr[i], cnt: originArr[i].cnt + 1};
      }
    })
    // 없는 부품이었던 경우
    if(!flag){
      originArr = [...originArr, {productIdx: idx, cnt : 1}];
    }
    const dataToSubmit = {
      idx: currBuilder,
      builderProducts: originArr,
    }

    const result = await postBuilderRequest(dataToSubmit);
    if(result?.data?.message === "success"){
      message.success("추가되었습니다!");
    }
  }

  useEffect(() => {
    const fetchData = async () => {
      const payload = await getProductInfoRequest(idx);
      if (payload?.data?.message === "success") {
        setData(payload?.data?.responseDto);
      }
    }
    fetchData();

    const fetchPriceData = async () => {
      const dataToSubmit = {
        idx,
        period,
      }
      const payload = await getProductPriceRequest(dataToSubmit);
      if(payload?.data?.message === "success"){
        const priceArr = payload.data.responseDto.date;
        const newArr = [];
        priceArr?.map((curr) => {
          const item = {"x": (curr[0]+"").replace(/(\d{4})(\d{2})(\d{2})/g, '$2.$3'), "y": curr[1]};
          newArr.push(item);
        })
        setPriceData(newArr);
      }
    }
    fetchPriceData();

    const fetchBuilderData = async() => {
      const result = await getAllBuilderRequest();
      if(result?.data?.message === "success"){
        const data = result?.data?.responseDto?.myBuilderDetailDtoList;
        setBuilderList(data);
      }
    }

    fetchBuilderData();
  },[])
  
  useEffect(() => {
    const fetchPriceData = async () => {
      const dataToSubmit = {
        idx,
        period,
      }
      const payload = await getProductPriceRequest(dataToSubmit);
      if(payload?.data?.message === "success"){
        const priceArr = payload.data.responseDto.date;
        const newArr = [];
        priceArr?.map((curr) => {
          const item = {"x": (curr[0]+"").replace(/(\d{4})(\d{2})(\d{2})/g, '$2.$3'), "y": curr[1]};
          newArr.push(item);
        })
        setPriceData(newArr);
      }
    }
    fetchPriceData();
  },[period])

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
              <ProductPriceChart priceData={priceData} />
            </div>
            <div className={style['right-box']}>
              <div>
                <span className={style['price']}>{(data?.price)?.toLocaleString()} 원</span>
              </div>
              {isLogin ? 
              <>
                <div className={style['right-box-mid']}>
                <span className={style['shopping-button']}
                onClick={()=>
                window.open(`https://search.shopping.naver.com/search/all?query=${data?.name}`)}>
                  네이버 쇼핑검색
                  </span> 
                  {!data?.wish && !isWishClicked ? <HeartOutlined 
                    style={{
                      fontSize: '40px',
                      cursor: 'pointer',
                      marginLeft: '20px',
                    }}
                    onClick={() => onWishClicked(data?.idx)}
                    /> : <HeartFilled 
                        style={{
                          fontSize: '40px',
                          color: '#FF4300',
                          cursor: 'pointer',
                          marginLeft: '20px',
                        }}
                  onClick={() => onWishCancelClicked(data?.idx)} 
                />}                  
                </div>
              <div className={style['builder-select-box']}>
                <select onChange={onBuilderSelectChange}>
                  <option value={-1}>견적 선택</option>
                  {builderList.map((builder, idx) => {
                    return(
                      <option key={idx} value={builder.myBuilderDto.idx}>
                        {builder.myBuilderDto.name}
                      </option>
                        );
                    })
                  }
                </select>
                <button onClick={onBuilderAddClicked}>추가</button>
              </div>
              </>
              :
              null}
              
            </div>
          </div>
          
        </div>
      </div>

      <div className={style['bottom-info']}>
        <ProductInfoDetail idx={idx} product={data} type={type} period={period} setPeriod={setPeriod} />
      </div>
    </div>
  );
}

export default ProductInfo;