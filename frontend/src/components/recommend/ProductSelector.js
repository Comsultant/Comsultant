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

const ProductSelector = ({isRecommendPressed, setIsRecommendPressed}) => {
  const [cpuList, setCpuList] = useState(initProduct);
  const [mbList, setMbList] = useState(initProduct);
  const [vgaList, setVgaList] = useState(initProduct);
  const [ramList, setRamList] = useState(initProduct);
  const [powerList, setPowerList] = useState(initProduct);
  const [ssdList, setSsdList] = useState(initProduct);
  const [hddList, setHddList] = useState(initProduct);

  const [cpuChecked, setCpuChecked] = useState(true);
  const [mbChecked, setMbChecked] = useState(true);
  const [vgaChecked, setVgaChecked] = useState(true);
  const [ramChecked, setRamChecked] = useState(true);
  const [powerChecked, setPowerChecked] = useState(true);
  const [ssdChecked, setSsdChecked] = useState(true);
  const [hddChecked, setHddChecked] = useState(false);

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

  const onRecommendButtonClicked = () => {
    setIsRecommendPressed(true);
  }

  useEffect(() => {
    getTotalPrice();
  }, [cpuList, mbList, vgaList, ramList, powerList, ssdList, hddList])

  return (
    <>
      <div className={style["content-box"]}>
        <ContentItem
          name="CPU"
          checkState={cpuChecked}
          checkSetter={setCpuChecked}
          contentList={cpuList}
          contentSetter={setCpuList}
        />
        <ContentItem
          name="M/B"
          checkState={mbChecked}
          checkSetter={setMbChecked}
          contentList={mbList}
          contentSetter={setMbList}
        />
        <ContentItem
          name="그래픽카드"
          checkState={vgaChecked}
          checkSetter={setVgaChecked}
          contentList={vgaList}
          contentSetter={setVgaList}
        />
        <ContentItem
          name="RAM"
          checkState={ramChecked}
          checkSetter={setRamChecked}
          contentList={ramList}
          contentSetter={setRamList}
        />
        <ContentItem
          name="POWER"
          checkState={powerChecked}
          checkSetter={setPowerChecked}
          contentList={powerList}
          contentSetter={setPowerList}
        />
        <ContentItem
          name="SSD"
          checkState={ssdChecked}
          checkSetter={setSsdChecked}
          contentList={ssdList}
          contentSetter={setSsdList}
        />
        <ContentItem
          name="HDD"
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
