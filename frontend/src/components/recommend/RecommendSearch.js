import React, { useState, useEffect } from "react";
import style from "@/styles/Search.module.scss";
import ProductSearchFilter from "../search/ProductSearchFilter";
import DescFilter from "../recommend/DescFilter";
import { Slider, Drawer, Tabs } from "antd";
import PriceFormatter from "@/tools/PriceFormatter";
import { SearchOutlined, PlusOutlined, CloseOutlined, LeftOutlined, RightOutlined } from "@ant-design/icons";
import { getProductFilterRequest, getProductRequest } from "@/services/productService";
import ProductFilterKorean from "@/tools/ProductFilterKorean";
import RecommendProductList from '@/components/recommend/RecommendProductList'


const RecommendSearch = ({type, pickProduct, setPickProduct}) => {
  const defaultMaxPrice = 1000000;
  const productType = {"cpu" : 0, "vga" : 2, "ram" : 3, "hdd": 6, "ssd": 5, "psu": 4, "cooler": 8, "cases": 7, "mainboard": 1};


  const [currTypeTab, setCurrTypeTab] = useState(productType[type]);
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
  const [currBuilder, setCurrBuilder] = useState([]);

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
      { ...filterBody, name: searchValue.trim() }
    );
  }

  const onSearchInputKeyDown = (e) => {
    if (e.key == 'Enter') {
      setFilterBody(
        { ...filterBody, name: searchValue.trim() }
      );
    }
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
    getProductFilterData(type);
  }, []);

  useEffect(() => {
    // 순서 변경한 상품 데이터 받아오기
  }, [currDescNum]);


  return (
    <>
      <div className={style.container}>
        <div className={style["product-filter"]}>
          <ProductSearchFilter
            filterList={filterList}
            filterDetailList={filterDetailList}
            currTypeTab={currTypeTab.toString()}
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
          <span className={style["price-tooptip"]} style={{left: `${((minPrice/defaultMaxPrice) * 330) + 40}px`}}>{PriceFormatter(minPrice)}</span>
          <span className={style["price-tooptip"]} style={{left: `${((maxPrice/defaultMaxPrice) * 330) + 40}px`}}>{PriceFormatter(maxPrice)}</span>
          <Slider
            range={{ draggableTrack: true }}
            onAfterChange={onPriceChange}
            defaultValue={[0, defaultMaxPrice]}
            max={defaultMaxPrice}
            step={1000}
            className={style.slider}
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
                          {ProductFilterKorean[key]} : {value.map((v, idx) => {
                            return (
                              <span
                                key={idx}
                                className={style['filter-body-item']}>
                                {v}
                                <CloseOutlined
                                  onClick={() => onFilterBodyItemDelete(key, v)}
                                  style={{ color: 'red' }}
                                />
                              </span>);
                          })}
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
                onKeyDown={onSearchInputKeyDown}
              />
              <SearchOutlined className={style["search-icon"]} onClick={onSearchButtonClicked} />
            </div>
          </div>

          <div className={style["builder-drawer"]}>
            <div className={style["product-list"]}>
              <RecommendProductList
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
                pickProduct={pickProduct}
                setPickProduct={setPickProduct}
              />
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default RecommendSearch;
