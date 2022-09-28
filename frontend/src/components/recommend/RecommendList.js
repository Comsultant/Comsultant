import React from "react";
import style from "@/styles/RecommendList.module.scss"

const RecommendList = ({isRecommendPressed}) => {

  const recommendBuilderList = [
    {
      cpu: "인텔 코어i7-12세대 12700K",
      vga: "그래픽카드",
      ram: "램",
      mb: "메인보드",
      power: "파워",
      totalPrice: "1,123,000"
    },
    {
      cpu: "AMD",
      vga: "그래픽카드",
      ram: "램",
      mb: "메인보드",
      power: "파워",
      totalPrice: "1,123,000"
    },
    {
      cpu: "인텔 코어i7-12세대 1200K",
      vga: "그래픽카드",
      ram: "램",
      mb: "메인보드",
      power: "파워",
      totalPrice: "1,123,000"
    },
  ];
  return (
    <>
      {
        (isRecommendPressed && recommendBuilderList.length > 0) ?
        recommendBuilderList.map((item, idx) => {
        return (
          <div key={idx}  className={style['builder-item']}>
            <div className={style['builder-box']}>
              <div>
                <table className={style['builder-table']}>
                  <tbody>
                    <tr>
                      <td>CPU</td>
                      <td>{item.cpu}</td>
                    </tr>
                    <tr>
                      <td>그래픽카드</td>
                      <td>{item.vga}</td>
                    </tr>
                    <tr>
                      <td>RAM</td>
                      <td>{item.ram}</td>
                    </tr>
                    <tr>
                      <td>메인보드</td>
                      <td>{item.mb}</td>
                    </tr>
                    <tr>
                      <td>파워</td>
                      <td>{item.power}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div className={style['more-button']}>
                <span>...</span>
              </div>
            </div>
            <div className={style['price-box']}>
              <span>{item.totalPrice} 원</span>
            </div>
          </div>
        );
        }) : 
          isRecommendPressed ?
          <div className={style['no-result-box']}>
            <span>추천할수 있는 제품이 없습니다.</span><br/>
            <span>다음과 같은 경우 제품 추천 결과가 없을 수 있습니다.</span><br/>
            <span> • 내가 선택한 부품의 제품이 내가 선택한 용도의 최소 사양보다 낮은 경우</span><br/>
            <span> • 내가 설정한 가격이 너무 낮은 경우</span>
            </div>
            :
            null
    }     
    </>
  );
}

export default RecommendList;