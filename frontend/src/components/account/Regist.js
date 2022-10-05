import React, { useRef, useState, useEffect } from "react";
import { Button, DatePicker, Form, Input, Space, Alert, Modal, message } from "antd";
import { registRequest, sendAuthNumberEmail, verifyAuthNumber, checkEmailRequest, checkNickNameRequest } from "@/services/accountService";
import { debounce } from "lodash";
import style from "@/styles/Regist.module.scss";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

const Regist = () => {

  const authNumberLength = 6;
  const navigate = useNavigate();
  const isLogin = useSelector((state) => state.account.isLogin);

  const [form] = Form.useForm();
  const authNumberInput = useRef();
  const authCompleteMessage = useRef();
  const sendAuthNumberButton = useRef();

  const [email, setEmail] = useState("");
  const [authNumber, setAuthNumber] = useState("");
  const [password, setPassword] = useState("");
  const [passwordCheck, setPasswordCheck] = useState("");
  const [nickname, setNickname] = useState("");
  const [birthYear, setBirthYear] = useState("");
  
  const [isEmailValid, setIsEmailValid] = useState(false);
  const [isAuthVerify, setAuthVerify] = useState(false);
  const [isPasswordValid, setIsPasswordValid] = useState(false);
  const [isPasswordSame, setIsPasswordSame] = useState(false);
  const [isAuthNumberSended, setIsAuthNumberSended] = useState(false);
  const [isNicknameValid, setIsNicknameValid] = useState(false);
  const [isRegistSuccess, setRegistSucces] = useState(false);

  const [emailChecked, setEmailChecked] = useState(false);
  const [nicknameChecked, setNicknameChecked] = useState(false);

  const debounceFunc = debounce(async (value, request, setState) => {
    const result = await request(value)
    if (result?.data?.message) {
      setState(result?.data?.message)
    } else {
      countDown('올바르지 않은 접근입니다', false);
    };
  }, 500);

  const countDown = (title, isSuccess) => {
    let secondsToGo = 3;
    let modal;
    let content = `이 창은 ${secondsToGo} 초 뒤 사라집니다.`;
    if(isSuccess){
      modal = Modal.success({
        title : `${title}`,
        content,
      });
    }else{
      modal = Modal.error({
        title : `${title}`,
        content,
      });
    }

    const timer = setInterval(() => {
      secondsToGo -= 1;
      modal.update({
        content: `이 창은 ${secondsToGo} 초 뒤 사라집니다.`,
      });
    }, 1000);

    setTimeout(() => {
      clearInterval(timer);
      modal.destroy();
    }, secondsToGo * 1000);
  }

  // const onFinish = values => {
  //   console.log("Success:", values);
  // };

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
      setEmailChecked(false);
      debounceFunc(
        email.trim(),
        checkEmailRequest,
        setEmailChecked
      );
    }
  };

  const onSendAuthNumberClicked = async () => {
    let title = '인증 메일 전송 실패!';
    let isSuccess = false;
    if(isEmailValid){
      const dataToSubmit = {
        email
      }
      const result = await sendAuthNumberEmail(dataToSubmit);
      if (result?.status === 200) {
        title = '인증 메일 전송 완료!'
        isSuccess = true;
        setIsAuthNumberSended(true);
      }
    }
    countDown(title, isSuccess);
  }
  
  const onVerifyAuthNumberClicked = async (event) => {
    let title = '인증 실패!';
    let isSuccess = false;
    const dataToSubmit = {
      code: authNumber,
      email,
    };
    const result = await verifyAuthNumber(dataToSubmit);
    if(result.data?.message === "success"){
      title = '인증 완료!';
      isSuccess = true;
      setAuthVerify(true);
    }
    countDown(title, isSuccess);
  }

  const onPasswordChanged = (event) => {
    const regPassword = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
    const tmpPassword = event.target.value.trim();
    const isPasswordValid = regPassword.test(tmpPassword);
    setPassword(tmpPassword);
    setIsPasswordValid(isPasswordValid);
    setIsPasswordSame(tmpPassword === passwordCheck);
  }

  const onPasswordCheckChanged = (event) => {
    const tmpPasswordCheck = event.target.value.trim();
    setPasswordCheck(tmpPasswordCheck);
    setIsPasswordSame(password === tmpPasswordCheck);
  }

  const onNicknameChanged = async(event) => {
    const regNickname = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,15}$/;
    const tmpNickname = event.target.value.trim();
    const nicknameValidation = regNickname.test(tmpNickname);
    setNickname(tmpNickname);
    setIsNicknameValid(nicknameValidation);
    if (tmpNickname && nicknameValidation) {
      debounceFunc(
        tmpNickname,
        checkNickNameRequest,
        setNicknameChecked
      );
    }
  }

  const onSubmitClicked = async () => {
    if (!isAuthVerify) {
      countDown("회원 가입 실패!", false);
      return;
    }
    const account = {
      email,
      password,
      nickname,
      birthYear : birthYear,
    }
    const result = await registRequest(account);
    if (result?.data?.message === "success") {
      countDown("회원가입 완료!", true);
      setRegistSucces(true);
    } else {
      countDown("회원 가입 실패!", false);
      return;
    }     
  }

  useEffect(() => {
    if(isLogin){
      navigate("/");
    }
  },[]);

  useEffect(() => {
    if(isRegistSuccess){
      navigate("/account/login");
    }
  },[isRegistSuccess]);

  useEffect(() => {
    if(emailChecked && emailChecked !== "success"){
        form.setFields([{
          name: 'email',
          errors: ['사용 중인 이메일입니다.'],
        }])
    }
  }, [emailChecked]);

  useEffect(() => {
    if (isAuthVerify) {
      authCompleteMessage.current.style.display = "block";
    } else {
      authCompleteMessage.current.style.display = "none";
    }
  },[isAuthVerify])

  useEffect(() => {
    if (passwordCheck.length > 0 && password != passwordCheck) {
      form.setFields([
        {
        name: 'passwordCheck',
        errors: ['비밀번호가 일치하지 않습니다!'],
        }
      ])
    } else {
      form.setFields([
        {
          name: 'passwordCheck',
          errors: [],
        }
      ])
    }
  }, [password, passwordCheck])

  useEffect(() => {
    if(nicknameChecked && nicknameChecked !== "success"){
        form.setFields([{
          name: 'nickname',
          errors: ['사용 중인 닉네임입니다.'],
        }])
    }
  }, [nicknameChecked]);

  return (
    <div className={style['regist-container']}>
      <div className={style.title}>
        <span>Regist</span>
      </div>
      <Form
        form = {form}
        name="regist"
        // onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
        layout="vertical"
        style={{ marginLeft: "auto", marginRight: "auto" }}
        className={style["regist-form"]}
      >
        <Form.Item
          label="이메일"
          name="email"
          value={email}
          hasFeedback
          rules={[
            {
              required: true,
              message: "이메일을 입력해주세요!",
            },
            {
              type: 'email',
              message: "이메일 형식이 아닙니다!"
            },
          ]}
          
        >
          <div className={style["horizon-container"]}>
            <div className={style.left}>
              <Input                
                onChange={onEmailChanged}
                placeholder="이메일"
                className={style["space-input"]}
                disabled={isAuthVerify}
              />
            </div>
            <div className={style.right} ref={sendAuthNumberButton}>
              <Button
                type="primary"
                onClick={onSendAuthNumberClicked}
                disabled={isAuthVerify || emailChecked !== "success"}>
                인증번호 전송
              </Button>
            </div>
          </div>
        </Form.Item>
        
        <Form.Item
          label="이메일 인증번호"
          name="authNumber"
          value={authNumber}
          hasFeedback
          onChange={({ target: { value } }) => setAuthNumber(value)}
          rules={[
            {
              required: true,
              message: "이메일을 인증해주세요!",
            },
            {
              len: authNumberLength,
              message: `${authNumberLength}자리 인증번호를 입력해주세요!`,
            },
          ]}
          >
          <div className={style["horizon-container"]}>
            <div className={style.left}>
              <Input
                placeholder="인증번호"
                className={style["space-input"]}
                disabled={isAuthVerify || !isAuthNumberSended}
                maxLength={authNumberLength}
              />
            </div>
            <div className={style.right}>
              <Button
                type="primary"
                onClick={onVerifyAuthNumberClicked}
                disabled={isAuthVerify || !isAuthNumberSended}
              >
                인증번호 확인
              </Button>
            </div>
          </div>
        </Form.Item>

        <div ref={authCompleteMessage} className={style['auth-complete-box']} >
            <p>인증이 완료되었습니다.</p>
        </div>
        <Form.Item
          label="비밀번호"
          name={"password"}
          hasFeedback
          rules={[
            {
              required: true,
              message: "비밀번호를 입력해주세요!",
            },
            {
              pattern: "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$",
              message: "8 ~ 16자 / 최소 하나의 문자, 숫자, 특수문자 포함!"
            },
          ]}
        >
          <div className={style.left}>
            <Input.Password placeholder="비밀번호" value={password} onChange={onPasswordChanged}/>
          </div>
        </Form.Item>

        <Form.Item
          label="비밀번호 확인"
          name={"passwordCheck"}
          hasFeedback
          rules={[
            {
              required: true,
              message: "비밀번호를 확인해주세요!",
            },
            ({ getFieldValue }) => ({
              validator(_, value) {
                if (!value || getFieldValue("password") === value) {
                  return Promise.resolve();
                }
                return Promise.reject(
                  "비밀번호가 일치하지 않습니다."
                );
              },
            }),
          ]}
        >
          <div className={style.left}>
            <Input.Password placeholder="비밀번호 확인" value={passwordCheck} onChange={onPasswordCheckChanged} />
          </div>
        </Form.Item>

        <Form.Item
          label="닉네임"
          name="nickname"
          hasFeedback
          rules={[
            {
              required: true,
              message: "닉네임을 입력해주세요!",
            },
            {
              pattern: "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,15}$",
              message: "2 ~ 15자 / 영어 또는 숫자 또는 한글 (초성 불가)"
            },
          ]}
        >
          <div className={style.left}>
            <Input placeholder="닉네임" onChange={onNicknameChanged}/>
          </div>
        </Form.Item>

        <Form.Item
          label="출생년도"
          name="birthYear"
          rules={[
            {
              required: true,
              message: "출생년도를 입력해주세요",
            }
          ]}
          >
          <DatePicker placeholder="출생년도" picker="year" value={birthYear} onChange={(date, dateString) => { setBirthYear(dateString); }} />
        </Form.Item>

        <Form.Item
          wrapperCol={{
            offset: 8,
          }}
        >
          <Space>
            <Button
              type="primary"
              htmlType="submit"
              onClick={onSubmitClicked}
              disabled={
                !email || !isEmailValid || emailChecked !== "success" ||
                !password || !isPasswordValid || !isPasswordSame || !isNicknameValid ||
                nicknameChecked !== "success" || !birthYear || !isAuthVerify
              }>
              가입하기
            </Button>
            <Button type="primary" onClick={() => navigate("/")}>
              돌아가기
            </Button>
          </Space>
        </Form.Item>
      </Form>
    </div>
  );
};

export default Regist;
