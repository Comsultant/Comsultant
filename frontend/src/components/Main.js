import React from "react";
import { Outlet } from "react-router-dom";
import Navbar from "./layout/Navbar";
import { Layout } from "antd";
const { Header, Footer, Content } = Layout;

const Main = () => {
  return (
    <>
      
      <header>
        <Navbar />
      </header>
      <main>
        <Outlet />
      </main>
      <footer>
        <div>Hello I'm Footer!</div>
        <div>Comsultunt ©2022 Created by SSAFY A602</div>
      </footer>
      
      {/* <Layout>
        <Header
          style={{
            position: "fixed",
            zIndex: 1,
            width: "100%",
          }}
        >
          <Navbar />
        </Header>

        <Content
        style={{
          padding: '100px 50px',
        }}
        > 
          <div className="site-layout-content">
            <Outlet />
          </div>
        </Content>

        <Footer
          style={{
            textAlign: "center",
          }}
        >
          <div>Hello I'm Footer!</div>
          <div>Comsultunt ©2022 Created by SSAFY A602</div>
        </Footer>
      </Layout> */}
    </>
  );
};

export default Main;
