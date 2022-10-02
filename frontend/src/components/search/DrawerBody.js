import React from "react";
import { Menu } from 'antd';
import { useState } from "react";
import { reduce } from "lodash";
import style from "@/styles/DrawerBody.module.scss";
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

const DrawerBody = ({ currBuilder, setCurrBuilder }) => {
  // const [openKeys, setOpenKeys] = useState(['cpu']);

  // const onOpenChange = (keys) => {
  //   const latestOpenKey = keys.find((key) => openKeys.indexOf(key) === -1);

  //   if (rootSubmenuKeys.indexOf(latestOpenKey) === -1) {
  //     setOpenKeys(keys);
  //   } else {
  //     setOpenKeys(latestOpenKey ? [latestOpenKey] : []);
  //   }
  // }
  console.log(currBuilder);
  return (
    <div className={style['container']}>
        <div className={style['product-box']}>
          <div className={style['title']}>
            <span>CPU</span>
          </div>
          <div className={style['product']}>
            hello
          </div>
        </div>

        <div className={style['item']}>
          <div className={style['title']}>
            <span>메인보드</span>
          </div>
        </div>

      <div className={style['title']}>
        <span>그래픽카드</span>
      </div>

      <div className={style['title']}>
        <span>RAM</span>
      </div>

      <div className={style['title']}>
        <span>파워</span>
      </div>

      <div className={style['title']}>
        <span>SSD</span>
      </div>

      <div className={style['title']}>
        <span>HDD</span>
      </div>

      <div className={style['title']}>
        <span>케이스</span>
      </div>

      <div className={style['title']}>
        <span>쿨러/기타</span>
      </div>
    </div>
    // <>
    /* <Menu
      mode="inline"
      openKeys={openKeys}
      onOpenChange={onOpenChange}
      // style={{
      //   backgroundColor: 'rgb(227, 62, 56)',
      //   color: 'white'
      //   // width: 256,
      // }}
      items={items}
      /> */

      /* <Menu
          mode="inline">
      <Menu.Item>CPU</Menu.Item>
      <Menu.Item>그래픽카드</Menu.Item>
      <Menu.SubMenu title="sub menu">
          <Menu.Item>
            <div>
              <div>hello</div>
              <div>10000원</div>
            </div>
          </Menu.Item>
      </Menu.SubMenu>
    </Menu> */
    // </>
       
  );
}

export default DrawerBody;