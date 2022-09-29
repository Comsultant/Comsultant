import React from "react";
import Checkbox from "antd/lib/checkbox/Checkbox";
import style from "@/styles/ProductFilter.module.scss"


const ProductFilter = ({filterList, filterDetailList}) => {
  return (
    <>
      <div className={style["left-box"]}>
        <span>상세</span>
      </div>
      <div className={style['filter-list']}>
        <table>
          <tbody>
            {filterList.map((filter, idx) => {
              return (
                      <tr key={idx}>
                        <td>
                          {filter}
                        </td>
                        {filterDetailList[idx].map((filterDetail, idx2)=>{
                          return(
                            <td key={idx2}>
                              <Checkbox />
                              {filterDetail}
                            </td>
                          );
                        })}
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
