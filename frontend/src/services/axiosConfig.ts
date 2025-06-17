import Axios, { AxiosError, AxiosRequestConfig } from 'axios';
import { useNavigate } from 'react-router-dom';

/*
 *
 * @version : 1.0.0
 * @date : 2024-09-20
 * @description : axios 공통 설정 작성 파일.
 * @modify
 * =======================================================================
 *  DATE                   AUTHOR                NOTE
 * -----------------------------------------------------------------------
 *
 */
const conf: AxiosRequestConfig = {
  baseURL: import.meta.env.VITE_APP_ENV === 'dev' ? '/' : '/api/',
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json;charset=UTF-8',
    // 'Content-Type': 'multipart/form-data',
    'Access-Control-Allow-Origin': '*',
    withCredentials: true,
  },
  timeout: 30000,
  responseType: 'json',
  // maxContentLength: Infinity,
  // maxBodyLength: Infinity,
};

export const axios = Axios.create(conf);

// 인터셉터 설정 (필요에 따라)
axios.interceptors.request.use(
  (config: any) => {
    console.log(':::', import.meta.env.VITE_APP_ENV);
    // if (import.meta.env.VITE_APP_ENV === 'dev') {
    //   config.url = config.url?.replace('/api/', '${import.meta.env.VITE_APP_PROXY}/');
    // }
    //startLoading();
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);

//
// axios.interceptors.request.use(
//   (config) => {
//     // config.headers.Authorization = ''
//
//     return config;
//   },
//   (error) => {
//     return Promise.reject(error);
//   },
// );

axios.interceptors.response.use(
  (response) => {
    return response;
  },

  (error) => {
    const errorRes: AxiosError = error;
    return Promise.reject(errorRes);
  },
);

export const setAuthHeader = (id: string | null) => {
  if (id) {
    axios.defaults.headers.common['Authorization'] = 'Bearer ${id}';
  } else {
    delete axios.defaults.headers.common['Authorization'];
  }
};
