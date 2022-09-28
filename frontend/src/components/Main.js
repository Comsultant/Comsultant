import React, { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "./layout/Navbar";
import Footer from "./layout/Footer";
import { useLocation } from "react-router-dom";

const Main = () => {
  const location = useLocation();
  const [isMain, setIsMain] = useState(false);

  useEffect(() => {
    if (location.pathname === '/') {
      setIsMain(true);
    } else {
      setIsMain(false);
    } 
  }, [location])
  return (
    <>
      
      <header className={isMain ? "header-main" : "header"}>
        <Navbar isMain={isMain} />
      </header>
      <main>
        <Outlet />
      </main>
      <footer>
        <Footer />
      </footer>
      
    </>
  );
};

export default Main;
