import React, { useEffect, useState } from "react";
import style from "@/styles/ProductSelector.module.scss";
import {
  InfoCircleOutlined,
  PlusSquareOutlined,
  CloseCircleOutlined,
} from "@ant-design/icons";
import ContentItem from "./ContentItem";
import CustomCheckbox from "../CustomCheckbox";

const initProduct = [{ id: "", name: "", count: 1, price: 0 }];

const ProductSelector = ({filterItem, setFilterItem, getRecommendList}) => {
  const [cpuList, setCpuList] = useState(initProduct);
  const [mbList, setMbList] = useState(initProduct);
  const [vgaList, setVgaList] = useState(initProduct);
  const [ramList, setRamList] = useState(initProduct);
  const [powerList, setPowerList] = useState(initProduct);
  const [ssdList, setSsdList] = useState(initProduct);
  const [caseList, setCaseList] = useState(initProduct);
  const [coolerList, setCoolerList] = useState(initProduct);
  const [hddList, setHddList] = useState(initProduct);

  const [cpuChecked, setCpuChecked] = useState(true);
  const [mbChecked, setMbChecked] = useState(true);
  const [vgaChecked, setVgaChecked] = useState(true);
  const [ramChecked, setRamChecked] = useState(true);
  const [powerChecked, setPowerChecked] = useState(true);
  const [ssdChecked, setSsdChecked] = useState(true);
  const [caseChecked, setCaseChecked] = useState(true);
  const [coolerChecked, setCoolerChecked] = useState(true);
  const [hddChecked, setHddChecked] = useState(false);

  const [currTybeTab, setCurrTypeTab] = useState("");

  const [isOnlyViewRecommend, setIsOnlyViewRecommend] = useState(true);

  const [totalPrice, setTotalPrice] = useState(0);
  const getTotalPrice = () => {
    let price = 0;
    cpuList.map((curr) => price += curr.price * curr.count);
    mbList.map((curr) => price += curr.price * curr.count);
    vgaList.map((curr) => price += curr.price * curr.count);
    ramList.map((curr) => price += curr.price * curr.count);
    powerList.map((curr) => price += curr.price * curr.count);
    ssdList.map((curr) => price += curr.price * curr.count);
    hddList.map((curr) => price += curr.price * curr.count);
    setTotalPrice(price);
  }

  const getStringPrice = (price) => {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
  }

  const onRecommendButtonClicked = async () => {
    await getRecommendList()
  }

  const getProdCount = (prodList) => {
    let s = 0
    prodList.map((i) => {
      s += i.count
    })
    return s
  }

  /**
   * 부품 개수 계산
   */
  useEffect(() => {
    getTotalPrice();
    const cpuCount = getProdCount(cpuList);
    const ramCount = getProdCount(ramList);
    const hddCount = getProdCount(hddList);
    const ssdCount = getProdCount(ssdList);
    const psuCount = getProdCount(powerList);
    const coolerCount = getProdCount(coolerList);
    const caseCount = getProdCount(caseList);
    const mainboardCount = getProdCount(mbList);
    const vgaCount = getProdCount(vgaList);

    setFilterItem({
      ...filterItem, 
      cpu_cnt: cpuChecked ? cpuCount : 0,
      ram_cnt: ramChecked ? ramCount : 0,
      hdd_cnt: hddChecked ? hddCount : 0,
      ssd_cnt: ssdChecked ? ssdCount : 0,
      psu_cnt: powerChecked ? psuCount : 0,
      cooler_cnt: coolerChecked ? coolerCount : 0,
      cases_cnt: caseChecked ? caseCount : 0,
      mainboard_cnt: mbChecked ? mainboardCount : 0,
      vga_cnt: vgaChecked ? vgaCount : 0,
    })
  }, [cpuList, mbList, vgaList, ramList, powerList, ssdList, hddList, coolerList, caseList, 
    cpuChecked, ramChecked, hddChecked, ssdChecked, powerChecked, coolerChecked, caseChecked, mbChecked, vgaChecked])

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
        list[key].sort();
      });

      // console.log(list);
      setFilterDetailList(list);
    }
  };

  return (
    <>
      <div className={style["content-box"]}>
        <ContentItem
          name="CPU"
          type="cpu"
          currTybeTab={currTybeTab}
          checkState={cpuChecked}
          checkSetter={setCpuChecked}
          contentList={cpuList}
          contentSetter={setCpuList}
          getProductFilterData={getProductFilterData}
        />
        <p>uasdfadsfasd</p>
        <ContentItem
          name="M/B"
          type="mainboard"
          currTybeTab={currTybeTab}
          checkState={mbChecked}
          checkSetter={setMbChecked}
          contentList={mbList}
          contentSetter={setMbList}
          getProductFilterData={getProductFilterData}
        />
        <ContentItem
          name="그래픽카드"
          type="vga"
          currTybeTab={currTybeTab}
          checkState={vgaChecked}
          checkSetter={setVgaChecked}
          contentList={vgaList}
          contentSetter={setVgaList}
          getProductFilterData={getProductFilterData}
        />
        <ContentItem
          name="RAM"
          type="ram"
          currTybeTab={currTybeTab}
          checkState={ramChecked}
          checkSetter={setRamChecked}
          contentList={ramList}
          contentSetter={setRamList}
          getProductFilterData={getProductFilterData}
        />
        <ContentItem
          name="POWER"
          type="psu"
          currTybeTab={currTybeTab}
          checkState={powerChecked}
          checkSetter={setPowerChecked}
          contentList={powerList}
          contentSetter={setPowerList}
          getProductFilterData={getProductFilterData}
        />
        <ContentItem
          name="SSD"
          type="ssd"
          currTybeTab={currTybeTab}
          checkState={ssdChecked}
          checkSetter={setSsdChecked}
          contentList={ssdList}
          contentSetter={setSsdList}
          getProductFilterData={getProductFilterData}
        />
        <ContentItem
        name="케이스"
          type="cases"
        currTybeTab={currTybeTab}
        checkState={caseChecked}
        checkSetter={setCaseChecked}
        contentList={caseList}
        contentSetter={setCaseList}
        getProductFilterData={getProductFilterData}
        />          
        <ContentItem
        name="쿨러"
          type="cooler"
        currTybeTab={currTybeTab}
        checkState={coolerChecked}
        checkSetter={setCoolerChecked}
        contentList={coolerList}
        contentSetter={setCoolerList}
        getProductFilterData={getProductFilterData}
        />
        <ContentItem
          name="HDD"
          type="hdd"
          currTybeTab={currTybeTab}
          checkState={hddChecked}
          checkSetter={setHddChecked}
          contentList={hddList}
          contentSetter={setHddList}
          
        />    
        <div className={style['bottom-box']}>
          <div className={style['bottom-left']}>
            <span>
              선택 가격
            </span>
            <div className={style.price}>
              <span>{getStringPrice(totalPrice)} 원</span>
            </div>
          </div>
          <div className={style['bottom-right']}>
            <span>추천 품목만 보기</span>
            <CustomCheckbox
              backgroundColor="pink"
              state={isOnlyViewRecommend}
              setter={setIsOnlyViewRecommend}
            />
            <button
              className={style.button}
              onClick={onRecommendButtonClicked}
            >
              추천 받기
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default ProductSelector;
