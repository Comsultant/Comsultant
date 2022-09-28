import "./styles/App.scss"
import React from "react";
import { Route, Routes } from "react-router-dom";
import Main from "./components/Main";
import Home from "./components/Home";
import Account from "./routes/account";


function App() {
  return (
    <Routes>
      <Route element={<Main />}>
          <Route index element={<Home />} />
          <Route path="/account/*" element={<Account />} />
      </Route>
    </Routes>
  );
}

export default App;
