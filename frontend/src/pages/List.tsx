import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import '@/App.css';
import axios from 'axios';
import { useRecoilState } from 'recoil';
import { Item, useItemsStore, useLoginStore } from '@/store.ts';

function List() {
  const { items, setItems } = useItemsStore();
  const [page, setPage] = useState(1);
  const [size] = useState(5);
  const [totalPages, setTotalPages] = useState(1);
  useEffect(() => {
    // POST 방식, 파라미터 객체로 전달
    axios.post('/api/list', { page, size }).then((res) => {
      setItems(res.data.content);
      setTotalPages(res.data.totalPages);
    });
  }, [page, size, setItems]);

  const deleteClick = (e: Item) => {
    const id = e.id;
    if (id) {
      fetch(`/api/delete/${id}`, {
        method: 'DELETE',
      }).then((response) => {
        if (response.ok) {
          // 함수형 업데이트 대신 직접 새 배열 전달
          setItems(items.filter((item) => item.id !== id));
        } else {
          console.error('삭제 실패');
        }
      });
    }
  };
  console.log(items);
  return (
    <>
      {items &&
        items.map((item, index) => (
          <div className={'card'} key={index}>
            <Link to={`/detail/${item.id}`}>
              <img src="https://placehold.co/300" />
              <div>
                <h4>{item.title}</h4>
                <p>{item.price}</p>
              </div>
            </Link>
            <div className={'common_btn'}>
              <Link to={`/edit/${item.id}`} className={'btn'}>
                수정
              </Link>
              <button type={'button'} onClick={() => deleteClick(item)}>
                삭제
              </button>
            </div>
          </div>
        ))}
      <div className="pagination">
        {[...Array(totalPages)].map((_, idx) => (
          <button key={idx} onClick={() => setPage(idx)} disabled={page === idx}>
            {idx + 1}
          </button>
        ))}
      </div>
    </>
  );
}

export default List;
