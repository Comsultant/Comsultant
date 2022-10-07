import React, { useEffect } from "react";
import style from "@/styles/RecommendList.module.scss";
import { Link, useNavigate, useParams } from "react-router-dom";
import { SET_RECOMMEND } from "@/reducer/type";
import { useDispatch, useSelector } from "react-redux";

const RecommendList = ({ item, filterItem }) => {
  const dispatch = useDispatch();

  const showBuilderDetail = () => {
    const savedData = item;
    dispatch({ type: SET_RECOMMEND, payload: savedData });
    window.open("/recommend/detail")
  };

  return (
    <div className={style["builder-box"]}>
      <table className={style["builder-table"]} onClick={showBuilderDetail}>
        <colgroup>
          <col style={{ width: "15%" }} />
          <col style={{ width: "80%" }} />
          <col style={{ width: "5%" }} />
        </colgroup>
        <thead className={style["builder-table-head"]}>
          <tr>
            <td>종류</td>
            <td>이름</td>
            <td>개수</td>
          </tr>
        </thead>
        <tbody className={style["builder-table-body"]}>
          {Object.keys(item.prodDetail[0]).map((cpu) => {
            let prodIdx = cpu.split("_")[0];
            let prodName = cpu.split("_")[1];
            return (
              <tr key={prodIdx}>
                <td>CPU</td>
                <td>{prodName}</td>
                <td>{item.prodDetail[0][cpu]}</td>
              </tr>
            );
          })}
          {Object.keys(item.prodDetail[8]).map((vga) => {
            let prodIdx = vga.split("_")[0];
            let prodName = vga.split("_")[1];
            return (
              <tr key={prodIdx}>
                <td>그래픽카드</td>
                <td>{prodName}</td>
                <td>{item.prodDetail[8][vga]}</td>
              </tr>
            );
          })}
          {Object.keys(item.prodDetail[7]).map((mainboard) => {
            let prodIdx = mainboard.split("_")[0];
            let prodName = mainboard.split("_")[1];
            return (
              <tr key={prodIdx}>
                <td>메인보드</td>
                <td>{prodName}</td>
                <td>{item.prodDetail[7][mainboard]}</td>
              </tr>
            );
          })}
          {Object.keys(item.prodDetail[4]).map((psu) => {
            let prodIdx = psu.split("_")[0];
            let prodName = psu.split("_")[1];
            return (
              <tr key={prodIdx}>
                <td>파워</td>
                <td>{prodName}</td>
                <td>{item.prodDetail[4][psu]}</td>
              </tr>
            );
          })}
          {Object.keys(item.prodDetail[1]).map((ram) => {
            let prodIdx = ram.split("_")[0];
            let prodName = ram.split("_")[1];
            return (
              <tr key={prodIdx}>
                <td>메모리</td>
                <td>{prodName}</td>
                <td>{item.prodDetail[1][ram]}</td>
              </tr>
            );
          })}
          {Object.keys(item.prodDetail[3]).map((ssd) => {
            let prodIdx = ssd.split("_")[0];
            let prodName = ssd.split("_")[1];
            return (
              <tr key={prodIdx}>
                <td>SSD</td>
                <td>{prodName}</td>
                <td>{item.prodDetail[3][ssd]}</td>
              </tr>
            );
          })}
          {Object.keys(item.prodDetail[2]).map((hdd) => {
            let prodIdx = hdd.split("_")[0];
            let prodName = hdd.split("_")[1];
            return (
              <tr key={prodIdx}>
                <td>HDD</td>
                <td>{prodName}</td>
                <td>{item.prodDetail[2][hdd]}</td>
              </tr>
            );
          })}
          {Object.keys(item.prodDetail[6]).map((cases) => {
            let prodIdx = cases.split("_")[0];
            let prodName = cases.split("_")[1];
            return (
              <tr key={prodIdx}>
                <td>케이스</td>
                <td>{prodName}</td>
                <td>{item.prodDetail[6][cases]}</td>
              </tr>
            );
          })}
          {Object.keys(item.prodDetail[5]).map((custom) => {
            let prodIdx = custom.split("_")[0];
            let prodName = custom.split("_")[1];
            return (
              <tr key={prodIdx}>
                <td>기타</td>
                <td>{prodName}</td>
                <td>{item.prodDetail[5][custom]}</td>
              </tr>
            );
          })}
        </tbody>
      </table>

      <div className={style["price-box"]}>
        <span>{parseInt(item.price / 10000)} 만원</span>
        {/* <span>{item.priceDate} </span> */}
      </div>
    </div>
  );
};

export default RecommendList;
