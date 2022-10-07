import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { Button, DatePicker, Form, Input, Space, Modal } from "antd";
import { registRequest, checkNickNameRequest, changeAccountInfoRequest } from "@/services/accountService";
import { debounce } from "lodash";
// import style from "@/styles/Regist.module.scss";
import style from "@/styles/MyProfile.module.scss";


const MyProfile = ({
  email, nickName, birthYear, setNickName, setBirthYear, snsType, password, setPassword, isPasswordValid, setIsPasswordValid
}) => {
  
  const navigate = useNavigate();
  const [form] = Form.useForm();
  const [isNicknameValid, setIsNicknameValid] = useState(true);
  const [nicknameChecked, setNicknameChecked] = useState("success");
  const originalNickName = useSelector((state) => state.account.nickname);
  const movePage = () => {
    navigate("/");
  }
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

  const onFinishFailed = errorInfo => {
    // ("Failed:", errorInfo);
  };

  const onPasswordChanged = (event) => {
    const regPassword = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
    const tmpPassword = event.target.value.trim();
    const isPasswordValid = regPassword.test(tmpPassword);
    setPassword(tmpPassword);
    setIsPasswordValid(isPasswordValid);
  }

  const onNicknameChanged = async(event) => {
    const regNickname = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,15}$/;
    const tmpNickname = event.target.value.trim();
    const nicknameValidation = regNickname.test(tmpNickname);
    setNickName(tmpNickname);
    setIsNicknameValid(nicknameValidation);
    if (nickName && nicknameValidation) {
      if (nickName != originalNickName) {
        debounceFunc(
          nickName,
          checkNickNameRequest,
          setNicknameChecked
        );
      }
    }
  }

  const onSubmitClicked = async () => {
    const account = {
      password: password,
      nickname: nickName,
      birthYear : birthYear,
    }
    const result = await changeAccountInfoRequest(account);
    if (result?.data?.message === "success") {
      countDown("회원정보 수정 완료!", true);
      navigate("/");
    } else {
      countDown("회원정보 수정 실패!", false);
      return;
    }
  }

  useEffect(() => {
    if(nicknameChecked && nicknameChecked !== "success"){
        form.setFields([{
          name: 'nickname',
          errors: ['사용 중인 닉네임입니다.'],
        }])
    }
  }, [nicknameChecked]);

  const formattingEmail = () => {
    if(snsType == 0) {
      return email;
    } else if (snsType == 1) {
      return "네이버 로그인"
    } else if (snsType == 2) {
      return "카카오 로그인"
    } else if (snsType == 3) {
      return "구글 로그인" 
    } else {
      return "ERROR"
    }
  }

  const computeValidate = () => {
    if(snsType == 0) {
      return (!email || !password || !isPasswordValid 
        || !isNicknameValid || nicknameChecked !== "success" || !birthYear)
    } else {
      return (!email || !isNicknameValid || nicknameChecked !== "success" || !birthYear)
    }
  }

  return (
    <div className={style['profile-container']}>
      <div className={style['profile-title']}>
        <span >회원 정보</span>
      </div>
      <Form
        form = {form}
        name="regist"
        onFinishFailed={onFinishFailed}
        autoComplete="off"
        layout="vertical"
        style={{ marginLeft: "auto", marginRight: "auto" }}
        className={style["regist-form"]}
      >
        <Form.Item
          label="이메일"
          name="email"
        >
          <div className={style["horizon-container"]}>
            <div className={style.left}>
              <Input                
                className={style["space-input"]}
                value={formattingEmail()}
                disabled="block"
              />
            </div>
          </div>
        </Form.Item>
        <Form.Item
          label="닉네임"
          name="nickname"
          hasFeedback
          value={nickName}
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
            <Input placeholder="닉네임" value={nickName} onChange={onNicknameChanged}/>
          </div>
        </Form.Item>

        <Form.Item
          label={`출생년도 (${birthYear})`}
          name="birthYear"
          value={birthYear}
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
            <Input.Password placeholder="비밀번호" visibilityToggle={false} disabled={snsType!==0?true:false} value={password} onChange={onPasswordChanged}/>
          </div>
        </Form.Item>

        <Form.Item className={style["mypage-bottom-form"]}>
          <Space>
            <Button
              type="primary"
              htmlType="submit"
              onClick={onSubmitClicked}
              disabled={computeValidate()}>
              수정하기
            </Button>
            <Button type="default" onClick={movePage}>
              돌아가기
            </Button>
          </Space>
        </Form.Item>
      </Form>
    </div>
  );
};

export default MyProfile;
