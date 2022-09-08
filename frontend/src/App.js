import React from "react";
import { Route, Routes } from "react-router-dom";
import Main from "./components/Main";

function App() {
  return (
    <Routes>
      <Route>
        <Route path="/" element={<Main />} />
      </Route>
    </Routes>
  );
}

export default App;
