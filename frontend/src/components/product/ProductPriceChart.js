import React, { useEffect, useState } from "react";
import { ResponsiveLine } from "@nivo/line";
import { Empty } from "antd";

const ProductPriceChart = ({ priceData }) => {
  const [data, setData] = useState();
  useEffect(() => {
    if (priceData == undefined) {
      return;
    }
    const obj = [
      {
        id: "price",
        color: "hsl(174, 70%, 50%)",
        data: priceData,
      },
    ];
    setData(obj);
  }, [priceData]);

  return (
    <>
          {priceData == undefined || priceData.length <= 0 ? (
              <div style={{display: 'flex', justifyContent:"center", alignItems:"center"}}>  
          <Empty description={"최근 시세 데이터가 없습니다"} />
              </div>
      ) : (
        <ResponsiveLine
          data={data}
          margin={{ top: 10, right: 30, bottom: 50, left: 60 }}
          colors={"rgb(65, 142, 249)"}
          xScale={{ type: "point" }}
          yScale={{
            type: "linear",
            min: "auto",
            max: "auto",
            stacked: true,
            reverse: false,
          }}
        />
      )}
    </>
  );
};

export default ProductPriceChart;
