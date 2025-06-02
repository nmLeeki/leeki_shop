import { useLoginStore } from '@/store.ts';
import { Navigate, Outlet } from 'react-router-dom';

function RequireAuth() {
  const { isLoggedIn } = useLoginStore();
  if (!isLoggedIn) {
    return <Navigate to="/login" replace />;
  }
  return <Outlet />;
}

export default RequireAuth;
