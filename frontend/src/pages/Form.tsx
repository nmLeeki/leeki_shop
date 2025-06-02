import axios from 'axios';
import { useState } from 'react';

function Form() {
  const [title, setTitle] = useState('');
  const [price, setPrice] = useState('');

  const handleSubmit = () => {
    console.log({ title, price });
    // axios로 post요청
    axios
      .post('/api/add', { title, price }, { withCredentials: true })
      .then((response: any) => {
        console.log('성공:', response.data);
        window.location.href = '/';
        window.scrollTo(0, document.body.scrollHeight);
      })
      .catch((error: any) => {
        console.error('에러:', error);
      });
  };

  return (
    <form>
      <input name="title" value={title} onChange={(e) => setTitle(e.target.value)} placeholder="제목" />
      <input name="price" value={price} onChange={(e) => setPrice(e.target.value)} placeholder="가격" type="number" />
      <button type="button" onClick={handleSubmit}>
        버튼
      </button>
    </form>
  );
}

export default Form;
