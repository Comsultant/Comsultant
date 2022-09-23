import React from "react";
import { Outlet } from "react-router-dom";
import Navbar from "./layout/Navbar";
import Footer from "./layout/Footer";

const Main = () => {
  
  return (
    <>
      
      <header>
      </header>
      <main>
        <Navbar />
        <Outlet />
      </main>
      <footer>
        <Footer />
      </footer>
      
    </>
  );
};

export default Main;
