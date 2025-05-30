import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Form from '@/pages/Form';
import List from '@/pages/List';
import Edit from '@/pages/Edit';
import Join from '@/pages/Join';
import Login from '@/pages/Login';
import RequireAuth from '@/pages/RequireAuth';
import { RecoilRoot } from 'recoil';
import Detail from '@/pages/Detail.tsx';
import { useEffect } from 'react';
import axios from 'axios';

function App() {
  // /getCurrentMember api post 요청 보내기
  useEffect(() => {
    axios
      .post('/api/getCurrentMember', { withCredentials: true })
      .then((response) => {
        console.log('Current member:', response.data);
      })
      .catch((error) => {
        console.error('Error fetching current member:', error);
      });
  }, []);
  return (
    <RecoilRoot>
      <BrowserRouter>
        <div className="nav">
          <a className="logo">SpringMall</a>
          <Link to="/">List</Link>
          <Link to="/form">Form</Link>
        </div>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route
            path="/"
            element={
              <RequireAuth>
                <List />
              </RequireAuth>
            }
          />
          <Route path="/form" element={<Form />} />
          <Route path="/edit/:id" element={<Edit />} />
          {/*//detail뒤에 id값 넣기*/}
          <Route path="/detail/:id" element={<Detail />} />
          <Route path="/join" element={<Join />} />
        </Routes>
      </BrowserRouter>
    </RecoilRoot>
  );
}

export default App;
