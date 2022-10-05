import "./styles/App.scss"
import React from "react";
import { Route, Routes } from "react-router-dom";
import Main from "./components/Main";
import Home from "./components/Home";
import Account from "./routes/Account";
import Recommend from "./components/recommend/Recommend";
import Search from "./components/search/Search";
import SocailAuth from "./components/account/SocailAuth";
import ProductInfo from "./components/product/ProductInfo";
import RecommendDetail from "./components/recommend/RecommendDetail";

function App() {
  return (
    <Routes>
      <Route element={<Main />}>
          <Route index element={<Home />} />
          <Route path="/account/*" element={<Account />} />
          <Route path="/recommend/" element={<Recommend />} />
          <Route path="/recommend/detail" element={<RecommendDetail />}></Route>
          <Route path="/product/search" element={<Search />} />
          <Route path="/product/info" element={<ProductInfo />} />
      </Route>
      <Route path="/social/*" element={<SocailAuth />} />
    </Routes>
  );
}

export default App;
