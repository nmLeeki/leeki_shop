// src/pages/RequireAuth.tsx
import { useLoginStore } from '@/store.ts';
import { Navigate } from 'react-router-dom';
import { JSX } from 'react';

function RequireAuth({ children }: { children: JSX.Element }) {
  const { isLoggedIn } = useLoginStore();
  if (!isLoggedIn) {
    return <Navigate to="/login" replace />;
  }
  return children;
}

export default RequireAuth;
