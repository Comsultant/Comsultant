import React from "react";
import { Collapse } from 'antd';
import { useState } from "react";
import { reduce } from "lodash";
import style from "@/styles/DrawerBody.module.scss";
import DrawerBodyItem from "./DrawerBodyItem";
// import "@/styles/DrawerBody.scss";

// function getItem(label, key, icon, children, type) {
//   return {
//     key,
//     icon,
//     children,
//     label,
//     type,
//   };
// }

// const items = [
//   getItem('CPU', 'cpu', null, [
//     getItem('Option 1', '1'),
//     getItem('Option 2', '2'),
//     getItem('Option 3', '3'),
//     getItem('Option 4', '4'),
//   ]),
//   getItem('메인보드', 'mainboard', null, [
//     getItem('Option 5', '5'),
//     getItem('Option 6', '6'),
//     getItem('Option 7', '7'),
//     getItem('Option 8', '8'),
//   ]),
//   getItem('그래픽카드', 'vga', null, [
//     getItem('Option 9', '9'),
//     getItem('Option 10', '10'),
//     getItem('Option 11', '11'),
//   ]),
// ];

// const items = [
//   { label: 'CPU', key: 'cpu' }, // remember to pass the key prop
//   { label: '메인보드', key: 'mainboard' }, // which is required
//   {
//     label: '그래픽카드',
//     key: 'vga',
//     children: [{ label: 'item 3', key: 'submenu-item-1' }],
//   },
// ];

// const rootSubmenuKeys = ['cpu', 'mainboard', 'vga'];

const { Panel } = Collapse;

