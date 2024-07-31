import { Route, Routes } from 'react-router-dom';
import { AuthContextProvider } from './contexts/AuthContext';

import Header from './components/header/Header';
import Home from './components/home/Home';
import Login from './components/login/Login';
import Logout from './components/logout/Logout';
import Register from './components/register/Register';
import GameList from './components/game-list/GameList';
import GameCreate from './components/game-create/GameCreate';
import GameDetails from './components/game-details/GameDetails';


function App() {

  return (
    <AuthContextProvider >
      <div id="box">
          <Header/>

          <main id="main-content">
              <Routes>
                  <Route path='/' element={<Home/>}/>
                  <Route path='/login' element={<Login/>}/>
                  <Route path='/register' element={<Register/>}/>
                  <Route path='/doctors' element={<GameList/>}/>
                  <Route path='/doctors/:doctorId/details' element={<GameDetails/>}/>
                  <Route path='/doctors/create' element={<GameCreate/>}/>
                  <Route path='/logout' element={<Logout/>}/>
              </Routes>
          </main>
      </div>
    </AuthContextProvider>
  )
}

export default App

