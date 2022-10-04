import React, { useEffect, useState } from "react";
import style from "@/styles/PopularBuilder.module.scss"
import { getPopularRecommendRequest } from "@/services/recommendService";
const PopluarBuilder = () => {

  const [builderList, setBuilderList] = useState([]);


  useEffect(() => {
    async function getPopularRecommend() {
      const result = await getPopularRecommendRequest();
      if(result?.data.message == "success") {
        console.log(result?.data?.responseList)
        setBuilderList(result?.data?.responseList);
      }
    }

    getPopularRecommend();
  }, []);

  useEffect(() => {
    // console.log("get success");
  }, [builderList])

  const getImgUrl = (caseObject) => {
    const prodInfo = Object.keys(caseObject)[0];
    if(prodInfo == undefined) { 
      return `/assets/default_case.jpg`; 
    }

    
    const prodIdx = prodInfo.split("_")[0];
    
    return `https://j7a602.p.ssafy.io/static/images/${prodIdx}/0.jpg`
    
  }

  const getCpuInfo = (builder) => {
    const cpuInfo = Object.keys(builder[0])[0];
    if(cpuInfo != undefined && cpuInfo.split("_").length > 1) {
      return cpuInfo.split("_")[1]
    } else {
      return "-"
    }
  }

  const getVgaInfo = (builder) => {
    const vgaInfo = Object.keys(builder[8])[0];
    if(vgaInfo != undefined && vgaInfo.split("_").length > 1) {
      return vgaInfo.split("_")[1]
    } else {
      return "-"
    }
  }

  const getMainBoardInfo = (builder) => {
    const vgaInfo = Object.keys(builder[7])[0];
    if(vgaInfo != undefined && vgaInfo.split("_").length > 1) {
      return vgaInfo.split("_")[1]
    } else {
      return "-"
    }
  }

  return (
    <div className={style['popular-builder-box']}>
      <div className={style['title-box']}>
        <p>인기 견적</p>
        <span> &gt;&gt; 다른견적</span>
      </div>
      <div className={style['builder-container']}>
        {builderList.map((builder, idx) => {
          
          return (
            <div key={idx} className={style['builder-content']}>
              <div className={style['builder-img']}>
                <img src={getImgUrl(builder.prodDetail[6])} alt=""/>
              </div>

              <div className={style['builder-info']}>
                <p className={style['builder-info-p']}>{getCpuInfo(builder.prodDetail)}</p>
                <p className={style['builder-info-p']}>{getVgaInfo(builder.prodDetail)}</p>
                <p className={style['builder-info-p']}>{getMainBoardInfo(builder.prodDetail)}</p>
                <p className={style['builder-info-price']}>{parseInt(builder.price/10000)} 만원</p>
              </div>
            </div>
            
          );
        })}
      </div>
    </div>
  );
}

export default PopluarBuilder;