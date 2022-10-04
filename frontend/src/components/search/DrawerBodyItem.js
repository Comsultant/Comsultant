import React, { useEffect, useState } from "react";
import { InputNumber } from "antd";

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

  const onMinusClicked = (idx) => {
    switch (type) {
      case 1:
        const newCpuList = [...cpuList];
        newCpuList.map((curr) => {
          if (curr.productIdx === idx && curr.cnt > 1) {
            curr.cnt--;
          }
        })
        setCpuList([...cpuList])
        break;
    }
  }

  const onPlusClicked = (productIdx) => {
    switch (type) {
      case 1:
        let idx = -1;
        cpuList?.map((curr, i) => {
          if (curr.productIdx == productIdx) {
            idx = i;
          }
        })
        const newCpuList = [...cpuList];
        if (idx != -1) {
          newCpuList[idx] = { ...newCpuList[idx], cnt: cnt + 1 };
        }
        setCpuList({ newCpuList });
        // console.log(cpuList);
        // setCpuList([...newCpuList.map((curr) => {
        //   if (curr.productIdx == idx) {
        //     curr.cnt += 1;
        //   }
        // })])
        break;
    }
  }

  const onCntChange = (e) => {
    // console.log(e);
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
                <div>{product.productName}</div>
                <div style={{ display: 'flex', justifyContent: "center" }}>
                  <button onClick={()=>onMinusClicked(product.productIdx)}>-</button>
                  <input value={product.cnt} style={{ width: '25px' }} onChange={onCntChange} />
                  <button onClick={()=>onPlusClicked(product.productIdx)}>+</button>
                  <span>{product.price * product.cnt}원</span>
                  <span onClick={()=>onDeleteClicked(product.productIdx)}>X</span>
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
                <div>{product.productName}</div>
                <div>{product.price * product.cnt}원</div>
                <div>{product.cnt}</div>
              </div>
            );
          })}
        </div>
      ) : type == 3 ? (
        <div>
          {hddList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div>{product.productName}</div>                
                <div>{product.cnt} <span>{product.price * product.cnt}원</span></div>
              </div>
            );
          })}
        </div>
      ) : type == 4 ? (
        <div>
          {ssdList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div>{product.productName}</div>
                <div>{product.price * product.cnt}원</div>
                <div>{product.cnt}</div>
              </div>
            );
          })}
        </div>
      ) : type == 5 ? (
        <div>
          {powerList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div>{product.productName}</div>
                <div>{product.price * product.cnt}원</div>
                <div>{product.cnt}</div>
              </div>
            );
          })}
        </div>
      ) : type == 6 ? (
        <div>
          {coolerList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div>{product.productName}</div>
                <div>{product.price * product.cnt}원</div>
                <div>{product.cnt}</div>
              </div>
            );
          })}
        </div>
      ) : type == 7 ? (
        <div>
          {caseList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div>{product.productName}</div>
                <div>{product.price * product.cnt}원</div>
                <div>{product.cnt}</div>
              </div>
            );
          })}
        </div>
      ) : type == 8 ? (
        <div>
          {mbList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div>{product.productName}</div>
                <div>{product.price * product.cnt}원</div>
                <div>{product.cnt}</div>
              </div>
            );
          })}
        </div>
      ) : type == 9 ? (
        <div>
          {vgaList?.map((product, idx) => {
            return (
              <div key={idx}>
                <div>{product.productName}</div>
                <div>{product.price * product.cnt}원</div>
                <div>{product.cnt}</div>
              </div>
            );
          })}
        </div>
      ) : null}
    </>
  );
};

export default DrawerBodyItem;