const DrawerBody = ({ currBuilder, setCurrBuilder, cpuList, setCpuList, ramList, setRamList, hddList, setHddList, ssdList, setSsdList, powerList, setPowerList, coolerList, setCoolerList, caseList, setCaseList, mbList, setMbList, vgaList, setVgaList }) => {

  // console.log(currBuilder);
  return (
    <>
      <Collapse defaultActiveKey={'1'}>
        <Panel header="CPU" key="1" >
          <DrawerBodyItem
            type={1}
            cpuList={cpuList}
            setCpuList={setCpuList}
            mbList={mbList}
            setMbList={setMbList}
            vgaList={vgaList}
            setVgaList={setVgaList}
            ramList={ramList}
            setRamList={setRamList}
            powerList={powerList}
            setPowerList={setPowerList}
            ssdList={ssdList}
            setSsdList={setSsdList}
            hddList={hddList}
            setHddList={setHddList}
            caseList={caseList}
            setCaseList={setCaseList}
            coolerList={coolerList}
            setCoolerList={setCoolerList}
        />
      </Panel>
      <Panel header="메인보드" key="2">
          <DrawerBodyItem
            type={8}
            cpuList={cpuList}
            setCpuList={setCpuList}
            mbList={mbList}
            setMbList={setMbList}
            vgaList={vgaList}
            setVgaList={setVgaList}
            ramList={ramList}
            setRamList={setRamList}
            powerList={powerList}
            setPowerList={setPowerList}
            ssdList={ssdList}
            setSsdList={setSsdList}
            hddList={hddList}
            setHddList={setHddList}
            caseList={caseList}
            setCaseList={setCaseList}
            coolerList={coolerList}
            setCoolerList={setCoolerList} />
      </Panel>
      <Panel header="그래픽카드" key="3">
          <DrawerBodyItem type={9}
          cpuList={cpuList}
          setCpuList={setCpuList}
          mbList={mbList}
          setMbList={setMbList}
          vgaList={vgaList}
          setVgaList={setVgaList}
          ramList={ramList}
          setRamList={setRamList}
          powerList={powerList}
          setPowerList={setPowerList}
          ssdList={ssdList}
          setSsdList={setSsdList}
          hddList={hddList}
          setHddList={setHddList}
          caseList={caseList}
          setCaseList={setCaseList}
          coolerList={coolerList}
          setCoolerList={setCoolerList}/>
      </Panel>
      <Panel header="RAM" key="4">
          <DrawerBodyItem type={2}
          cpuList={cpuList}
          setCpuList={setCpuList}
          mbList={mbList}
          setMbList={setMbList}
          vgaList={vgaList}
          setVgaList={setVgaList}
          ramList={ramList}
          setRamList={setRamList}
          powerList={powerList}
          setPowerList={setPowerList}
          ssdList={ssdList}
          setSsdList={setSsdList}
          hddList={hddList}
          setHddList={setHddList}
          caseList={caseList}
          setCaseList={setCaseList}
          coolerList={coolerList}
          setCoolerList={setCoolerList}/>
      </Panel>
      <Panel header="파워" key="5">
          <DrawerBodyItem type={5}
          cpuList={cpuList}
          setCpuList={setCpuList}
          mbList={mbList}
          setMbList={setMbList}
          vgaList={vgaList}
          setVgaList={setVgaList}
          ramList={ramList}
          setRamList={setRamList}
          powerList={powerList}
          setPowerList={setPowerList}
          ssdList={ssdList}
          setSsdList={setSsdList}
          hddList={hddList}
          setHddList={setHddList}
          caseList={caseList}
          setCaseList={setCaseList}
          coolerList={coolerList}
          setCoolerList={setCoolerList}/>
      </Panel>
      <Panel header="SSD" key="6">
          <DrawerBodyItem type={4}
          cpuList={cpuList}
          setCpuList={setCpuList}
          mbList={mbList}
          setMbList={setMbList}
          vgaList={vgaList}
          setVgaList={setVgaList}
          ramList={ramList}
          setRamList={setRamList}
          powerList={powerList}
          setPowerList={setPowerList}
          ssdList={ssdList}
          setSsdList={setSsdList}
          hddList={hddList}
          setHddList={setHddList}
          caseList={caseList}
          setCaseList={setCaseList}
          coolerList={coolerList}
          setCoolerList={setCoolerList}/>
      </Panel>
      <Panel header="HDD" key="7">
          <DrawerBodyItem type={3}
          cpuList={cpuList}
          setCpuList={setCpuList}
          mbList={mbList}
          setMbList={setMbList}
          vgaList={vgaList}
          setVgaList={setVgaList}
          ramList={ramList}
          setRamList={setRamList}
          powerList={powerList}
          setPowerList={setPowerList}
          ssdList={ssdList}
          setSsdList={setSsdList}
          hddList={hddList}
          setHddList={setHddList}
          caseList={caseList}
          setCaseList={setCaseList}
          coolerList={coolerList}
          setCoolerList={setCoolerList}/>
      </Panel>
      <Panel header="케이스" key="8">
          <DrawerBodyItem type={7}
          cpuList={cpuList}
          setCpuList={setCpuList}
          mbList={mbList}
          setMbList={setMbList}
          vgaList={vgaList}
          setVgaList={setVgaList}
          ramList={ramList}
          setRamList={setRamList}
          powerList={powerList}
          setPowerList={setPowerList}
          ssdList={ssdList}
          setSsdList={setSsdList}
          hddList={hddList}
          setHddList={setHddList}
          caseList={caseList}
          setCaseList={setCaseList}
          coolerList={coolerList}
          setCoolerList={setCoolerList}/>
      </Panel>
      <Panel header="쿨러/기타" key="9">
          <DrawerBodyItem type={6}
          cpuList={cpuList}
          setCpuList={setCpuList}
          mbList={mbList}
          setMbList={setMbList}
          vgaList={vgaList}
          setVgaList={setVgaList}
          ramList={ramList}
          setRamList={setRamList}
          powerList={powerList}
          setPowerList={setPowerList}
          ssdList={ssdList}
          setSsdList={setSsdList}
          hddList={hddList}
          setHddList={setHddList}
          caseList={caseList}
          setCaseList={setCaseList}
          coolerList={coolerList}
          setCoolerList={setCoolerList}/>
      </Panel>
      </Collapse>
    </>
       
  );
}

export default DrawerBody;