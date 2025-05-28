//form을 작성해야하는데 아이디/비번/유저이름 컬럼은 각각 username/password/displayName 으로 만들어주세요

import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '@/App.css';
function Join() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [displayName, setDisplayName] = useState('');
  const navigate = useNavigate();

  const joinSubmit = () => {
    console.log({ username, password, displayName });
    // axios로 post요청
    axios
      .post('/api/join', { username, password, displayName })
      .then((response) => {
        console.log('성공:', response.data);
        navigate('/'); // 회원가입 성공 후 홈으로 이동
      })
      .catch((error) => {
        console.error('에러:', error);
      });
  };

  return (
    <form className="join-form">
      <input name="username" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="아이디" />
      <input name="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="비밀번호" type="password" />
      <input name="displayName" value={displayName} onChange={(e) => setDisplayName(e.target.value)} placeholder="이름" />
      <button type="button" onClick={joinSubmit}>
        가입하기
      </button>
    </form>
  );
}
export default Join;
