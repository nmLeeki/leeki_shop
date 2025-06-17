import { axios, setAuthHeader } from '@/services/axiosConfig';
import { AxiosResponse } from 'axios';
/*
 *
 * @author : LeeKiHeang
 * @version : 1.0.0
 * @date : 2025-03-12
 * @description : axios 인스턴스 생성 파일.
 * @modify
 * =======================================================================
 *  DATE                   AUTHOR                NOTE
 * -----------------------------------------------------------------------
 *
 */

export const Get = async <T = any>(url: string, params = {}, signal?: any): Promise<AxiosResponse<T>> => await axios.get(url, { params, signal });
export const Post = async (url: string, params = {}) => await axios.post(url, params);
export const Put = async (url: string, params = {}) => await axios.put(url, params);
export const Delete = async (url: string, params = {}) => await axios.delete(url, { data: params });

export const FormPost = async (url: string, params = {}) =>
  await axios.post(url, params, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
    },
  });
