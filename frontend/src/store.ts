import { create } from 'zustand';
import { persist } from 'zustand/middleware';
export interface Item {
  id: number;
  title: string;
  price: number;
} // 혹은 { id: number, name: string } 등 실제 item 타입으로 변경

export interface ItemsStore {
  items: Item[];
  addItem: (item: Item) => void;
  removeItem: (item: Item) => void;
  clearItems: () => void;
  setItems: (items: Item[]) => void; // 추가
}

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
