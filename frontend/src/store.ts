import { create } from 'zustand';
import { persist } from 'zustand/middleware';
export interface Item {
  id: number;
  title: string;
  price: number;
  filename: string;
} // 혹은 { id: number, name: string } 등 실제 item 타입으로 변경

export interface ItemsStore {
  items: Item[];
  addItem: (item: Item) => void;
  removeItem: (item: Item) => void;
  clearItems: () => void;
  setItems: (items: Item[]) => void; // 추가
}

// 로그인 store
export interface LoginStore {
  isLoggedIn: boolean;
  username: string;
  displayName?: string; // 선택적 속성으로 변경
  setLogin: (username: string, displayName: string) => void;
  setLogout: () => void;
}

export const useLoginStore = create(
  persist<LoginStore>(
    (set) => ({
      isLoggedIn: false,
      username: '',
      displayName: '', // 선택적 속성으로 초기화
      setLogin: (username, displayName) => set({ isLoggedIn: true, username, displayName }),
      setLogout: () => set({ isLoggedIn: false, username: '' }),
    }),
    { name: 'login-storage' },
  ),
);

export const useItemsStore = create(
  persist<ItemsStore>(
    (set) => ({
      items: [],
      addItem: (item) => set((state) => ({ items: [...state.items, item] })),
      removeItem: (item) => set((state) => ({ items: state.items.filter((i) => i !== item) })),
      clearItems: () => set({ items: [] }),
      setItems: (items) => set({ items }),
    }),
    { name: 'items-storage' },
  ),
);
