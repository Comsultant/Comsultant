import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Regist from '@/components/account/Regist';
import Login from '@/components/account/Login';
import MyPage from "@/components/account/mypage/MyPage";

const Account = () => {
    return (
        <Routes>
            <Route path="regist" element={<Regist />}/>
            <Route path="login" element={<Login />} />
            <Route path="mypage" element={<MyPage />}/>
        </Routes>
    );
}

export default Account;