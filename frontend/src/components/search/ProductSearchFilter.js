import React, { useState } from "react";
import Checkbox from "antd/lib/checkbox/Checkbox";
import style from "@/styles/ProductSeachFilter.module.scss"
import ProductFilterKorean from "@/tools/ProductFilterKorean";
import { useEffect } from "react";
import { AddtionalCoolerFilterList } from "@/tools/AddtionalCoolerFilterList";
import { AdditionalCasesFilterList } from "@/tools/AddtionalCasesFilterLIst";

const ProductFilter = ({ filterList, filterDetailList, currTypeTab, getProductFilterData, filterBody, setFilterBody, minPrice, maxPrice }) => {

  const [isClicked, setIsClicked] = useState(false);

  useEffect(() => {
    let init = {};
    switch (currTypeTab) {
      case '0':
        getProductFilterData("cpu");
        // setFilterBody(
        //   { corp: [], intelCpu: [], amdCpu: [], socket:[],core:[],name:"",price: [minPrice, maxPrice] }
        // );
        init = {
          "corp": [],
          "intelCpu": [],
          "amdCpu": [],
          "socket": [],
          "core": [],
          "name": "",
          "price": [minPrice, maxPrice]
        }
        break;
      case '1':
        getProductFilterData("mainboard");
        init = {
          "corp": [],
          "cpuSocket": [],
          "type": [],
          "detailChipset": [],
          "name": "",
          "price": [minPrice, maxPrice]
        };
        break;
      case '2':
        getProductFilterData("vga");
        init = {
            "corp": [],
            "chipsetCorp": [],
            "nvidia": [],
            "amd": [],
            "memoryVolume": [],
            "name": "",
            "price": [minPrice, maxPrice]
        };
        break;
      case '3':
        getProductFilterData("ram");
        init = {
          "corp": [],
          "useDevice": [],
          "type": [],
          "memoryVolume": [],
          "name": "",
          "price": [minPrice, maxPrice]
        };
        break;
      case '4':
        getProductFilterData("psu");
        init = {
          "corp": [],
          "type": [],
          "ratedPower": [],
          "name": "",
          "price": [minPrice, maxPrice]
        };
        break;
      case '5':
        getProductFilterData("ssd");
        init = {
          "corp": [],
          "formFactor": [],
          "volume": [],
          "memoryType": [],
          "name": "",
          "price": [minPrice, maxPrice]
        };
        break;
      case '6':
        getProductFilterData("hdd");
        init = {
          "corp": [],
          "diskVolume": [],
          "name": "",
          "price": [minPrice, maxPrice]
        }
        break;
      case '7':
        getProductFilterData("cases");
        init = {
          "corp": [],
          "classType": [],
          "size": [],
          "powerSize": [],
          // "extendedAtx": false,
          // "standardAtx": false,
          // "microAtx": false,
          // "flexAtx": false,
          // "standardItx": false,
          // "miniItx": false,
          // "ssiCeb": false,
          // "ssiEeb": false,
          // "miniDtx": false,
          "name": "",
          "price": [minPrice, maxPrice]
        }
        break;
      case '8':
        getProductFilterData("cooler");
        init = {
          "corp": [],
          "type": [],
          "coolingSystem": [],
          "coolerHeight": [],
          // "lga3647": false,
          // "lga2066": false,
          // "lga2011V3": false,
          // "lga2011": false,
          // "lga1700": false,
          // "lga1366": false,
          // "lga1200": false,
          // "lga115x": false,
          // "lga775": false,
          // "lga771": false,
          // "lga4677": false,
          // "lga4189": false,
          // "socket478": false,
          // "socket370": false,
          // "tr4": false,
          // "am5": false,
          // "am4": false,
          // "am3": false,
          // "am1": false,
          // "sp3": false,
          // "strx4": false,
          // "socket939": false,
          // "socket754": false,
          // "socket940": false,
          // "swrx8": false,
          // "socketa": false,
          // "socketf": false,
          // "fmxAmx": false,
          "name": "",
          "price": [minPrice, maxPrice]
        }
        break;
    }
    setFilterBody(init);
  }, [currTypeTab]);
  
  
  const onCheckBoxChanged = (e, key, value) => {
    let isChecked = e.target.checked;

    //추가
    if (isChecked) {      
      setFilterBody(
        { ...filterBody, [key]: [...filterBody[key], value] }
      );
    //제거
    } else {
      setFilterBody(
        { ...filterBody, [key] : filterBody[key].filter((curr) => curr != value)}
      )
    }
    
    
  }

  const onSelectChanged = (e, key) => {
    const value = e.target.value;
    if (value === '')
      return;
    //추가
    setFilterBody(
      { ...filterBody, [key]: [...filterBody[key], value] }
    );
     
  }

  const onAdditionalSelectChanged = (e) => {
    const key = e.target.value;
    if (key === '')
      return;
    setFilterBody(
      { ...filterBody, [key]: true }
    );
  }

  
  return (
    <div className={style['container']}>
      <div className={style["left-box"]}>
        <span>상세</span>
      </div>
      <div className={style['filter-list']}>
        <table className={style['filter-table']}>
          <tbody>
            {filterList?.map((filter, idx) => {
              return (
                      <tr key={idx}>
                        <td className={style['filter-name']}>
                          {ProductFilterKorean[filter]}
                        </td>
                  { 
                    filterDetailList[filter]?.length < 8 ? 
                      filterDetailList[filter].map((filterDetail, idx2) => {
                          return(
                            <td key={idx2} className={style['filter-detail-checkbox']}>
                              <Checkbox onChange={(e)=>onCheckBoxChanged(e, filter, filterDetail)}/>
                              {filterDetail}
                            </td>
                          );
                      }
                      ) :
                      <td>
                        <select
                          name={filter}
                          onChange={(e) => onSelectChanged(e, filter)}
                          className={'filter-detail-select'}
                        >
                          <option value=''>선택해주세요</option>
                          {filterDetailList[filter]?.map((filterDetail, idx2) => {
                            return (
                              <option key={idx2} value={filterDetail}>
                                {filterDetail}
                              </option>
                            );
                          })}
                        </select>
                      </td>
                  }
                      </tr>
                    
              );
            })}
            {
              currTypeTab === '7' ? 
                <tr>
                  <td>
                    지원 보드 규격
                  </td>
                  <td>
                    <select name="boardSize" onChange={onAdditionalSelectChanged}>
                      <option value=''>선택해주세요</option>
                    {AdditionalCasesFilterList.map((data, idx) => {
                      return (
                        <option key={idx} value={Object.keys(data)[0]}>
                          {Object.keys(data)[0]}
                        </option>
                      );
                    })}
                  </select>
                  </td>
                </tr>
                : currTypeTab === '8' ?
                  <tr>
                    <td>
                      소켓
                    </td> 
                    <td>
                      <select name="socket" onChange={onAdditionalSelectChanged}>
                        <option value=''>선택해주세요</option>
                        {AddtionalCoolerFilterList.map((data, idx) => {
                          return (
                            <option key={idx} value={Object.keys(data)[0]}>
                              {Object.keys(data)[0]}
                            </option>
                          )
                        })}
                      </select>
                    </td>
                  </tr>
                : null  
            }
            
          </tbody>
        </table>
      </div>
    </div >
  );
};

export default ProductFilter;
