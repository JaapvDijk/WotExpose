import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { BrowserRouter } from 'react-router-dom';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            staleTime: Infinity,
            structuralSharing: false
        }
    }
});

const theme = createTheme({
  palette: {
    mode: 'dark',
    primary: { main: '#1476A3' },
    secondary: { main: '#292977' },
  },
  typography: {
    fontFamily: 'Montserrat, Arial, sans-serif',
    h1: { fontFamily: 'Orbitron, sans-serif', fontWeight: 700 },
    h2: { fontFamily: 'Orbitron, sans-serif', fontWeight: 500 },
    subtitle1: { fontFamily: 'Montserrat, sans-serif', fontWeight: 600 },
    body1: { fontFamily: 'Montserrat, sans-serif', fontWeight: 400 },
  },
});


root.render(
  <React.StrictMode>
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@500;700&family=Montserrat:wght@400;600&display=swap" rel="stylesheet"></link>
    <ThemeProvider theme={theme}>
      <CssBaseline />
        <QueryClientProvider client={queryClient}>
            <BrowserRouter>
              <App />
            </BrowserRouter>
        </QueryClientProvider>
      </ThemeProvider>

  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
