import React, { useState, useEffect } from "react";
import style from "@/styles/Search.module.scss";
import ProductSearchFilter from "../search/ProductSearchFilter";
import DescFilter from "../recommend/DescFilter";
import { Affix, Slider, Drawer, Tabs, Popconfirm, message, Modal, Input } from "antd";
import PriceFormatter from "@/tools/PriceFormatter";
import {
  SearchOutlined,
  PlusOutlined,
  CloseOutlined,
  LeftOutlined,
  RightOutlined,
} from "@ant-design/icons";
import RecommendProductList from "./RecommendProductList";
import {
  getProductFilterRequest,
  getProductRequest,
} from "@/services/productService";
import ProductFilterKorean from "@/tools/ProductFilterKorean";
import { useSelector } from "react-redux";
import {
  getAllBuilderRequest,
  postBuilderRequest,
  getBuilderDetailRequest,
  deleteBuilderRequest,
} from "@/services/builderService.js";

const RecommendSearch = ({type, pickProduct, setPickProduct}) => {
  const defaultMaxPrice = 10000000;
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
  const [currBuilder, setCurrBuilder] = useState({});


  const [tabOpen, setTabOpen] = useState(false);

  const [filterBody, setFilterBody] = useState({});

  const [cpuList, setCpuList] = useState([]);
  const [mbList, setMbList] = useState([]);
  const [vgaList, setVgaList] = useState([]);
  const [ramList, setRamList] = useState([]);
  const [powerList, setPowerList] = useState([]);
  const [ssdList, setSsdList] = useState([]);
  const [hddList, setHddList] = useState([]);
  const [caseList, setCaseList] = useState([]);
  const [coolerList, setCoolerList] = useState([]);
  const [activeKey, setActiveKey] = useState(["1"]);

  const onPriceChange = e => {
    setMinPrice(e[0]);
    setMaxPrice(e[1]);
    setFilterBody({ ...filterBody, price: [e[0], e[1]] });
  };

  const onMinPriceChange = (price) => {
    if (price > maxPrice) {
      return;
    }
    setMinPrice(price);
    setFilterBody({ ...filterBody, price: [price, maxPrice] })
  }

  const onMaxPriceChange = (price) => {
    if (price < minPrice) {
      return;
    }
    setMaxPrice(price);
    setFilterBody({ ...filterBody, price: [minPrice, price] })
  }

  const onSearchButtonClicked = e => {
    setFilterBody({ ...filterBody, name: searchValue.trim() });
  };

  const onSearchInputKeyDown = e => {
    if (e.key == "Enter") {
      setFilterBody({ ...filterBody, name: searchValue.trim() });
    }
  };

  const onFilterBodyItemDelete = (key, value) => {
    //제거
    setFilterBody({
      ...filterBody,
      [key]: filterBody[key].filter(curr => curr != value),
    });
  };

  const onAddtionalFilterBodyItemDelete = (key) => {
    //false로 바꾸기
    setFilterBody({
      ...filterBody,
      [key]: false,
    })
  }

  // 현재 선택된 부품 필터키리스트, 필터밸류리스트 받아오기
  const getProductFilterData = async type => {
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
        list[key]?.sort();
      });
      setFilterDetailList(list);
    }
  };


  useEffect(() => {
    // 초기 페이지 로딩 시 cpu 필터링 데이터 가져오기
    getProductFilterData(type);
  }, []);

  // useEffect(() => {
  //   console.log("afg")
  // }, [filterBody])

  return (
    <>
      <div className={style.container}>
        <div className={style["product-filter"]}>
          <ProductSearchFilter
            filterList={filterList}
            filterDetailList={filterDetailList}
            currTypeTab={String(productType[type])}
            getProductFilterData={getProductFilterData}
            selectedFilterList={selectedFilterList}
            setSelectedFilterList={setSelectedFilterList}
            filterBody={filterBody}
            setFilterBody={setFilterBody}
            minPrice={minPrice}
            maxPrice={maxPrice}
          />
        </div>
        <div className={style['price-input-box']}>
          <div style={{display: 'flex', alignItems:'center'}}>
          <span style={{whiteSpace: 'nowrap'}}>최소</span>
            <input
              type={"number"}
              value={minPrice}
              onChange={(e) => onMinPriceChange(e.target.value)}
              step={1000}
              min={0}
              max={maxPrice}
              className={style['price-input']}
            />
          </div>
          <div style={{display: 'flex', alignItems:'center'}}>
          <span style={{whiteSpace: 'nowrap'}}>최대</span>
            <input
              type={"number"}
              value={maxPrice}
              onChange={(e) => onMaxPriceChange(e.target.value)}
              step={1000}
              min={minPrice}
              max={defaultMaxPrice}
              className={style['price-input']}
            />
          </div>
            <div className={style["price-filter"]}>
            <span style={{ fontSize: "18px", whiteSpace: "nowrap" }}>가격</span>
            {/* <span className={style["price-tooptip"]} style={{left: `${((minPrice/defaultMaxPrice) * 560) + 15}px`}}>{PriceFormatter(minPrice)}</span> */}
            {/* <span className={style["price-tooptip"]} style={{left: `${((maxPrice/defaultMaxPrice) * 600) + 50}px`}}>{PriceFormatter(maxPrice)}</span> */}
            <Slider
              range={{ draggableTrack: true }}
              onChange={onPriceChange}
              defaultValue={[0, defaultMaxPrice]}
              value={[minPrice, maxPrice]}
              min={0}
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
          </div>
        </div>
        
        
        <div className={style["selected-filter-box"]}>

          {Object.entries(filterBody).map((curr, idx) => {
              const key = curr[0];
              const value = curr[1];
            if (key !== "name" && key !== "price") {
              if (typeof (value) == 'boolean') {
                return (
                  <>
                    <span key={idx}>
                        {value ? <span key={idx} className={style["filter-body-item"]}>
                            {key}
                            <CloseOutlined
                              onClick={() => onAddtionalFilterBodyItemDelete(key)}
                              style={{ color: "red" }}
                            />
                          </span>: null}
                    </span>
                  </>
                );
                }
              if (value != undefined && value.length > 0) {
                return (
                  <div key={idx}>
                    <span>
                      {ProductFilterKorean[key]} :{" "}
                      {value?.map((v, idx) => {
                        return (
                          <span key={idx} className={style["filter-body-item"]}>
                            {v}
                            <CloseOutlined
                              onClick={() => onFilterBodyItemDelete(key, v)}
                              style={{ color: "red" }}
                            />
                          </span>
                        );
                      })}
                    </span>

                    <br />
                  </div>
                );
                } 
              }
            })}
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
              <SearchOutlined
                className={style["search-icon"]}
                onClick={onSearchButtonClicked}
              />
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
                currBuilder={currBuilder}
                setCurrBuilder={setCurrBuilder}
                cpuList={cpuList}
                setCpuList={setCpuList}
                ramList={ramList}
                setRamList={setRamList}
                hddList={hddList}
                setHddList={setHddList}
                mbList={mbList}
                setMbList={setMbList}
                ssdList={ssdList}
                setSsdList={setSsdList}
                coolerList={coolerList}
                setCoolerList={setCoolerList}
                powerList={powerList}
                setPowerList={setPowerList}
                caseList={caseList}
                setCaseList={setCaseList}
                vgaList={vgaList}
                setVgaList={setVgaList}
                activeKey={activeKey}
                setActiveKey={setActiveKey}
                tabOpen={tabOpen}
                setTabOpen={setTabOpen}
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
