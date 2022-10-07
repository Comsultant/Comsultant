import React, { useEffect, useState } from "react";
import { notification } from "antd";
import style from "@/styles/DrawerBodyItem.module.scss";
import { MinusOutlined, PlusOutlined, CloseOutlined } from "@ant-design/icons";
import { postBuilderCheckRequest } from "@/services/builderService";
import CompatibilityMapper from "@/tools/CompatibilityMapper";

const DrawerBodyItem = ({
  type,
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
}) => {

  const [cnt, setCnt] = useState(0);

  const openNotificationWithIcon = (type, desc) => {
    notification[type]({
      message: "호환성 문제",
      description: desc,
    });
  };

  const getCurrBuilderList = () => {
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

    return builderProducts;
  }

  const onMinusClicked = async(productIdx) => {
    const builderProducts = getCurrBuilderList();
    let tmpIdx = -1;
    builderProducts.map((curr, i) => {
      if (curr.productIdx == productIdx) {
        tmpIdx = i;
      } 
    })
    builderProducts[tmpIdx] = { productIdx, cnt: builderProducts[tmpIdx].cnt - 1 };
    const dataToSubmit = {
      products: [...builderProducts.filter((curr) => curr)],
    }
    const result = await postBuilderCheckRequest(dataToSubmit);
    if (result?.data?.message !== "success") {
      openNotificationWithIcon('error', CompatibilityMapper[result.data.message]);
      return;
    }

    let idx = -1;
    switch (type) {
      case 1:
        cpuList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        if(cpuList[idx].cnt <= 1){
          return;
        } 
        const newCpuList = [...cpuList];
        if (idx != -1) {
          newCpuList[idx] = { ...newCpuList[idx], cnt: newCpuList[idx].cnt - 1 };
        }
        setCpuList(newCpuList);
        break;
      case 2:
        ramList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        if(ramList[idx].cnt <= 1){
          return;
        } 
        const newRamList = [...ramList];
        if (idx != -1) {
          newRamList[idx] = { ...newRamList[idx], cnt: newRamList[idx].cnt - 1 };
        }
        setRamList(newRamList);
        break;
      case 3:
        hddList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        if(hddList[idx].cnt <= 1){
          return;
        } 
        const newHddList = [...hddList];
        if (idx != -1) {
          newHddList[idx] = { ...newHddList[idx], cnt: newHddList[idx].cnt - 1 };
        }
        setHddList(newHddList);
        break;
      case 4:        
        ssdList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        if(ssdList[idx].cnt <= 1){
          return;
        } 
        const newSsdList = [...ssdList];
        if (idx != -1) {
          newSsdList[idx] = { ...newSsdList[idx], cnt: newSsdList[idx].cnt - 1 };
        }
        setSsdList(newSsdList);
        break;
      case 5:
        powerList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        if(powerList[idx].cnt <= 1){
          return;
        } 
        const newPowerList = [...powerList];
        if (idx != -1) {
          newPowerList[idx] = { ...newPowerList[idx], cnt: newPowerList[idx].cnt - 1 };
        }
        setPowerList(newPowerList);
        break;
      case 6:
        coolerList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        if(coolerList[idx].cnt <= 1){
          return;
        } 
        const newCoolerList = [...coolerList];
        if (idx != -1) {
          newCoolerList[idx] = { ...newCoolerList[idx], cnt: newCoolerList[idx].cnt - 1 };
        }
        setCoolerList(newCoolerList);
        break;
      case 7:
        caseList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        if(caseList[idx].cnt <= 1){
          return;
        } 
        const newCaseList = [...caseList];
        if (idx != -1) {
          newCaseList[idx] = { ...newCaseList[idx], cnt: newCaseList[idx].cnt - 1 };
        }
        setCaseList(newCaseList);
        break;
        case 8:
          mbList?.map((curr, i) => {
            if (curr.productIdx == productIdx) {
              idx = i;
            }
          })
          if(mbList[idx].cnt <= 1){
            return;
          } 
          const newMbList = [...mbList];
          if (idx != -1) {
            newMbList[idx] = { ...newMbList[idx], cnt: newMbList[idx].cnt - 1 };
          }
          setMbList(newMbList);
        break;
        case 9:
          vgaList?.map((curr, i) => {
            if (curr.productIdx == productIdx) {
              idx = i;
            }
          })
          if(vgaList[idx].cnt <= 1){
            return;
          } 
          const newVgaList = [...vgaList];
          if (idx != -1) {
            newVgaList[idx] = { ...newVgaList[idx], cnt: newVgaList[idx].cnt - 1 };
          }
          setVgaList(newVgaList);
          break;
    }
  }

  const onPlusClicked = async(productIdx) => {
    const builderProducts = getCurrBuilderList();
    let tmpIdx = -1;
    builderProducts.map((curr, i) => {
      if (curr.productIdx == productIdx) {
        tmpIdx = i;
      } 
    })
    builderProducts[tmpIdx] = { productIdx, cnt: builderProducts[tmpIdx].cnt + 1 };
    const dataToSubmit = {
      products: [...builderProducts.filter((curr) => curr)],
    }
    const result = await postBuilderCheckRequest(dataToSubmit);
    if (result?.data?.message !== "success") {
      openNotificationWithIcon('error', CompatibilityMapper[result.data.message]);
      return;
    }
    let idx = -1;
    switch (type) {
      case 1:
        cpuList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        const newCpuList = [...cpuList];
        if (idx != -1) {
          newCpuList[idx] = { ...newCpuList[idx], cnt: newCpuList[idx].cnt + 1 };
        }
        setCpuList(newCpuList);
        break;
      case 2:
        ramList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        const newRamList = [...ramList];
        if (idx != -1) {
          newRamList[idx] = { ...newRamList[idx], cnt: newRamList[idx].cnt + 1 };
        }
        setRamList(newRamList);
        break;
      case 3:
        hddList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        const newHddList = [...hddList];
        if (idx != -1) {
          newHddList[idx] = { ...newHddList[idx], cnt: newHddList[idx].cnt + 1 };
        }
        setHddList(newHddList);
        break;
      case 4:
        ssdList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        const newSsdList = [...ssdList];
        if (idx != -1) {
          newSsdList[idx] = { ...newSsdList[idx], cnt: newSsdList[idx].cnt + 1 };
        }
        setSsdList(newSsdList);
        break;
      case 5:
        powerList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        const newPowerList = [...powerList];
        if (idx != -1) {
          newPowerList[idx] = { ...newPowerList[idx], cnt: newPowerList[idx].cnt + 1 };
        }
        setPowerList(newPowerList);
        break;
      case 6:
        coolerList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        const newCoolerList = [...coolerList];
        if (idx != -1) {
          newCoolerList[idx] = { ...newCoolerList[idx], cnt: newCoolerList[idx].cnt + 1 };
        }
        setCoolerList(newCoolerList);
        break;
      case 7:
        caseList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        const newCaseList = [...caseList];
        if (idx != -1) {
          newCaseList[idx] = { ...newCaseList[idx], cnt: newCaseList[idx].cnt + 1 };
        }
        setCaseList(newCaseList);
        break;
        case 8:
          mbList?.map((curr, i) => {
            if (curr.productIdx == productIdx) {
              idx = i;
            }
          })
          const newMbList = [...mbList];
          if (idx != -1) {
            newMbList[idx] = { ...newMbList[idx], cnt: newMbList[idx].cnt + 1 };
          }
          setMbList(newMbList);
        break;
        case 9:
          vgaList?.map((curr, i) => {
            if (curr.productIdx == productIdx) {
              idx = i;
            }
          })
          const newVgaList = [...vgaList];
          if (idx != -1) {
            newVgaList[idx] = { ...newVgaList[idx], cnt: newVgaList[idx].cnt + 1 };
          }
          setVgaList(newVgaList);
          break;
    }
  }

  const onCntChange = (e) => {
  }

  const onDeleteClicked = (idx) => {
    setCpuList(cpuList?.filter((curr) => curr.productIdx != idx));
    setRamList(ramList?.filter((curr) => curr.productIdx != idx));
    setHddList(hddList?.filter((curr) => curr.productIdx != idx));
    setSsdList(ssdList?.filter((curr) => curr.productIdx != idx));
    setPowerList(powerList?.filter((curr) => curr.productIdx != idx));
    setCoolerList(coolerList?.filter((curr) => curr.productIdx != idx));
    setCaseList(caseList?.filter((curr) => curr.productIdx != idx));
    setMbList(mbList?.filter((curr) => curr.productIdx != idx));
    setVgaList(vgaList?.filter((curr) => curr.productIdx != idx));
  }
  return (
    <>
      {type == 1 ? (
        <div>
          {cpuList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div className={style['product-name']}>{product.productName}</div>
                <div style={{ display: 'flex', justifyContent:"space-between" }}>
                  <div>
                    <button onClick={()=>onMinusClicked(product.productIdx)}><MinusOutlined /></button>
                    <input value={product.cnt} style={{ width: '25px' }} onChange={onCntChange} />
                    <button onClick={()=>onPlusClicked(product.productIdx)}><PlusOutlined /></button>
                  </div>
                  <div>
                    <span className={style['price']}>{(product.price * product.cnt).toLocaleString()}원</span>
                    <span className={style['delete-button']} onClick={()=>onDeleteClicked(product.productIdx)}><CloseOutlined /></span>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      ) : type == 2 ? (
        <div>
          {ramList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div className={style['product-name']}>{product.productName}</div>
                <div style={{ display: 'flex', justifyContent:"space-between" }}>
                  <div>
                    <button onClick={()=>onMinusClicked(product.productIdx)}><MinusOutlined /></button>
                    <input value={product.cnt} style={{ width: '25px' }} onChange={onCntChange} />
                    <button onClick={()=>onPlusClicked(product.productIdx)}><PlusOutlined /></button>
                  </div>
                  <div>
                    <span className={style['price']}>{(product.price * product.cnt).toLocaleString()}원</span>
                    <span className={style['delete-button']} onClick={()=>onDeleteClicked(product.productIdx)}><CloseOutlined /></span>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      ) : type == 3 ? (
        <div>
          {hddList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div className={style['product-name']}>{product.productName}</div>
                <div style={{ display: 'flex', justifyContent:"space-between" }}>
                  <div>
                    <button onClick={()=>onMinusClicked(product.productIdx)}><MinusOutlined /></button>
                    <input value={product.cnt} style={{ width: '25px' }} onChange={onCntChange} />
                    <button onClick={()=>onPlusClicked(product.productIdx)}><PlusOutlined /></button>
                  </div>
                  <div>
                    <span className={style['price']}>{(product.price * product.cnt).toLocaleString()}원</span>
                    <span className={style['delete-button']} onClick={()=>onDeleteClicked(product.productIdx)}><CloseOutlined /></span>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      ) : type == 4 ? (
        <div>
          {ssdList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div className={style['product-name']}>{product.productName}</div>
                <div style={{ display: 'flex', justifyContent:"space-between" }}>
                  <div>
                    <button onClick={()=>onMinusClicked(product.productIdx)}><MinusOutlined /></button>
                    <input value={product.cnt} style={{ width: '25px' }} onChange={onCntChange} />
                    <button onClick={()=>onPlusClicked(product.productIdx)}><PlusOutlined /></button>
                  </div>
                  <div>
                    <span className={style['price']}>{(product.price * product.cnt).toLocaleString()}원</span>
                    <span className={style['delete-button']} onClick={()=>onDeleteClicked(product.productIdx)}><CloseOutlined /></span>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      ) : type == 5 ? (
        <div>
          {powerList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div className={style['product-name']}>{product.productName}</div>
                <div style={{ display: 'flex', justifyContent:"space-between" }}>
                  <div>
                    <button onClick={()=>onMinusClicked(product.productIdx)}><MinusOutlined /></button>
                    <input value={product.cnt} style={{ width: '25px' }} onChange={onCntChange} />
                    <button onClick={()=>onPlusClicked(product.productIdx)}><PlusOutlined /></button>
                  </div>
                  <div>
                    <span className={style['price']}>{(product.price * product.cnt).toLocaleString()}원</span>
                    <span className={style['delete-button']} onClick={()=>onDeleteClicked(product.productIdx)}><CloseOutlined /></span>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      ) : type == 6 ? (
        <div>
          {coolerList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div className={style['product-name']}>{product.productName}</div>
                <div style={{ display: 'flex', justifyContent:"space-between" }}>
                  <div>
                    <button onClick={()=>onMinusClicked(product.productIdx)}><MinusOutlined /></button>
                    <input value={product.cnt} style={{ width: '25px' }} onChange={onCntChange} />
                    <button onClick={()=>onPlusClicked(product.productIdx)}><PlusOutlined /></button>
                  </div>
                  <div>
                    <span className={style['price']}>{(product.price * product.cnt).toLocaleString()}원</span>
                    <span className={style['delete-button']} onClick={()=>onDeleteClicked(product.productIdx)}><CloseOutlined /></span>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      ) : type == 7 ? (
        <div>
          {caseList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div className={style['product-name']}>{product.productName}</div>
                <div style={{ display: 'flex', justifyContent:"space-between" }}>
                  <div>
                    <button onClick={()=>onMinusClicked(product.productIdx)}><MinusOutlined /></button>
                    <input value={product.cnt} style={{ width: '25px' }} onChange={onCntChange} />
                    <button onClick={()=>onPlusClicked(product.productIdx)}><PlusOutlined /></button>
                  </div>
                  <div>
                    <span className={style['price']}>{(product.price * product.cnt).toLocaleString()}원</span>
                    <span className={style['delete-button']} onClick={()=>onDeleteClicked(product.productIdx)}><CloseOutlined /></span>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      ) : type == 8 ? (
        <div>
          {mbList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div className={style['product-name']}>{product.productName}</div>
                <div style={{ display: 'flex', justifyContent:"space-between" }}>
                  <div>
                    <button onClick={()=>onMinusClicked(product.productIdx)}><MinusOutlined /></button>
                    <input value={product.cnt} style={{ width: '25px' }} onChange={onCntChange} />
                    <button onClick={()=>onPlusClicked(product.productIdx)}><PlusOutlined /></button>
                  </div>
                  <div>
                    <span className={style['price']}>{(product.price * product.cnt).toLocaleString()}원</span>
                    <span className={style['delete-button']} onClick={()=>onDeleteClicked(product.productIdx)}><CloseOutlined /></span>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      ) : type == 9 ? (
        <div>
          {vgaList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div className={style['product-name']}>{product.productName}</div>
                <div style={{ display: 'flex', justifyContent:"space-between" }}>
                  <div>
                    <button onClick={()=>onMinusClicked(product.productIdx)}><MinusOutlined /></button>
                    <input value={product.cnt} style={{ width: '25px' }} onChange={onCntChange} />
                    <button onClick={()=>onPlusClicked(product.productIdx)}><PlusOutlined /></button>
                  </div>
                  <div>
                    <span className={style['price']}>{(product.price * product.cnt).toLocaleString()}원</span>
                    <span className={style['delete-button']} onClick={()=>onDeleteClicked(product.productIdx)}><CloseOutlined /></span>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      ) : null}
    </>
  );
};

export default DrawerBodyItem;
