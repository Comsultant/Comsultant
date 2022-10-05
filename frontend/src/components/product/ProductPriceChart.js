import React, { useEffect, useState } from "react";
import { ResponsiveLine } from '@nivo/line'



const ProductPriceChart = ({priceData}) => {
    
    const [data, setData] = useState();
    useEffect(()=> {
        if(priceData == undefined){
            return;
        }
        const obj = [{
            "id": "price",
            "color": "hsl(174, 70%, 50%)",
            "data": priceData,
        }];
        setData(obj);
    }, [priceData])

    return(
        <>
            <ResponsiveLine
                data={data}
                margin={{ top: 50, right: 110, bottom: 50, left: 60 }}
                xScale={{ type: 'point' }}
                yScale={{
                    type: 'linear',
                    min: 'auto',
                    max: 'auto',
                    stacked: true,
                    reverse: false
            }}
            />
        </>
    );
}

export default ProductPriceChart;