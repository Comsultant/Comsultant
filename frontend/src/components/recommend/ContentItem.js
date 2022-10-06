import React, { useEffect, useState } from "react";
import style from "@/styles/ProductSelector.module.scss";
import { InputNumber, Modal } from "antd";
import {
  InfoCircleOutlined,
  PlusSquareOutlined,
  MinusSquareOutlined,
  CloseCircleOutlined,
} from "@ant-design/icons";
import CustomCheckbox from "../CustomCheckbox";
import RecommendModal from "./RecommendModal";

const checkboxColor = "#377BB9";

const ContentItem = ({checkState, checkSetter, contentList, contentSetter, name, type, currTypeTab}) => {
  
  const onChangeCount = (curr, idx) =>{
    contentSetter((contentList) => contentList.map((content, i) => i == idx ? {...content, count: curr} : content));
  }

  const initProduct = {id:"", name:"", count:1, price:0};
  const [open, setOpen] = useState(false);
  const [confirmLoading, setConfirmLoading] = useState(false);
  const [currIdx, setCurrIdx] = useState(0);
  const [pickProduct, setPickProduct] = useState({id:"", name:"", count:1, price:0});
  const productType = {"cpu" : 0, "vga" : 2, "ram" : 3, "hdd": 6, "ssd": 5, "psu": 4, "cooler": 8, "cases": 7, "mainboard": 1};

  const showModal = (idx) => {
    setCurrIdx(idx);
    setOpen(true);
  };

  const handleOk = () => {
    setConfirmLoading(true);
    setTimeout(() => {
      setOpen(false);
      setConfirmLoading(false);
      contentSetter((contentList) =>
       contentList.map((item, idx) => 
        idx === currIdx ? { ...item, id: pickProduct.id, name: pickProduct.name, price: pickProduct.price } : item
       ));
      setPickProduct({id:"", name:"", count:1, price:0})
    }, 300);
    
  };

  const handleCancel = () => {
    setOpen(false);
  };

  const onPlusButtonClicked = () => {
    contentSetter([...contentList, initProduct]);
  }

  const onDeleteButtonClicked = (i) => {
    if(i <= 0)
      return;
    contentSetter(contentList.filter((content, idx) => idx !== i))
  }

  const deletePickProduct = (idx) => {
    console.log(contentList, idx)
    if(contentList[idx].id == '') {
      console.log("empty")
      return;
    }
    contentSetter(contentList.map((item, item_idx) => 
      item_idx == idx ? {... item, id: '', name: '', price: 0} : item
    ))
  }

  return (
    <>
    <Modal
        title={name}
        open={open}
        onOk={handleOk}
        confirmLoading={confirmLoading}
        onCancel={handleCancel}
        width={"80vw"}
        centered
        bodyStyle={
          {"height" : "70vh",
            "width" : "80vw",
          "overflowY": 'scroll',
          "padding": "0px",
          }}
        zIndex={2000}
        okText="확인"
        cancelText="취소"
        >
        <RecommendModal 
        pickProduct={pickProduct}
        setPickProduct={setPickProduct}
        type={type} />
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
        </div>      
        <div className={style['item-right-box']}>  
        {contentList.map((content, idx)=>{
          return(
            <div key={idx} className={style['product-item']}>
              {
                idx == 0 ?
                <PlusSquareOutlined 
                  className={checkState ? style["plus-button"] : style["plus-button-disabled"]}
                  onClick={onPlusButtonClicked} 
                /> : <MinusSquareOutlined 
                className={checkState ? style["plus-button"] : style["plus-button-disabled"]}
                onClick={() => onDeleteButtonClicked(idx)} 
              />
              }
                     
              <div className={style["item-selector"]}>
                <div className={style["item-input-box"]} onClick={checkState ? ()=>showModal(idx) : null}>
                  <input 
                  disabled={true} 
                  className={checkState ? style['product-input'] :style['product-input-disabled']  }
                  value={content?.name !== "" ? content.name : ""}
                  />
                </div>
                
                <CloseCircleOutlined 
                  className={checkState ? style["delete-button"] : style["delete-button-disabled"]} 
                  onClick={() => deletePickProduct(idx)}
                  // onClick={onDeleteButtonClicked}
                  // 이건 선택된 부품 삭제하는 거!!!!!
                />
              </div>
              {
                content.id !== "" ? <div>
                <button className={style["detail-button"]}><a target="_blank" href={`/product/info?idx=${content.id}&type=${productType[type]}`}>상세보기</a></button>
                </div> : null
              }
              
              <div className={ checkState ? style['product-item-right'] : style['product-item-right-disabled']}>
                <InputNumber
                  disabled={!checkState}
                  className={style["input-number"]}
                  min={1}
                  max={10}
                  value={content.count}
                  defaultValue={1}
                  onChange={(curr)=> {
                    onChangeCount(curr, idx);
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