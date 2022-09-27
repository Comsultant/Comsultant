import React, { useEffect, useState } from "react";
import style from "@/styles/ProductSelector.module.scss";
import { InputNumber, Modal } from "antd";
import {
  InfoCircleOutlined,
  PlusSquareOutlined,
  CloseCircleOutlined,
} from "@ant-design/icons";
import CustomCheckbox from "../CustomCheckbox";
import RecommendModal from "./RecommendModal";

const checkboxColor = "pink";

const ContentItem = ({checkState, numberState, checkSetter, numberSetter, contentList, contentSetter, name}) => {
  
  const onChangeCount = (curr, content, idx) =>{
    const productList = contentList;
    const product = {
      id:content.id, 
      name:content.name, 
      count: curr
    };
    productList[idx] = product;
    contentSetter(productList);
  }

  const initPorduct = {id:"", name:"", count:"", price:0};
  const [open, setOpen] = useState(false);
  const [confirmLoading, setConfirmLoading] = useState(false);
  const [currProduct, setCurrProduct] = useState("");

  const showModal = (idx) => {
    setCurrProduct(contentList[idx]);
    setOpen(true);
  };

  const handleOk = () => {
    setConfirmLoading(true);
    setTimeout(() => {
      setOpen(false);
      setConfirmLoading(false);
    }, 1000);
  };

  const handleCancel = () => {
    setOpen(false);
  };

  return (
    <>
    <Modal
        title={name}
        open={open}
        onOk={handleOk}
        confirmLoading={confirmLoading}
        onCancel={handleCancel}
        width={"80%"}
        centered
        bodyStyle={{"height" : "80vh", overflowY: 'scroll'}}
        zIndex={2000}
        okText="확인"
        cancelText="취소"
        >
          <RecommendModal currProduct={currProduct}/>
      </Modal>
    <div className={style["content-item"]}>
        
      <div className={style["item-left-box"]}>
        <InfoCircleOutlined />
        <CustomCheckbox
          backgroundColor={checkboxColor}
          state={checkState}
          setter={checkSetter}
        />
        <span className={style.title}>{name}</span>
        <PlusSquareOutlined className={style["plus-button"]} />
        </div>      
        <div className={style['item-right-box']}>  
        {contentList.map((content, idx)=>{
          return(
            <div key={idx} className={style['product-item']}>            
              <div className={style["item-selector"]}>
                <div className={style["item-input-box"]} onClick={()=>showModal(idx)}>
                  <input 
                  disabled={true} 
                  className={style['product-input']}
                  value={content?.name !== "" ? content.name : ""}
                  />
                </div>
                <CloseCircleOutlined className={style["delete-button"]} />
              </div>
              {
                content.id !== "" ? <div>
                <button className={style["detail-button"]}>상세보기</button>
                </div> : null
              }
              
              <div>
                <InputNumber
                  className={style["input-number"]}
                  min={1}
                  defaultValue={content.count}
                  onChange={(curr)=> {
                    onChangeCount(curr, content, idx);
                  }}
                />
              </div>
            </div>
          );
        })
          }
          </div>
      </div>
      </>
  );
};

export default ContentItem;