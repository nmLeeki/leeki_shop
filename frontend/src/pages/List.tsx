import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import '@/App.css';
import { useRecoilState } from 'recoil';
import { Item, useItemsStore } from '@/store.ts';

function List() {
  const { items, setItems } = useItemsStore();
  useEffect(() => {
    fetch('/api/list')
      .then((res) => res.json())
      .then((data) => setItems(data));
  }, []);

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
          <div className={'card'}>
            <Link to={`/detail/${item.id}`} key={index}>
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
    </>
  );
}

export default List;
