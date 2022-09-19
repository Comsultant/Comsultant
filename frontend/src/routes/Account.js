import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Regist from '@/components/account/Regist';
import Login from '@/components/account/Login';

const Account = () => {
    return (
        <Routes>
            <Route path="regist" element={<Regist />}/>
            <Route path="login" element={<Login />}/>
        </Routes>
    );
}

export default Account;