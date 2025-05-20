import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Form from '@/pages/Form';
import List from '@/pages/List';
import { RecoilRoot } from 'recoil';

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
          <Route path="/form" element={<Form />} />
          <Route path="/" element={<List />} />
        </Routes>
      </BrowserRouter>
    </RecoilRoot>
  );
}

export default App;
