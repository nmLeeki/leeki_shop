import { BrowserRouter, Routes, Route, Link, Navigate } from 'react-router-dom';
import Form from '@/pages/Form';
import List from '@/pages/List';
import Edit from '@/pages/Edit';
import Join from '@/pages/Join';
import Login from '@/pages/Login';
import RequireAuth from '@/pages/RequireAuth';
import { RecoilRoot } from 'recoil';
import Detail from '@/pages/Detail.tsx';
import axios from 'axios';
import { useLoginStore } from '@/store.ts';

function App() {
  const { displayName, isLoggedIn, setLogout } = useLoginStore();

  const handleLogout = () => {
    axios
      .post('/api/logout', {}, { withCredentials: true })
      .then((response) => {
        setLogout();
        window.location.href = '/login';
      })
      .catch((error) => {
        console.error('로그아웃 실패:', error);
      });
  };

  return (
    <RecoilRoot>
      <BrowserRouter>
        <div className="nav">
          <a className="logo">SpringMall</a>
          <Link to="/">List</Link>
          <Link to="/form">Form</Link>
          {isLoggedIn && <span style={{ marginRight: '10px' }}>{displayName}</span>}
          {isLoggedIn && (
            <button type={'button'} onClick={handleLogout}>
              로그아웃
            </button>
          )}
        </div>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/join" element={<Join />} />
          <Route element={<RequireAuth />}>
            <Route path="/" element={<List />} />
            <Route path="/form" element={<Form />} />
            <Route path="/edit/:id" element={<Edit />} />
            <Route path="/detail/:id" element={<Detail />} />
            <Route path="*" element={<Navigate to="/" replace />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </RecoilRoot>
  );
}

export default App;
