import React from 'react';
import ReactDOM from 'react-dom/client';
import { Provider } from 'react-redux';
import { persistor, store } from './redux/store';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { BrowserRouter } from 'react-router-dom';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import { PersistGate } from 'redux-persist/integration/react';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

//React devtools
const script = document.createElement("script");
script.src = "http://localhost:8097";
script.async = true;
document.head.appendChild(script);


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
      <Provider store={store}>
        <PersistGate loading={null} persistor={persistor}>
            <QueryClientProvider client={queryClient}>
              <BrowserRouter>
                <App />
              </BrowserRouter>
          </QueryClientProvider>
        </PersistGate>
      </Provider>
    </ThemeProvider>

  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
