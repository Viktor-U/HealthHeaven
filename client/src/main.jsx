import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import './utils/i18n'

import App from './App';
import { AuthContextProvider } from './contexts/AuthContext';

ReactDOM.createRoot(document.getElementById('root')).render(
  // <React.StrictMode>
  <AuthContextProvider >

    <BrowserRouter>
      <App />
    </BrowserRouter>
  </AuthContextProvider>
  // </React.StrictMode>
)

