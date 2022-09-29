import React, { useState, useEffect } from "react";
import style from "@/styles/Search.module.scss";
import ProductFilter from "../recommend/ProductFilter";
import DescFilter from "../recommend/DescFilter";
import { SearchOutlined } from "@ant-design/icons";
import { Affix, Slider, Drawer, Tabs } from "antd";
import PriceFormatter from "@/tools/PriceFormatter";
import { PlusOutlined, CloseOutlined } from "@ant-design/icons";
import SearchProductListComponent from "./SearchProductListComponent";
import { getProductFilterRequest } from "@/services/productService";

const Search = () => {
  const defaultMaxPrice = 5000000;

  const [currTypeTab, setCurrTypeTab] = useState(0);
  const [filterList, setFilterList] = useState([]);
  const [filterDetailList, setFilterDetailList] = useState([]);
  const [currDescNum, setCurrDescNum] = useState(0);
  const [minPrice, setMinPrice] = useState(0);
  const [maxPrice, setMaxPrice] = useState(defaultMaxPrice);
  const [searchValue, setSearchValue] = useState("");
  const [productList, setProductList] = useState([]);
  const [currPage, setCurrPage] = useState(1);

  const [accountBuildList, setAccountBuildList] = useState([]);

  const [tabOpen, setTabOpen] = useState(false);

  const [filter, setFilter] = useState({});

  const toggleDrawer = () => {
    setTabOpen(curr => !curr);
  };

  const onClose = () => {
    setTabOpen(false);
  };


  const onPriceChange = e => {
    setMinPrice(e[0]);
    setMaxPrice(e[1]);
  };

  const onAddAccountBuildItem = () => {
    //프론트단에서 추가
    const newBuild = {
      "name": "PC"+parseInt(accountBuildList.length + 1),
      "builderProducts": []
    };
    setAccountBuildList([...accountBuildList, newBuild])
    // axios 요청으로 사용자 저장 견적 데이터 추가
  }

  const onRemoveAccountBuildItem = (idx) => {
    // 프론트단에서 제거
    setAccountBuildList(accountBuildList.filter((accountBuild, i) => i !== idx));
    // 실제 axios 요청으로 사용자 저장 견적 데이터 제거

  }

  const getProductFilterData = async (type) => {
    const result = await getProductFilterRequest(type);
    if (result?.data?.message === "success") {
      const data = result.data.responseDto;
      const keys = Object.keys(data);
      setFilterList(keys);
      const list = [];
      keys.map((key) => data[key] != "0" ? list.push(data[key]) : null);
      setFilterDetailList(list);

    }
  }

  useEffect(() => {
    switch (currTypeTab) {
      case 0:
        getProductFilterData("cpu");
        break;
      case 1:
        getProductFilterData("mainboard");
        break;
      case 2:
        break;
    }
    //필터링 데이터, 인기상품순 상품 데이터 받아오기 (임시로 상수로 설정함)
    // let filterData;
    // switch (currTypeTab) {
    //   case 0:
    //     filterData = getProductFilterData("cpu");
    //     console.log(filterData);
    //     break;
    //     default:
    //       break;
    // }
    // setFilterList(["제조회사", "코어 수"]);
    // setFilterDetailList([
    //   ["인텔", "AMD"],
    //   ["2코어", "4코어", "8코어"],
    // ]);
    // 사용자 저장 견적 데이터 가져오기 (임시로 상수로 설정함)
    setAccountBuildList([
      {
        "name" : "pc1",
        "builderProducts" : [
            {
                "productIdx" : 16756757,
                "cnt" : 1
            },
            {
                "productIdx" : 11790199,
                "cnt" : 2
            },
              {
                "productIdx" : 12397271,
                "cnt" : 1
            },
            {
                "productIdx" : 16331876,
                "cnt" : 1
            },
              {
                "productIdx" : 16178162,
                "cnt" : 1
            }
        ],
        "kafka" : true,
        "use" : "work"
      },
      {
        "name" : "pc2",
        "builderProducts" : [
            {
                "productIdx" : 16756757,
                "cnt" : 1
            },
            {
                "productIdx" : 11790199,
                "cnt" : 2
            },
              {
                "productIdx" : 12397271,
                "cnt" : 1
            },
            {
                "productIdx" : 16331876,
                "cnt" : 1
            },
              {
                "productIdx" : 16178162,
                "cnt" : 1
            }
        ],
        "kafka" : true,
        "use" : "work"
    }
    ]);
  }, []);

  useEffect(() => {
    // 순서 변경한 상품 데이터 받아오기
  }, [currDescNum]);

  const productTypeList = [
    { label: 'CPU', key: '0'}, 
    { label: '메인보드', key: '1'}, 
    { label: '그래픽카드', key: '2'}, 
    { label: 'RAM', key: '3'}, 
    { label: '파워', key: '4'}, 
    { label: 'SSD', key: '5'}, 
    { label: 'HDD', key: '6'}, 
    { label: '케이스', key: '7'}, 
    { label: '쿨러/기타', key: '8'}, 
  ];

  return (
    <>
      <div className={style.container}>
      <div className={style["product-tab"]}>
          <Tabs
            defaultActiveKey="0"
            items={productTypeList}
            onChange={(key) => setCurrTypeTab(key)} 
          />
      </div>
        
        <div className={style["product-filter"]}>
          <ProductFilter
            filterList={filterList}
            filterDetailList={filterDetailList}
          />
        </div>
        <div className={style["price-filter"]}>
          <span>가격</span>
          <Slider
            range={{ draggableTrack: true }}
            onChange={onPriceChange}
            defaultValue={[0, defaultMaxPrice]}
            max={10000000}
            step={100000}
            className={style.slider}
            tooltip={{
              open: true,
              formatter: PriceFormatter,
            }}
            trackStyle={{ backgroundColor: "#377BB9", height: "8px" }}
            handleStyle={{
              borderColor: "black",
              width: "25px",
              height: "16px",
              borderRadius: "5px",
            }}
          />
        </div>

        <div className={style["main-content"]}>
          <div className={style["filter-box"]}>
            <div className={style["desc-filter"]}>
              <DescFilter
                currDescNum={currDescNum}
                setCurrDescNum={setCurrDescNum}
              />
            </div>
            <div className={style["search-bar"]}>
              <input
                onChange={e => {
                  setSearchValue(e.target.value);
                }}
              />
              <SearchOutlined className={style["search-icon"]} />
            </div>
            <div className={style["builder-tab-button"]}>
              <button onClick={toggleDrawer}>{tabOpen ? '견적 탭 닫기':'견적 탭 열기'}</button>
            </div>
          </div>

          <div className={style["builder-drawer"]}>
            <div className={style["product-list"]}>
              <SearchProductListComponent
                productList={productList}
                setProductList={setProductList}
                currPage={currPage}
                setCurrPage={setCurrPage}
                currDescNum={currDescNum}
                setCurrDescNum={setCurrDescNum}
                searchValue={searchValue}
                minPrice={minPrice}
                maxPrice={maxPrice}
                filter={filter}
              />

            </div>
            <Drawer
              title="현재 견적 이름"
              placement="right"
              // closable={false}
              onClose={onClose}
              open={tabOpen}
              getContainer={false}
              mask={false}
              zIndex={0}
              // push={100}
              style={{
                position: "absolute",
              }}
            >
              <div>
                견적아이템들
              </div>
            </Drawer>
          </div>
        </div>
      </div>
      <Affix
        offsetTop={200}
        style={{ position: 'absolute', top : 80, right: 20 }}
      >
        <div className={style['side-menu']}>
          <div className={style['side-menu-header']}>
            <div className={style['side-menu-title']}>
              <span>PC 추가하기</span>
            </div>
            <div>
              <PlusOutlined onClick={onAddAccountBuildItem} />
            </div>
          </div>          
          <div className={style['side-menu-body']}>
            {accountBuildList.map((accountBuild, idx) => {
              return (
                <div key={idx} className={style['side-menu-item']}>
                  <span>{accountBuild.name}</span>
                  <span onClick={()=>onRemoveAccountBuildItem(idx)}><CloseOutlined /></span>
                </div>
              );
            })}
            
          </div>
        </div>
      </Affix>
      
    </>
  );
};

export default Search;
