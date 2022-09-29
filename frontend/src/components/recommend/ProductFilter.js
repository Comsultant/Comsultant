import React from "react";
import Checkbox from "antd/lib/checkbox/Checkbox";
import style from "@/styles/ProductFilter.module.scss"
import ProductFilterKorean from "@/tools/ProductFilterKorean";

const ProductFilter = ({filterList, filterDetailList}) => {
  return (
    <>
      <div className={style["left-box"]}>
        <span>상세</span>
      </div>
      <div className={style['filter-list']}>
        <table className={style['filter-table']}>
          <tbody>
            {filterList.map((filter, idx) => {
              return (
                      <tr key={idx}>
                        <td>
                          {ProductFilterKorean[filter]}
                        </td>
                  { 
                    filterDetailList[idx].length < 5 ? 
                      filterDetailList[idx].map((filterDetail, idx2) => {
                          return(
                            <td key={idx2}>
                              <Checkbox />
                              {filterDetail}
                            </td>
                          );
                      }
                      ) :
                      <td>
                        <select name={filter}>
                          {filterDetailList[idx].map((filterDetail, idx2) => {
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
          </tbody>
        </table>
      </div>
    </>
  );
};

export default ProductFilter;
