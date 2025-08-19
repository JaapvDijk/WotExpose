import './App.css';
import { Route, Routes } from 'react-router-dom';
// import MenuBar from './components/Header';
import { Container } from '@mui/material';
import Banner from './components/Banner';
import Footer from './components/Footer';
import PlayerInfoPage from './pages/PlayerInfoPage';

function App() {
  return (
   <div className="App" style={{ display: "flex", flexDirection: "column", minHeight: "100vh" }}>
    {/* <MenuBar /> */}
    <div style={{ flex: 1 }}>
        <Routes>
            <Route path="/" element={
                <>
                    <Banner />
                </>
            } />
        </Routes>
        <Routes>
            <Route path="/info/:id" element={
                <Container maxWidth="lg" sx={{ padding: "0", textAlign: "center", minHeight: "100px" }}>
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
