import React from "react";
import style from "@/styles/SocialLogin.module.scss";
import classNames from "classnames";

const SocialLogin = () => {

  const baseURI = process.env.REACT_APP_SERVER_URL;
  const redirectURI = process.env.REACT_APP_CLIENT_URL;
  return (
    <div className={style["social-button-list"]}>
      <div
        className={classNames(
          `${style["social-button"]}`,
          `${style["google-button"]}`
        )}
      >
        <a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=632011927144-o4dtug6qrhq2ol6dockrfqehvp74s63d.apps.googleusercontent.com&redirect_uri=http://localhost:3000/social/google&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email">
          <img src="/assets/buttons/google_button.png" alt="" />
        </a>
      </div>
      <div className={style["social-button"]}>
        <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=c5ca9b88e40cac66b5cdd051c40731b3&redirect_uri=http://localhost:3000/social/kakao">
          <img src="/assets/buttons/kakao_button.png" alt="" />
        </a>
      </div>
      <div className={style["social-button"]}>
        <a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=nCYDlM4yiReKniB6s0eE&redirect_uri=http://localhost:3000/social/naver&state=asdfasdf">
          <img src="/assets/buttons/naver_button.png" alt="" />
        </a>
      </div>
    </div>
  );
};

export default SocialLogin;
