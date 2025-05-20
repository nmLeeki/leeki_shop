import { useEffect, useState } from 'react';

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

  return (
    <>
      {items.map((item, index) => (
        <div className={'card'} key={index}>
          <img src="https://placehold.co/300" />
          <div>
            <h4>{item.title}</h4>
            <p>{item.price}</p>
          </div>
        </div>
      ))}
    </>
  );
}

export default List;
