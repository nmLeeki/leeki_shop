//로그인 페이지 구현

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import '@/App.css';
import { useLoginStore } from '@/store.ts';
import { FormPost } from '@/services';

function Login() {
  const { setLogin, setLogout, isLoggedIn } = useLoginStore();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const navigate = useNavigate();

  const loginSubmit = () => {
    const params = new URLSearchParams();
    params.append('username', username);
    params.append('password', password);

    FormPost('/login', params)
      .then((response) => {
        setLogin(response.data.name, response.data.displayName); // 로그인 상태 업데이트
        navigate('/'); // 로그인 성공 후 홈으로 이동
      })
      .catch((error) => {
        console.error('에러:', error);
      });
  };

  useEffect(() => {
    if (isLoggedIn) {
      navigate(-1); // 이미 로그인된 경우 이전 페이지로 이동
    } else {
      setLogout(); // 비로그인 상태로 초기화
    }
  }, []);

  return (
    <form className="login-form">
      <input name="username" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="아이디" />
      <input name="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="비밀번호" type="password" />
      <button type="button" onClick={loginSubmit}>
        로그인
      </button>
      <Link to="/join">회원가입</Link>
    </form>
  );
}
export default Login;
