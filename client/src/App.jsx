import { Route, Routes } from 'react-router-dom';
import { AuthContextProvider, useAuthContext } from './contexts/AuthContext';

import Header from './components/header/Header';
import Home from './components/home/Home';
import Login from './components/login/Login';
import Logout from './components/logout/Logout';
import Register from './components/register/Register';
import DoctorList from './components/doctor-list/DoctorList';
import DoctorCreate from './components/doctor-create/DoctorCreate';
import DoctorDetails from './components/doctor-details/DoctorDetails';
import DoctorEdit from './components/doctor-edit/DoctorEdit';
import DeleteDoctor from './components/doctor-delete/DeleteDoctor';
import Shop from './components/shop/Shop';
import ItemDetails from './components/item-details/ItemDetails';
import Cart from './components/cart/Cart';
import Article from './components/articles/Articles';


function App() {
  const {isAuthenticated} = useAuthContext();
  //todo error page;


  return (
    <AuthContextProvider >
      <div id="box">
          <Header/>

          <main id="main-content">
              <Routes>
                  <Route path='/' element={<Home/>}/>
                  <Route path='/login' element={<Login/>}/>
                  <Route path='/register' element={<Register/>}/>
                  <Route path='/logout' element={<Logout/>}/>
                  <Route path='/doctors' element={<DoctorList/>}/>  
                  <Route path='/doctors/:doctorId/details' element={<DoctorDetails/>}/>
                  <Route path='/doctors/create' element={<DoctorCreate/>}/>
                  <Route path='/doctors/:doctorId/edit' element={<DoctorEdit/>}/>
                  <Route path='/doctors/:doctorId/delete' element={<DeleteDoctor/>}/>
                  <Route path='/shop' element={<Shop/>}/>
                  <Route path='/items/:itemId/details' element={<ItemDetails/>}/>
                  <Route path='/cart' element={<Cart/>}/>
                  <Route path='/articles' element={<Article/>}/>
              </Routes>
          </main>
      </div>
    </AuthContextProvider>
  )
}

export default App

