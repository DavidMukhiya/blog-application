import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home'
import Login from './pages/Login'
import Signup from './pages/Signup'
import About from './pages/About';
import Header from './components/Header';
import Services from './pages/Services';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer, toast } from 'react-toastify';
import Userdashboard from './pages/user-routes/Userdashboard';
import Privateroute from './components/Privateroute';
import Profileinfo from './pages/user-routes/Profileinfo';


function App() {
  return (
   <BrowserRouter>
   <ToastContainer position='top-center' />
   <Header/>
    <Routes>
      <Route path='/' element={<Home/>}/>
      <Route path='login' element={<Login/>}/>
      <Route path='signup' element={<Signup/>}/>
      <Route path='about' element={<About/>}/>
      <Route path='services' element={<Services/>}/>
      <Route path='user' element={<Privateroute/>}>
        <Route path='dashboard' element={<Userdashboard/>}/>
        <Route path='profile-info' element={<Profileinfo/>} />
      </Route>
      
    </Routes>
   </BrowserRouter>
  );
}

export default App;
