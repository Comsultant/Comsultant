import React, { useRef, useState } from "react";
import { Button, DatePicker, Form, Input, Space } from "antd";
import style from "@/styles/Regist.module.scss";

const Regist = () => {
  const [email, setEmail] = useState("");
  const [isEmailValid, setIsEmailValid] = useState(false);

  const onFinish = values => {
    console.log("Success:", values);
  };

  const onFinishFailed = errorInfo => {
    console.log("Failed:", errorInfo);
  };

  const onEmailChanged = event => {
    const email = event.target.value.trim();
    const regEmail =
      /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    const isEmailValid = regEmail.test(email);

    setIsEmailValid(isEmailValid);

    if (isEmailValid) {
      setEmail(email);
      //이메일 중복체크하기
      console.log(email);
    }
  };

  return (
    <div className={style['regist-container']}>
      <div className={style.title}>
        <span>Regist</span>
      </div>
      <Form
        name="regist"
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
        layout="vertical"
        style={{ marginLeft: "auto", marginRight: "auto" }}
        className={style["regist-form"]}
      >
        <Form.Item
          label="이메일"
          name="이메일"
          rules={[
            {
              required: true,
              message: "이메일을 입력해주세요!",
            },
          ]}
        >
          <div className={style["horizon-container"]}>
            <div className={style.left}>
              <Input
                onChange={onEmailChanged}
                placeholder="이메일"
                className={style["space-input"]}
              />
            </div>
            <div className={style.right}>
              <Button type="primary" htmlType="submit">
                인증번호 전송
              </Button>
            </div>
          </div>
        </Form.Item>

        <Form.Item
          label="이메일 인증번호"
          name="이메일 인증번호"
          rules={[
            {
              required: true,
              message: "이메일을 인증해주세요!",
            },
          ]}
        >
          <div className={style["horizon-container"]}>
            <div className={style.left}>
              <Input placeholder="인증번호" className={style["space-input"]} />
            </div>
            <div className={style.right}>
              <Button type="primary" htmlType="submit">
                인증번호 확인
              </Button>
            </div>
          </div>
        </Form.Item>

        <Form.Item
          label="비밀번호"
          name="비밀번호"
          rules={[
            {
              required: true,
              message: "비밀번호를 입력해주세요!",
            },
          ]}
        >
          <div className={style.left}>
            <Input.Password placeholder="비밀번호" />
          </div>
        </Form.Item>

        <Form.Item
          label="비밀번호 확인"
          name="비밀번호 확인"
          rules={[
            {
              required: true,
              message: "비밀번호를 확인해주세요!",
            },
          ]}
        >
          <div className={style.left}>
            <Input.Password placeholder="비밀번호 확인" />
          </div>
        </Form.Item>

        <Form.Item
          label="닉네임"
          name="닉네임"
          rules={[
            {
              required: true,
              message: "닉네임을 입력해주세요!",
            },
          ]}
        >
          <div className={style.left}>
            <Input placeholder="닉네임" />
          </div>
        </Form.Item>

        <Form.Item label="출생년도">
          <DatePicker placeholder="출생년도" picker="year" />
        </Form.Item>

        <Form.Item
          wrapperCol={{
            offset: 8,
          }}
        >
          <Space>
            <Button type="primary" htmlType="submit">
              가입하기
            </Button>
            <Button type="primary" htmlType="submit">
              돌아가기
            </Button>
          </Space>
        </Form.Item>
      </Form>
    </div>
  );
};

export default Regist;
