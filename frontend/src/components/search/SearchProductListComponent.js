import React, { useEffect, useState } from "react";
import style from "@/styles/SearchProductListComponent.module.scss"
import { message, Pagination } from "antd";
import ProductNumMapper from "@/tools/ProductNumMapper";
import { getProductRequest } from "@/services/productService";
import ProductDetail from "./ProductDetail";
import { HeartOutlined, HeartFilled } from "@ant-design/icons";
import { postBuilderRequest } from "@/services/builderService.js";
import { deleteWishRequest, postWishRequest } from "@/services/wishService.js";
import { useSelector } from "react-redux";
import Loading from "../Loading";

const SearchProductListComponent = (
  {
    productList, setProductList, currPage, setCurrPage, currDescNum, setCurrDescNum, filterBody, setFilterBody, totalPage, setTotalPage, currTypeTab, currBuilder, setCurrBuilder, currBuilerIdx,
    cpuList,
    setCpuList,
    ramList,
    setRamList,
    hddList,
    setHddList,
    ssdList,
    setSsdList,
    powerList,
    setPowerList,
    coolerList,
    setCoolerList,
    caseList,
    setCaseList,
    mbList,
    setMbList,
    vgaList,
    setVgaList,
    activeKey,
    setActiveKey,
    tabOpen,
    setTabOpen,
  }
) => {
  
  const isLogin = useSelector(state => state.account.isLogin);
  const [isLoading, setIsLoading] = useState(true);

  const onWishClicked = async(productIdx) => {
    const result = await postWishRequest(productIdx);
    message.success("찜 목록에 추가되었습니다.");
    let idx = -1;
    productList.map((product, i)=> {
      if(product.idx == productIdx){
        idx = i;
      }
    });
    const newList = [...productList];
    newList[idx] = {...productList[idx], wish: true};
    setProductList(newList);
  }
  
  const onWishCancelClicked = async(productIdx) => {
    const result = await deleteWishRequest(productIdx);
    message.error("찜 목록에서 제거되었습니다.");
    let idx = -1;
    productList.map((product, i)=> {
      if(product.idx == productIdx){
        idx = i;
      }
    });
    const newList = [...productList];
    newList[idx] = {...productList[idx], wish: false};
    setProductList(newList);
  }

  useEffect(() => {
    const builderProducts = [];

    cpuList.map((product, idx) => {
      const item = { productIdx: product.productIdx, cnt: product.cnt };
      builderProducts.push(item);
    })
    ramList.map((product, idx) => {
      const item = { productIdx: product.productIdx, cnt: product.cnt };
      builderProducts.push(item);
    })
    hddList.map((product, idx) => {
      const item = { productIdx: product.productIdx, cnt: product.cnt };
      builderProducts.push(item);
    })
    ssdList.map((product, idx) => {
      const item = { productIdx: product.productIdx, cnt: product.cnt };
      builderProducts.push(item);
    })
    powerList.map((product, idx) => {
      const item = { productIdx: product.productIdx, cnt: product.cnt };
      builderProducts.push(item);
    })
    coolerList.map((product, idx) => {
      const item = { productIdx: product.productIdx, cnt: product.cnt };
      builderProducts.push(item);
    })
    caseList.map((product, idx) => {
      const item = { productIdx: product.productIdx, cnt: product.cnt };
      builderProducts.push(item);
    })
    mbList.map((product, idx) => {
      const item = { productIdx: product.productIdx, cnt: product.cnt };
      builderProducts.push(item);
    })
    vgaList.map((product, idx) => {
      const item = { productIdx: product.productIdx, cnt: product.cnt };
      builderProducts.push(item);
    })

    
    
    const postData = async () => {
      const idx = currBuilder?.idx;
      if (idx != undefined) {
        const dataToSubmit = {
          idx,
          builderProducts,
        }
        const result = await postBuilderRequest(dataToSubmit);
      }
    }
    postData();

  },[cpuList, ramList, hddList, ssdList, powerList, coolerList, caseList, mbList, vgaList])


  const onPutBuilder = (productIdx, price, productName) => {
    setTabOpen(true);
    let idx = -1;
    switch (currTypeTab) {
      case '0':
        setActiveKey('1');
        cpuList.map((product, i) => {
          if(product.productIdx == productIdx){
            idx = i;
          }
        });
        if(idx != -1){
          const newList = [...cpuList];
          newList[idx] = {...newList[idx], cnt: cpuList[idx].cnt + 1};
          setCpuList(newList)
        }else{
          setCpuList([...cpuList, { productIdx, price, productName, cnt: 1 }]);
        }
        break;
      case '1':
        setActiveKey('2');
        mbList.map((product, i) => {
          if(product.productIdx == productIdx){
            idx = i;
          }
        });
        if(idx != -1){
          const newList = [...mbList];
          newList[idx] = {...newList[idx], cnt: mbList[idx].cnt + 1};
          setMbList(newList)
        }else{
          setMbList([...mbList, { productIdx, price, productName, cnt: 1 }]);
        }
        break;
      case '2':
        setActiveKey('3');
        vgaList.map((product, i) => {
          if(product.productIdx == productIdx){
            idx = i;
          }
        });
        if(idx != -1){
          const newList = [...vgaList];
          newList[idx] = {...newList[idx], cnt: vgaList[idx].cnt + 1};
          setVgaList(newList)
        }else{
          setVgaList([...vgaList, { productIdx, price, productName, cnt: 1 }]);
        }
        break;
      case '3':
        ramList.map((product, i) => {
          if(product.productIdx == productIdx){
            idx = i;
          }
        });
        if(idx != -1){
          const newList = [...ramList];
          newList[idx] = {...newList[idx], cnt: ramList[idx].cnt + 1};
          setRamList(newList)
        }else{
          setRamList([...ramList, { productIdx, price, productName, cnt: 1 }]);
        }
        break;
      case '4':
        powerList.map((product, i) => {
          if(product.productIdx == productIdx){
            idx = i;
          }
        });
        if(idx != -1){
          const newList = [...powerList];
          newList[idx] = {...newList[idx], cnt: powerList[idx].cnt + 1};
          setPowerList(newList)
        }else{
          setPowerList([...powerList, { productIdx, price, productName, cnt: 1 }]);
        }
        break;
      case '5':
        ssdList.map((product, i) => {
          if(product.productIdx == productIdx){
            idx = i;
          }
        });
        if(idx != -1){
          const newList = [...ssdList];
          newList[idx] = {...newList[idx], cnt: ssdList[idx].cnt + 1};
          setSsdList(newList)
        }else{
          setSsdList([...ssdList, { productIdx, price, productName, cnt: 1 }]);
        }
        break;
      case '6':
        hddList.map((product, i) => {
          if(product.productIdx == productIdx){
            idx = i;
          }
        });
        if(idx != -1){
          const newList = [...hddList];
          newList[idx] = {...newList[idx], cnt: hddList[idx].cnt + 1};
          setHddList(newList)
        }else{
          setHddList([...hddList, { productIdx, price, productName, cnt: 1 }]);
        }
        break;
      case '7':
        caseList.map((product, i) => {
          if(product.productIdx == productIdx){
            idx = i;
          }
        });
        if(idx != -1){
          const newList = [...caseList];
          newList[idx] = {...newList[idx], cnt: caseList[idx].cnt + 1};
          setCaseList(newList)
        }else{
          setCaseList([...caseList, { productIdx, price, productName, cnt: 1 }]);
        }
        break;
      case '8':
        coolerList.map((product, i) => {
          if(product.productIdx == productIdx){
            idx = i;
          }
        });
        if(idx != -1){
          const newList = [...coolerList];
          newList[idx] = {...newList[idx], cnt: coolerList[idx].cnt + 1};
          setCoolerList(newList)
        }else{
          setCoolerList([...coolerList, { productIdx, price, productName, cnt: 1 }]);
        }
        break;
        }
  } 

  useEffect(() => {
    
    let type = ProductNumMapper[currTypeTab];
    const dataToSubmit = {
      page: 1,
      desc: currDescNum,
      type,
      body: filterBody
    }
    // debounceFunc(dataToSubmit, getProductRequest);
    const fetchData = async () => {
      setIsLoading(true);
      const result = await getProductRequest(dataToSubmit);
      if (result?.data?.message === "success") {
        setTotalPage(result?.data?.responseDto?.totalPage);
        setProductList(result?.data?.responseDto?.productDtoList);
        setCurrPage(1);
      }
      setIsLoading(false);
    }
  
    const timer = setTimeout(() => {
      fetchData();
    }, 100);

    return () => {
      clearTimeout(timer); // 이 전의 timer를 clear합니다.
    };

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

  useEffect(()=> {
    //회원 wishList 불러오기
    console.log("productList changed!");
  },[productList])

  return(
    <>
      {productList?.map((product, idx)=>{
            return(
              <div key={idx} className={style['product-item']}>
                <div
                  className={tabOpen ? style['left-item-tab-open'] : style['left-item-tab-close']}
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
                <div className={tabOpen ? style['right-item-tab-open'] : style['right-item-tab-close']}>
                  <div>
                    <span className={style.price}>{product.price == 0 ? `재고없음 ` : `${product.price.toLocaleString()} 원`}</span>
                  </div>
                  <div className={style['right-button-box']}>
                    <div>
                      <button className={style['put-button']} onClick={() => onPutBuilder(product.idx, product.price, product.name)}>견적담기</button>
                    </div>
                    {isLogin ?
                    !product.wish ? 
                    <div>
                          <HeartOutlined onClick={() => onWishClicked(product.idx)} style={{ fontSize: '22px' }} />
                    </div>
                    : 
                    <div>
                      <HeartFilled onClick={() => onWishCancelClicked(product.idx)} style={{ fontSize: '22px', color: '#FF4300'}} />
                    </div> 
                    : 
                    null}
                    
                  </div>
                </div>
              </div>
            );
      })
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

export default SearchProductListComponent;
