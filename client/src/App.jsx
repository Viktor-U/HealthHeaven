import { Route, Routes } from 'react-router-dom';
import { AuthContextProvider } from './contexts/AuthContext';

import Header from './components/header/Header';
import Home from './components/home/Home';
import Login from './components/login/Login';
import Logout from './components/logout/Logout';
import Register from './components/register/Register';
import DoctorList from './components/doctor-list/DoctorList';
import DoctorCreate from './components/doctor-create/DoctorCreate';
import DoctorDetails from './components/doctor-details/DoctorDetails';


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
                  <Route path='/doctors' element={<DoctorList/>}/>
                  <Route path='/doctors/:doctorId/details' element={<DoctorDetails/>}/>
                  <Route path='/doctors/create' element={<DoctorCreate/>}/>
                  <Route path='/logout' element={<Logout/>}/>
              </Routes>
          </main>
      </div>
    </AuthContextProvider>
  )
}

export default App

