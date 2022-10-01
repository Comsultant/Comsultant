import React, { useState, useEffect } from "react";
import style from "@/styles/Search.module.scss";
import ProductSearchFilter from "../search/ProductSearchFilter";
import DescFilter from "../recommend/DescFilter";
import { Affix, Slider, Drawer, Tabs } from "antd";
import PriceFormatter from "@/tools/PriceFormatter";
import { SearchOutlined, PlusOutlined, CloseOutlined } from "@ant-design/icons";
import SearchProductListComponent from "./SearchProductListComponent";
import { getProductFilterRequest, getProductRequest } from "@/services/productService";
import ProductFilterKorean from "@/tools/ProductFilterKorean";
import { AddtionalCoolerFilterList } from "@/tools/AddtionalCoolerFilterList";
import { AdditionalCasesFilterList } from "@/tools/AddtionalCasesFilterLIst";
import { filter, max } from "lodash";
import ProductNumMapper from "@/tools/ProductNumMapper";


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
  const [totalPage, setTotalPage] = useState(0);
  const [selectedFilterList, setSelectedFilterList] = useState();

  const [accountBuildList, setAccountBuildList] = useState([]);

  const [tabOpen, setTabOpen] = useState(false);

  const [filterBody, setFilterBody] = useState({});

  const toggleDrawer = () => {
    setTabOpen(curr => !curr);
  };

  const onClose = () => {
    setTabOpen(false);
  };

  const onPriceChange = (e) => {
    setMinPrice(e[0]);
    setMaxPrice(e[1]);
    setFilterBody(
      { ...filterBody, price: [e[0], e[1]] }
    );
  };

  const onSearchButtonClicked = (e) => {
    setFilterBody(
      { ...filterBody, name: searchValue }
    );
  }

  const onFilterBodyItemDelete = (key, value) => {
    //제거
    setFilterBody(
      { ...filterBody, [key] : filterBody[key].filter((curr) => curr != value)}
    )
  
  }

  const onAddAccountBuildItem = () => {
    //프론트단에서 추가
    const newBuild = {
      name: "PC" + parseInt(accountBuildList.length + 1),
      builderProducts: [],
    };
    setAccountBuildList([...accountBuildList, newBuild]);
    // axios 요청으로 사용자 저장 견적 데이터 추가
  };
  
  const onRemoveAccountBuildItem = (idx) => {
    // 프론트단에서 제거
    setAccountBuildList(
      accountBuildList.filter((accountBuild, i) => i !== idx)
    );
    // 실제 axios 요청으로 사용자 저장 견적 데이터 제거
  };

  // 현재 선택된 부품 필터키리스트, 필터밸류리스트 받아오기
  const getProductFilterData = async (type) => {
    const result = await getProductFilterRequest(type);
    if (result?.data?.message === "success") {
      const data = result.data.responseDto;
      const keys = Object.keys(data);
      setFilterList(keys);
      const list = [];

      keys.map(key => {
        data[key].map(curr => {
          if (curr !== "0" && curr !== "") {
            if (list[key] === undefined) {
              list[key] = [curr];
            } else {
              list[key].push(curr);
            }
          }
        });
      });

      keys.map(key => {
        list[key].sort();
      });

      setFilterDetailList(list);
    }
  };

  useEffect(() => {
    // 초기 페이지 로딩 시 cpu 필터링 데이터 가져오기
    getProductFilterData("cpu");


    // 사용자 저장 견적 데이터 가져오기 (임시로 상수로 설정함)
    setAccountBuildList([
      {
        name: "pc1",
        builderProducts: [
          {
            productIdx: 16756757,
            cnt: 1,
          },
          {
            productIdx: 11790199,
            cnt: 2,
          },
          {
            productIdx: 12397271,
            cnt: 1,
          },
          {
            productIdx: 16331876,
            cnt: 1,
          },
          {
            productIdx: 16178162,
            cnt: 1,
          },
        ],
        kafka: true,
        use: "work",
      },
      {
        name: "pc2",
        builderProducts: [
          {
            productIdx: 16756757,
            cnt: 1,
          },
          {
            productIdx: 11790199,
            cnt: 2,
          },
          {
            productIdx: 12397271,
            cnt: 1,
          },
          {
            productIdx: 16331876,
            cnt: 1,
          },
          {
            productIdx: 16178162,
            cnt: 1,
          },
        ],
        kafka: true,
        use: "work",
      },
    ]);
  }, []);

  useEffect(() => {
    // 순서 변경한 상품 데이터 받아오기
  }, [currDescNum]);

  // const fetchProductList = async (dataToSubmit) => {
  //   const result = await getProductRequest(dataToSubmit);
  //   return result;
  // }

  // useEffect(() => {
  //   console.log("filterBody changed!");
  //   console.log(filterBody);
  //   let type = ProductNumMapper[currTypeTab];
  //   const dataToSubmit = {
  //     page: currPage,
  //     type,
  //     body: filterBody
  //   }
  //   const result = fetchProductList(dataToSubmit);
  //   if (result?.message === "success") {
  //     setProductList(result?.responseDto?.productList);
  //     setTotalPage(result?.responseDto?.totalPage);
  //     console.log(productList);
  //   }
  // }, [filterBody]);


  const productTypeList = [
    { label: "CPU", key: "0" },
    { label: "메인보드", key: "1" },
    { label: "그래픽카드", key: "2" },
    { label: "RAM", key: "3" },
    { label: "파워", key: "4" },
    { label: "SSD", key: "5" },
    { label: "HDD", key: "6" },
    { label: "케이스", key: "7" },
    { label: "쿨러/기타", key: "8" },
  ];

  return (
    <>
      <div className={style.container}>
        <div className={style["product-tab"]}>
          <Tabs
            defaultActiveKey="0"
            items={productTypeList}
            size={"large"}
            onChange={key => { setCurrTypeTab(key); }}
            className={style['tabs']}
          />
        </div>

        <div className={style["product-filter"]}>
          <ProductSearchFilter
            filterList={filterList}
            filterDetailList={filterDetailList}
            currTypeTab={currTypeTab}
            getProductFilterData={getProductFilterData}
            selectedFilterList={selectedFilterList}
            setSelectedFilterList={setSelectedFilterList}
            filterBody={filterBody}
            setFilterBody={setFilterBody}
            minPrice={minPrice}
            maxPrice={maxPrice}
          />
        </div>
        <div className={style["price-filter"]}>
          <span>가격</span>
          <Slider
            range={{ draggableTrack: true }}
            onAfterChange={onPriceChange}
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
          <div className={style["selected-filter-box"]}>
              {
                Object.entries(filterBody).map((curr) => {
                  const key = curr[0];
                  const value = curr[1];
                  if (key !== "name" && key !== "price" && value.length > 0) {
                    return (
                      <>
                      <span>
                          {ProductFilterKorean[key]} : {value.map((v) => { return (<span className={style['filter-body-item']}>{v}<CloseOutlined onClick={()=>onFilterBodyItemDelete(key, v) } style={{color:'red'}}/></span>); })}
                      </span>
                        
                      <br/>
                      </>
                    );
                  }
                })
              }

          </div>
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
              <SearchOutlined className={style["search-icon"]} onClick={onSearchButtonClicked} />
            </div>
            <div className={style["builder-tab-button"]}>
              <button onClick={toggleDrawer}>
                {tabOpen ? "견적 탭 닫기" : "견적 탭 열기"}
              </button>
            </div>
          </div>

          <div className={style["builder-drawer"]}>
            <div className={style["product-list"]}>
              <SearchProductListComponent
                productList={productList}
                setProductList={setProductList}
                currPage={currPage}
                totalPage={totalPage}
                setTotalPage={setTotalPage}
                setCurrPage={setCurrPage}
                currDescNum={currDescNum}
                setCurrDescNum={setCurrDescNum}
                searchValue={searchValue}
                minPrice={minPrice}
                maxPrice={maxPrice}
                filterBody={filterBody}
                setFilterBody={setFilterBody}
                currTypeTab={currTypeTab}
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
              zIndex={1}
              // push={100}
              style={{
                position: "absolute",
              }}
            >
              <div>견적아이템들</div>
            </Drawer>
          </div>
        </div>
      </div>
      <Affix
        offsetTop={200}
        style={{ position: "absolute", top: 80, right: 20 }}
      >
        <div className={style["side-menu"]}>
          <div className={style["side-menu-header"]}>
            <div className={style["side-menu-title"]}>
              <span>PC 추가하기</span>
            </div>
            <div>
              <PlusOutlined onClick={onAddAccountBuildItem} />
            </div>
          </div>
          <div className={style["side-menu-body"]}>
            {accountBuildList.map((accountBuild, idx) => {
              return (
                <div key={idx} className={style["side-menu-item"]}>
                  <span>{accountBuild.name}</span>
                  <span onClick={() => onRemoveAccountBuildItem(idx)}>
                    <CloseOutlined />
                  </span>
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
