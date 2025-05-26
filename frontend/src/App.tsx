import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Form from '@/pages/Form';
import List from '@/pages/List';
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
          <Route path="/" element={<List />} />
          <Route path="/form" element={<Form />} />
          {/*//detail뒤에 id값 넣기*/}
          <Route path="/detail/:id" element={<Detail />} />
        </Routes>
      </BrowserRouter>
    </RecoilRoot>
  );
}

export default App;
