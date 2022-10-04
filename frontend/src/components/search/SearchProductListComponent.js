import React, { useEffect } from "react";
import style from "@/styles/SearchProductListComponent.module.scss"
import { Pagination } from "antd";
import ProductNumMapper from "@/tools/ProductNumMapper";
import { getProductRequest } from "@/services/productService";
import ProductDetail from "./ProductDetail";
import { HeartOutlined } from "@ant-design/icons";
import { postBuilderRequest } from "@/services/builderService.js"


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
  }
) => {
  
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
    switch (currTypeTab) {
      case '0':
        setCpuList([...cpuList, { productIdx, price, productName, cnt: 1 }]);
        break;
      case '1':
        setMbList([...mbList, { productIdx, price, productName, cnt: 1 }]);
        break;
      case '2':
        setVgaList([...vgaList, { productIdx, price, productName, cnt: 1 }]);
        break;
      case '3':
        setRamList([...ramList, { productIdx, price, productName, cnt: 1 }]);
        break;
      case '4':
        setPowerList([...powerList, { productIdx, price, productName, cnt: 1 }]);
        break;
      case '5':
        setSsdList([...ssdList, { productIdx, price, productName, cnt: 1 }]);
        break;
      case '6':
        setHddList([...hddList, { productIdx, price, productName, cnt: 1 }]);
        break;
      case '7':
        setCaseList([...caseList, { productIdx, price, productName, cnt: 1 }]);
        break;
      case '8':
        setCoolerList([...coolerList, { productIdx, price, productName, cnt: 1 }]);
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
                      <button className={style['put-button']} onClick={() => onPutBuilder(product.idx, product.price, product.name)}>견적담기</button>
                    </div>
                    <div>
                      <HeartOutlined/>
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

export default SearchProductListComponent;
