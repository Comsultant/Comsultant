import React from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import style from "@/styles/RecommendBuilder.module.scss"
import { RightOutlined } from '@ant-design/icons';

const RecommendBuilder = () => {
  const builderList = [
  {
    idx: "16741211",
    name: "AMD 라이젠5-4세대 5600 (버미어)",
    type: "0"
  },{
    idx: "12822224",
    name: "이엠텍 지포스 RTX 3060 Ti STORM X Dual OC D6 8GB",
    type: "2"
  },{
    idx: "11541857",
    name: "삼성전자 DDR4-3200 (8GB)",
    type: "3"
  },{
    idx: "16101353",
    name: "인텔 코어i5-12세대 12400F (엘더레이크)",
    type: "0"
  }];

  return (
    <div className={style['popular-builder-box']}>
      <div className={style['title-box']}>
        <p>신상품</p>
        <span className={style['main-product-more']}><Link to='/product/search'>부품 검색<RightOutlined /></Link></span>
      </div>
      <div className={style['builder-container']}>
        {builderList.map((builder, idx) => {
          return (
            <div key={idx} className={style['builder-content']}>
              <Link to={`/product/info?idx=${builder.idx}&type=${builder.type}`}>
                <div className={style['builder-img']}>
                  <img src={`https://j7a602.p.ssafy.io/static/images/${builder.idx}/0.jpg`} alt=""/>
                </div>
              </Link>
              <Link to={`/product/info?idx=${builder.idx}&type=${builder.type}`}>
                <div className={style['builder-info']}>
                  <p className={style['builder-info-p']}>{builder.name}</p>
                </div>
              </Link>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default RecommendBuilder;