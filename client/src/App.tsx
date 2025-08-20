import './App.css';
import { Route, Routes } from 'react-router-dom';
// import MenuBar from './components/Header';
import { Container } from '@mui/material';
import IndexPage from './pages/IndexPage';
import Footer from './components/Footer';
import PlayerInfoPage from './pages/PlayerInfoPage';
import Sidebar from './components/SideBar';

function App() {
  return (
   <div className="App" style={{ display: "flex", flexDirection: "column", minHeight: "100vh" }}>
    <Sidebar />
    <div style={{ flex: 1 }}>
        <Routes>
            <Route path="/" element={
                <>
                    <IndexPage />
                </>
            } />
        </Routes>
        <Routes>
            <Route path="/info/:id" element={
                <Container>
                    <PlayerInfoPage />
                </Container>
            } />
        </Routes>
    </div>

    <Footer />
    </div>
  );
}

export default App;
