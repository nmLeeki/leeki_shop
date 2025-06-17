//detail페이지
//List의 item값을 받아서 detail페이지에 보여주기

import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Item, useItemsStore } from '@/store.ts';
import '@/App.css';
import axios from 'axios';
import { Get } from '@/services';

function Detail() {
  const { id } = useParams();
  const { items } = useItemsStore();
  const [findItem, setFindItem] = useState<any>([]);
  useEffect(() => {
    if (id) {
      Get(`/detail/${id}`)
        .then((response) => {
          setFindItem(response.data);
        })
        .catch((error) => {
          console.error('Error fetching item details:', error);
        });
      // if (foundItem) {
      //   // @ts-ignore
      //   setItems(foundItem.id);
      // } else {
      //   axios
      //     .get(`/api/detail/${id}`)
      //     .then((response) => {
      //       console.log('asd');
      //       setItems(response.data);
      //     })
      //     .catch((error) => {
      //       console.error('Error fetching item details:', error);
      //     });
      // }
    }
  }, [id]);

  return (
    <div className="detail">
      <img src={findItem.filename ? findItem.filename : 'https://placehold.co/300'} alt="Item" />
      <h4>{findItem.title}</h4>
      <p>Price: {findItem.price}</p>
    </div>
  );
}
export default Detail;
