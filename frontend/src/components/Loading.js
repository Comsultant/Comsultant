import React from "react";
import { Alert, Spin } from 'antd';

const Loading = () => (
  <Spin tip="Loading...">
    <Alert
      message="로딩중..."
      description="데이터를 불러오고 있습니다. 잠시만 기다려주세요."
      type="info"
    />
  </Spin>
);

export default Loading;