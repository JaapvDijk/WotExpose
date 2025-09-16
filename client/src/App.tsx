import './App.css';
import { Route, Routes } from 'react-router-dom';
import IndexPage from './pages/IndexPage';
import Footer from './components/Footer';
import PlayerInfoPage from './pages/PlayerInfoPage';
import Sidebar from './components/SideBar';
import LoginPage from './pages/LoginPage';
import AdminDashboardPage from './pages/admin/AdminDashboardPage';
import UserManagementPage from './pages/admin/UserManagementPage';

function App() {
  return (
   <div className="App">
    <Sidebar />
    <div style={{ flex: 1 }}>
        <Routes>
            <Route path="/" element={
                <IndexPage />
            } />

            <Route path="/info/:id" element={
                <>
                    <PlayerInfoPage />
                    <Footer />
                </>
            } />

            <Route path="/login" element={
                <LoginPage />
            } />

            <Route path="/admin" element={
                <AdminDashboardPage />
            } />

            <Route path="admin/userManagement" element={
                <UserManagementPage />
            } />

        </Routes>
    </div>
    </div>
  );
}

export default App;
