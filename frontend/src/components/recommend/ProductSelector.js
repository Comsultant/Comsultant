import React, { useEffect, useState } from "react";
import style from "@/styles/ProductSelector.module.scss";
import ContentItem from "./ContentItem";
import { useDispatch, useSelector } from "react-redux";
import CustomCheckbox from "../CustomCheckbox";
import { saveRecommendBuilder } from "@/services/recommendService";
import { getAllBuilderRequest } from "@/services/builderService";
import { Modal, Input, notification } from "antd";

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

  const [myBuilderList, setMyBuilderList] = useState([])
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [builderName, setBuilderName] = useState("");
  const isLogin = useSelector((state) => state.account.isLogin);

  const openNotificationWithIcon = (type, desc) => {
    notification[type]({
      message: desc,
    });
  };

  const getProductsArray = (exceptIdx) => {
    console.log(exceptIdx)
    const builderProducts = [];
    if(cpuChecked) {
      cpuList.map((product, idx) => {
        if(product.id != exceptIdx) {
          const item = { productIdx: product.id, cnt: product.count };
          builderProducts.push(item);
        } 
      })
    }

    if(ramChecked) {
      ramList.map((product, idx) => {
        if(product.id != exceptIdx) {
          const item = { productIdx: product.id, cnt: product.count };
          builderProducts.push(item);
        } 
      })
    }
    if(hddChecked) {
      hddList.map((product, idx) => {
        if(product.id != exceptIdx) {
          const item = { productIdx: product.id, cnt: product.count };
          builderProducts.push(item);
        } 
      })
    }

    if(ssdChecked) { 
      ssdList.map((product, idx) => {
        if(product.id != exceptIdx) {
          const item = { productIdx: product.id, cnt: product.count };
          builderProducts.push(item);
        } 
      })
    }
    if(powerChecked) {
      powerList.map((product, idx) => {
        if(product.id != exceptIdx) {
          const item = { productIdx: product.id, cnt: product.count };
          builderProducts.push(item);
        } 
      })
    }
    if(coolerChecked) {
      coolerList.map((product, idx) => {
        if(product.id != exceptIdx) {
          const item = { productIdx: product.id, cnt: product.count };
          builderProducts.push(item);
        } 
      })
    }
    if(caseChecked){
      caseList.map((product, idx) => {
        if(product.id != exceptIdx) {
          const item = { productIdx: product.id, cnt: product.count };
          builderProducts.push(item);
        } 
      })
    }
    if(mbChecked) {
      mbList.map((product, idx) => {
        if(product.id != exceptIdx) {
          const item = { productIdx: product.id, cnt: product.count };
          builderProducts.push(item);
        } 
      })
    }

    if(vgaChecked) {
      vgaList.map((product, idx) => {
        if(product.id != exceptIdx) {
          const item = { productIdx: product.id, cnt: product.count };
          builderProducts.push(item);
        } 
      })
    }
    return builderProducts;
  }
  
  const getTotalPrice = () => {
    let price = 0;
    cpuList.map((curr) => price += curr.price * curr.count);
    mbList.map((curr) => price += curr.price * curr.count);
    vgaList.map((curr) => price += curr.price * curr.count);
    ramList.map((curr) => price += curr.price * curr.count);
    powerList.map((curr) => price += curr.price * curr.count);
    ssdList.map((curr) => price += curr.price * curr.count);
    hddList.map((curr) => price += curr.price * curr.count);
    coolerList.map((curr) => price += curr.price * curr.count);
    caseList.map((curr) => price += curr.price * curr.count);
    setTotalPrice(price);
  }

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
      prods: getTotalProds()
    })
  }, [cpuList, mbList, vgaList, ramList, powerList, ssdList, hddList, coolerList, caseList, 
    cpuChecked, ramChecked, hddChecked, ssdChecked, powerChecked, coolerChecked, caseChecked, mbChecked, vgaChecked])


  // 아래서 부터는 견적 저장/불러오기 코드

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    setBuilderName("");
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };

  const makeRequestBody = () => {
    // console.log(filterItem)
    // console.log(builder)
    let builderProducts = [];
    Object.keys(filterItem.prods).map((key) => {
      builderProducts.push({productIdx: key, cnt: filterItem.prods[key]})
    })

    const reqBody = {
      builderProducts: builderProducts,
    }
    
    return reqBody;
  }

  const saveBuilder = async () => {
    if(builderName.trim().length == 0) {
      alert("이름을 입력해주세요");
      return;
    }
    const body = makeRequestBody()

    if(body.builderProducts.length == 0) {
      alert("제품을 추가해 주세요")
      handleCancel();
      return ;
    }

    body.name = builderName.trim()

    // 요청보낸다
    const result = await saveRecommendBuilder(body);
    console.log(result)
    if(result?.data?.message === "success") {
      alert('저장 성공')
    } else {
      alert('저장 실패')
    }

    // 보낸 후에 builderName 초기화시키고 모달 닫는다.
    handleOk();
    getMyBuilderList();
  }

  const onChangeBuilderName = (e) => {
    setBuilderName(e.target.value)
  }

  const onMyBuilderChange = (e) => {
    let idx = e.target.value;
    if(idx == -1) {
      return;
    }

    console.log(myBuilderList[idx])

    // 아래 배열을 탐색하면서 넣어준다.
    
    // 백엔드 기준 카테고리로 계산 ( 인덱스 1 부터 시작)
    let prodCnt = [0,0,0,0,0,0,0,0,0,0];
    let newProducts = [[],[],[],[],[],[],[],[],[],[]];
    let products = [];
    // console.log(myBuilderList[idx].builderProductDetailDtos)
    for(let builderProduct of myBuilderList[idx].builderProductDetailDtos) {
      let c = builderProduct.category
      prodCnt[c] += builderProduct.cnt;
      newProducts[c].push({id: builderProduct.productIdx, name: builderProduct.productName, count: builderProduct.cnt, price: builderProduct.price})
      products.push({[builderProduct.productIdx]: builderProduct.cnt})
    }
    // console.log(prodCnt, newProducts)
    setCpuList(newProducts[1].length == 0 ? initProduct : newProducts[1]);
    setRamList(newProducts[2].length == 0 ? initProduct : newProducts[2]);
    setHddList(newProducts[3].length == 0 ? initProduct : newProducts[3]);
    setSsdList(newProducts[4].length == 0 ? initProduct : newProducts[4]);
    setPowerList(newProducts[5].length == 0 ? initProduct : newProducts[5]);
    setCoolerList(newProducts[6].length == 0 ? initProduct : newProducts[6]);
    setCaseList(newProducts[7].length == 0 ? initProduct : newProducts[7]);
    setMbList(newProducts[8].length == 0 ? initProduct : newProducts[8]);
    setVgaList(newProducts[9].length == 0 ? initProduct : newProducts[9]);

    setCpuChecked(prodCnt[1] != 0)
    setRamChecked(prodCnt[2] != 0)
    setHddChecked(prodCnt[3] != 0)
    setSsdChecked(prodCnt[4] != 0)
    setPowerChecked(prodCnt[5] != 0)
    setCoolerChecked(prodCnt[6] != 0)
    setCaseChecked(prodCnt[7] != 0)
    setMbChecked(prodCnt[8] != 0)
    setVgaChecked(prodCnt[9] != 0)

    setFilterItem({
      ...filterItem, 
      cpu_cnt: prodCnt[1],
      ram_cnt: prodCnt[2],
      hdd_cnt: prodCnt[3],
      ssd_cnt: prodCnt[4],
      psu_cnt: prodCnt[5],
      cooler_cnt: prodCnt[6],
      cases_cnt: prodCnt[7],
      mainboard_cnt: prodCnt[8],
      vga_cnt:  prodCnt[9],
      prods: products
    })
  }

  const resetBuilderFilter = () => {
    const result = confirm("선택한 제품들을 초기화 하시겠습니까?")
    if(!result) {
      return
    }
    setCpuList(initProduct);
    setRamList(initProduct);
    setHddList(initProduct);
    setSsdList(initProduct);
    setPowerList(initProduct);
    setCoolerList(initProduct);
    setCaseList(initProduct);
    setMbList(initProduct);
    setVgaList(initProduct);

    setCpuChecked(true)
    setRamChecked(true)
    setHddChecked(false)
    setSsdChecked(true)
    setPowerChecked(true)
    setCoolerChecked(true)
    setCaseChecked(true)
    setMbChecked(true)
    setVgaChecked(true)
    
    setFilterItem({
      ...filterItem,
      prods: {},
      cpu_cnt: 1,
      ram_cnt: 1,
      hdd_cnt: 0,
      ssd_cnt: 1,
      psu_cnt: 1,
      cooler_cnt: 1,
      cases_cnt: 1,
      mainboard_cnt: 1,
      vga_cnt: 1,
    })
  }

  const getMyBuilderList = async () => {
    console.log("getMYBUILDER")
    const result = await getAllBuilderRequest();
    if(result?.data?.message == "success") {
      if(result?.data?.responseDto?.myBuilderDetailDtoList?.length != 0) {
        setMyBuilderList(result.data.responseDto.myBuilderDetailDtoList)
        // console.log( result.data.responseDto.myBuilderDetailDtoList[0].myBuilderDto)
      }
    } else {
      alert("견적 불러오기 실패")
    }
  }

  useEffect(() => {
    

    if(isLogin) {
      getMyBuilderList();
    }
  }, [])

  const notificateError = () => {
    openNotificationWithIcon('error', "해당 부품은 제외할 수 없습니다");
  }

  return (
    <>
      <div className={style["mybuilder-top-div"]}>
      {isLogin ?
        <div className={style["builder-box"]}>
          <Modal title="견적 저장하기" 
            open={isModalOpen} 
            onOk={saveBuilder} 
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
          <button className={style.button} onClick={showModal}>견적 저장하기</button>
          <select className={style["select-input"]} onChange={onMyBuilderChange}>
            <option value="-1">견적 선택하기</option>
            {myBuilderList.map((builder, idx)=>{
              return(
                <option key={idx} value={idx}>
                  {builder.myBuilderDto?.name}
                </option>
              );
            })}
          </select>
          <button className={style["mybuilder-reset-button"]} onClick={resetBuilderFilter}>견적 초기화</button>
        </div> : null }
      </div>
      <div className={style["product-selector-tool"]}>
        <div className={style["product-selector-tool-left"]}>제품 종류</div>
        <div>제품 선택</div>
      <div className={style["product-selector-tool-right"]}>수량</div>
      </div>
      <div className={style["content-box"]}>
        <ContentItem
          name="CPU"
          type="cpu"
          currTybeTab={currTybeTab}
          checkState={cpuChecked}
          checkSetter={() => {no}}
          contentList={cpuList}
          contentSetter={setCpuList}
          getProductsArray={getProductsArray}
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
          getProductsArray={getProductsArray}
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
          getProductsArray={getProductsArray}
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
          getProductsArray={getProductsArray}
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
          getProductsArray={getProductsArray}
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
          getProductsArray={getProductsArray}
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
        getProductsArray={getProductsArray}
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
        getProductsArray={getProductsArray}
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
          getProductsArray={getProductsArray}
          getTotalProds={getTotalProds}
          notificateError={notificateError}
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
