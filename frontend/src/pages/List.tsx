import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import '@/App.css';
import { useRecoilState } from 'recoil';
import { useItemsStore } from '@/store.ts';

function List() {
  const { items, setItems } = useItemsStore();
  useEffect(() => {
    fetch('/api/list')
      .then((res) => res.json())
      .then((data) => setItems(data));
  }, []);
  console.log(items);
  return (
    <>
      {items &&
        items.map((item, index) => (
          <Link to={`/detail/${item.id}`} className={'card'} key={index}>
            <img src="https://placehold.co/300" />
            <div>
              <h4>{item.title}</h4>
              <p>{item.price}</p>
            </div>
          </Link>
        ))}
    </>
  );
}

export default List;
