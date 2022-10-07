import React, { useEffect, useState } from "react";
import style from "@/styles/ProductSelector.module.scss";
import {
  InfoCircleOutlined,
  PlusSquareOutlined,
  CloseCircleOutlined,
} from "@ant-design/icons";
import ContentItem from "./ContentItem";
import CustomCheckbox from "../CustomCheckbox";
import { notification } from 'antd';

const initProduct = [{ id: "", name: "", count: 1, price: 0 }];

// Detail
const BuilderProductSelector = ({
  filterItem,
  setFilterItem,
  builder,
  products,
}) => {
  const [cpuList, setCpuList] = useState(
    products[0].length != 0 ? products[0] : initProduct
  );
  const [mbList, setMbList] = useState(
    products[7].length != 0 ? products[7] : initProduct
  );
  const [vgaList, setVgaList] = useState(
    products[8].length != 0 ? products[8] : initProduct
  );
  const [ramList, setRamList] = useState(
    products[1].length != 0 ? products[1] : initProduct
  );
  const [powerList, setPowerList] = useState(
    products[4].length != 0 ? products[4] : initProduct
  );
  const [ssdList, setSsdList] = useState(
    products[3].length != 0 ? products[3] : initProduct
  );
  const [caseList, setCaseList] = useState(
    products[6].length != 0 ? products[6] : initProduct
  );
  const [coolerList, setCoolerList] = useState(
    products[5].length != 0 ? products[5] : initProduct
  );
  const [hddList, setHddList] = useState(
    products[2].length != 0 ? products[2] : initProduct
  );

  const [cpuChecked, setCpuChecked] = useState(
    products[0].length != 0 ? true : false
  );
  const [mbChecked, setMbChecked] = useState(
    products[7].length != 0 ? true : false
  );
  const [vgaChecked, setVgaChecked] = useState(
    products[8].length != 0 ? true : false
  );
  const [ramChecked, setRamChecked] = useState(
    products[1].length != 0 ? true : false
  );
  const [powerChecked, setPowerChecked] = useState(
    products[4].length != 0 ? true : false
  );
  const [ssdChecked, setSsdChecked] = useState(
    products[3].length != 0 ? true : false
  );
  const [caseChecked, setCaseChecked] = useState(
    products[6].length != 0 ? true : false
  );
  const [coolerChecked, setCoolerChecked] = useState(
    products[5].length != 0 ? true : false
  );
  const [hddChecked, setHddChecked] = useState(
    products[2].length != 0 ? true : false
  );

  const [currTybeTab, setCurrTypeTab] = useState("");

  const [isOnlyViewRecommend, setIsOnlyViewRecommend] = useState(true);

  const [totalPrice, setTotalPrice] = useState(0);
  const getTotalPrice = () => {
    let price = 0;
    cpuList.map((curr) => (price += curr.price * curr.count));
    mbList.map((curr) => (price += curr.price * curr.count));
    vgaList.map((curr) => (price += curr.price * curr.count));
    ramList.map((curr) => (price += curr.price * curr.count));
    powerList.map((curr) => (price += curr.price * curr.count));
    ssdList.map((curr) => (price += curr.price * curr.count));
    hddList.map((curr) => (price += curr.price * curr.count));
    coolerList.map((curr) => (price += curr.price * curr.count));
    caseList.map((curr) => (price += curr.price * curr.count));
    setTotalPrice(price);
  };

  const getTotalProds = () => {
    let newObj = {};
    if(caseChecked) {
      caseList.map((curr) => {
        if (curr.id != '') {
          if(newObj[curr.id] != undefined) {
            newObj[curr.id] += curr.count
          } else {
            newObj[curr.id] = curr.count
          }
        }
      })
    }

    if(coolerChecked) {
      coolerList.map((curr) => {
        if (curr.id != '') {
          if(newObj[curr.id] != undefined) {
            newObj[curr.id] += curr.count
          } else {
            newObj[curr.id] = curr.count
          }
        }
      })
    }

    if(hddChecked) {
      hddList.map((curr) => {
        if (curr.id != '') {
          if(newObj[curr.id] != undefined) {
            newObj[curr.id] += curr.count
          } else {
            newObj[curr.id] = curr.count
          }
        }
      })
    }
    if(ssdChecked) {
      ssdList.map((curr) => {
        if (curr.id != '') {
          if(newObj[curr.id] != undefined) {
            newObj[curr.id] += curr.count
          } else {
            newObj[curr.id] = curr.count
          }
        }
      })
    }
    if(powerChecked) {
      powerList.map((curr) => {
        if (curr.id != '') {
          if(newObj[curr.id] != undefined) {
            newObj[curr.id] += curr.count
          } else {
            newObj[curr.id] = curr.count
          }
        }
      })
    }
    if(cpuChecked) {
      cpuList.map((curr) => {
        if (curr.id != '') {
          if(newObj[curr.id] != undefined) {
            newObj[curr.id] += curr.count
          } else {
            newObj[curr.id] = curr.count
          }
        }
      })
    }
    if(vgaChecked) {
      vgaList.map((curr) => {
        if (curr.id != '') {
          if(newObj[curr.id] != undefined) {
            newObj[curr.id] += curr.count
          } else {
            newObj[curr.id] = curr.count
          }
        }
      })
    }
    if(mbChecked) {
      mbList.map((curr) => {
        if (curr.id != '') {
          if(newObj[curr.id] != undefined) {
            newObj[curr.id] += curr.count
          } else {
            newObj[curr.id] = curr.count
          }
        }
      })
    }
    if(ramChecked) {
      ramList.map((curr) => {
        if (curr.id != '') {
          if(newObj[curr.id] != undefined) {
            newObj[curr.id] += curr.count
          } else {
            newObj[curr.id] = curr.count
          }
        }
      })
    }
    return newObj
  }

  const getStringPrice = (price) => {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };

  const getProdCount = (prodList) => {
    let s = 0;
    prodList.map((i) => {
      s += i.count;
    });
    return s;
  };

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
      prods: getTotalProds(),
    });
  }, [
    cpuList,
    mbList,
    vgaList,
    ramList,
    powerList,
    ssdList,
    hddList,
    coolerList,
    caseList,
    cpuChecked,
    ramChecked,
    hddChecked,
    ssdChecked,
    powerChecked,
    coolerChecked,
    caseChecked,
    mbChecked,
    vgaChecked,
  ]);

  useEffect(() => {
  }, [filterItem]);

  const openNotificationWithIcon = (type, desc) => {
    notification[type]({
      message: desc,
    });
  };

  const notificateError = () => {
    openNotificationWithIcon('error', "해당 부품은 제외할 수 없습니다");
  }

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
          getTotalProds={getTotalProds}
          notificateError={notificateError}
        />
        <ContentItem
          name="M/B"
          type="mainboard"
          currTybeTab={currTybeTab}
          checkState={mbChecked}
          checkSetter={setMbChecked}
          contentList={mbList}
          contentSetter={setMbList}
          getTotalProds={getTotalProds}
          notificateError={notificateError}
        />
        <ContentItem
          name="그래픽카드"
          type="vga"
          currTybeTab={currTybeTab}
          checkState={vgaChecked}
          checkSetter={setVgaChecked}
          contentList={vgaList}
          contentSetter={setVgaList}
          getTotalProds={getTotalProds}
          notificateError={notificateError}
        />
        <ContentItem
          name="RAM"
          type="ram"
          currTybeTab={currTybeTab}
          checkState={ramChecked}
          checkSetter={setRamChecked}
          contentList={ramList}
          contentSetter={setRamList}
          getTotalProds={getTotalProds}
          notificateError={notificateError}
        />
        <ContentItem
          name="POWER"
          type="psu"
          currTybeTab={currTybeTab}
          checkState={powerChecked}
          checkSetter={setPowerChecked}
          contentList={powerList}
          contentSetter={setPowerList}
          getTotalProds={getTotalProds}
          notificateError={notificateError}
        />
        <ContentItem
          name="SSD"
          type="ssd"
          currTybeTab={currTybeTab}
          checkState={ssdChecked}
          checkSetter={setSsdChecked}
          contentList={ssdList}
          contentSetter={setSsdList}
          getTotalProds={getTotalProds}
          notificateError={notificateError}
        />
        <ContentItem
          name="케이스"
          type="cases"
          currTybeTab={currTybeTab}
          checkState={caseChecked}
          checkSetter={setCaseChecked}
          contentList={caseList}
          contentSetter={setCaseList}
          getTotalProds={getTotalProds}
          notificateError={notificateError}
        />
        <ContentItem
          name="쿨러"
          type="cooler"
          currTybeTab={currTybeTab}
          checkState={coolerChecked}
          checkSetter={setCoolerChecked}
          contentList={coolerList}
          contentSetter={setCoolerList}
          getTotalProds={getTotalProds}
          notificateError={notificateError}
        />
        <ContentItem
          name="HDD"
          type="hdd"
          currTybeTab={currTybeTab}
          checkState={hddChecked}
          checkSetter={setHddChecked}
          contentList={hddList}
          contentSetter={setHddList}
          getTotalProds={getTotalProds}
          notificateError={notificateError}
        />
        <div className={style["bottom-box"]}>
          <div className={style["bottom-left"]}>
            <span>가격</span>
            <div className={style.price}>
              <span>약 {getStringPrice(builder.price)} 원</span>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default BuilderProductSelector;
