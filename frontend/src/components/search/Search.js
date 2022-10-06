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
import SearchProductListComponent from "./SearchProductListComponent";
import {
  getProductFilterRequest,
  getProductRequest,
} from "@/services/productService";
import ProductFilterKorean from "@/tools/ProductFilterKorean";
import { AddtionalCoolerFilterList } from "@/tools/AddtionalCoolerFilterList";
import { AdditionalCasesFilterList } from "@/tools/AddtionalCasesFilterLIst";
import { filter, max } from "lodash";
import ProductNumMapper from "@/tools/ProductNumMapper";
import DrawerBody from "./DrawerBody";
import { useSelector } from "react-redux";
import {
  getAllBuilderRequest,
  postBuilderRequest,
  getBuilderDetailRequest,
  deleteBuilderRequest,
} from "@/services/builderService.js";

const Search = () => {
  const defaultMaxPrice = 5000000;

  const [currTypeTab, setCurrTypeTab] = useState("0");
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

  const [accountBuildList, setAccountBuildList] = useState([]);

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

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [builderName, setBuilderName] = useState("");

  const isLogin = useSelector(state => state.account.isLogin);

  const toggleDrawer = () => {
    setTabOpen(curr => !curr);
  };

  const onClose = () => {
    setTabOpen(false);
  };

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

  const onBuilderClicked = builder => {
    setCurrBuilder(builder);
    setTabOpen(true);
    // const dataToSubmit = {
    //   idx: builder.idx,
    //   builderProducts: [
    //     {
    //       productIdx: 17157083,
    //       cnt: 1,
    //     },
    //     {
    //       productIdx: 16451030,
    //       cnt: 1,
    //     }
    //   ]
    // }
    // postBuilderRequest(dataToSubmit);

    const fetchDetailData = async () => {
      const newCpuList = [];
      const newRamList = [];
      const newHddList = [];
      const newSsdList = [];
      const newPowerList = [];
      const newCoolerList = [];
      const newCaseList = [];
      const newMbList = [];
      const newVgaList = [];
      setCpuList([]);
      setRamList([]);
      setHddList([]);
      setSsdList([]);
      setPowerList([]);
      setCoolerList([]);
      setCaseList([]);
      setMbList([]);
      setVgaList([]);
      const result = await getBuilderDetailRequest(builder.idx);
      if (result?.data?.message === "success") {
        const builderProductDetailDtos =
          result.data.responseDto.builderProductDetailDtos;
        builderProductDetailDtos.map((product, idx) => {
          const item = {
            productIdx: product?.productIdx,
            productName: product?.productName,
            price: product?.price,
            cnt: product?.cnt,
          };
          switch (product?.category) {
            case 1:
              newCpuList.push(item);
              break;
            case 2:
              newRamList.push(item);
              break;
            case 3:
              newHddList.push(item);
              break;
            case 4:
              newSsdList.push(item);
              break;
            case 5:
              newPowerList.push(item);
              break;
            case 6:
              newCoolerList.push(item);
              break;
            case 7:
              newCaseList.push(item);
              break;
            case 8:
              newMbList.push(item);
              break;
            case 9:
              newVgaList.push(item);
              break;
          }
        });
      }
      setCpuList(newCpuList);
      setRamList(newRamList);
      setHddList(newHddList);
      setSsdList(newSsdList);
      setPowerList(newPowerList);
      setCoolerList(newCoolerList);
      setCaseList(newCaseList);
      setMbList(newMbList);
      setVgaList(newVgaList);
    };
    fetchDetailData();
  };

  const onAddAccountBuildItem = async () => {
    // axios 요청으로 사용자 저장 견적 데이터 추가
    const fetchData = async () => {
      const requestBody = {
        name: builderName,
        builderProducts: [],
      };
      const result = await postBuilderRequest(requestBody);
      console.log(result);
      if (result?.data?.message === "success") {
        message.success("생성되었습니다!");
        const newBuild = {
          myBuilderDto: {
            name: result.data.responseDto.name,
            idx: result.data.responseDto.idx,
          },
          builderProductDetailDtos: [],
        };
        setAccountBuildList([...accountBuildList, newBuild]);
        console.log(accountBuildList);
      }
    };
    fetchData();
    handleOk();
  };

  const onRemoveAccountBuildItem = (idx, index) => {
    // 프론트단에서 제거
    setAccountBuildList(
      accountBuildList.filter((accountBuild, i) => i !== idx)
    );
    // 실제 axios 요청으로 사용자 저장 견적 데이터 제거
    deleteBuilderRequest(index);
  };

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

  const showModal = () => {
    setIsModalOpen(true);
  }

  useEffect(() => {
    // 초기 페이지 로딩 시 cpu 필터링 데이터 가져오기
    getProductFilterData("cpu");
    // 사용자 저장 견적 데이터 가져오기
    if (isLogin) {
      const fetchData = async () => {
        const result = await getAllBuilderRequest();
        if (result?.data?.message === "success") {
          setAccountBuildList(result.data.responseDto.myBuilderDetailDtoList);
        }
      };
      fetchData();
    }
  }, []);

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

  const onChangeBuilderName = (e) => {
    setBuilderName(e.target.value)
  }

  const handleOk = () => {
    setBuilderName("");
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };

  return (
    <>
      <Modal title="견적 저장하기" 
            open={isModalOpen} 
            onOk={onAddAccountBuildItem} 
            onCancel={handleCancel}
            okText="저장하기"
            cancelText="취소하기"
            style={{
              top: "30%"
            }}
          >
            <Input
            addonBefore="견적 이름"
            placeholder="견적 이름을 입력해 주세요"
            allowClear
            onChange={onChangeBuilderName}
            className={style["builder-name-input"]}
            />
      </Modal>
      <div className={style.container}>
        <Affix>
          <div className={style["product-tab"]}>
            <Tabs
              defaultActiveKey="0"
              items={productTypeList}
              size={"large"}
              onChange={key => {
                setCurrTypeTab(key);
              }}
              className={style["tabs"]}
            />
          </div>
        </Affix>

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
        <div className={style['price-input-box']}>
          <div style={{display: 'flex', alignItems:'center'}}>
          <span style={{whiteSpace: 'nowrap'}}>최소</span>
            <input
              type={"number"}
              value={minPrice}
              onChange={(e) => onMinPriceChange(e.target.value)}
              step={1000}
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
              className={style['price-input']}
            />
          </div>
            <div className={style["price-filter"]}>
            <span style={{ fontSize: "18px", whiteSpace: "nowrap" }}>가격</span>
            <Slider
              range={{ draggableTrack: true }}
              // onAfterChange={onPriceChange}
              onChange={onPriceChange}
              defaultValue={[0, defaultMaxPrice]}
              value={[minPrice, maxPrice]}
              max={10000000}
              step={1000}
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
                // if (key == 'extendedAtx') {
                //   console.log(key);
                //   return;
                // }
                
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
            <div className={style["builder-tab-button"]}>
              <button onClick={toggleDrawer}>
                {tabOpen ? (
                  <span>
                    견적 탭 <RightOutlined />
                  </span>
                ) : (
                  <span>
                    견적 탭 <LeftOutlined />
                  </span>
                )}
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
              />
            </div>
            <Drawer
              title={
                currBuilder?.name != undefined
                  ? currBuilder?.name
                  : "현재 견적 이름"
              }
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
              <DrawerBody
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
              />
            </Drawer>
          </div>
        </div>
      </div>
      {isLogin ? (
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
                  <PlusOutlined onClick={showModal} />
              </div>
            </div>
            <div className={style["side-menu-body"]}>
              {accountBuildList.map((accountBuild, idx) => {
                return (
                  <div key={idx} className={style["side-menu-item"]}>
                    <span
                      onClick={() =>
                        onBuilderClicked(accountBuild.myBuilderDto)
                      }
                    >
                      {accountBuild?.myBuilderDto?.name}
                    </span>
                    <Popconfirm
                      placement="left"
                      title={"정말 삭제하시겠습니까?"}
                      onConfirm={() =>
                        onRemoveAccountBuildItem(
                          idx,
                          accountBuild.myBuilderDto?.idx
                        )
                      }
                      okText="삭제"
                      cancelText="취소"
                    >
                      <span style={{ cursor: "pointer" }}>
                        <CloseOutlined />
                      </span>
                    </Popconfirm>
                  </div>
                );
              })}
            </div>
          </div>
        </Affix>
      ) : null}
    </>
  );
};

export default Search;
