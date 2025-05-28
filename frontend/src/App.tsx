import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Form from '@/pages/Form';
import List from '@/pages/List';
import Edit from '@/pages/Edit';
import Join from '@/pages/Join';
import Login from '@/pages/Login';
import RequireAuth from '@/pages/RequireAuth';
import { RecoilRoot } from 'recoil';
import Detail from '@/pages/Detail.tsx';

function App() {
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
