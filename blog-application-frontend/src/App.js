import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home'
import Login from './pages/Login'
import Signup from './pages/Signup'
import About from './pages/About';
import Header from './components/Header';
import Services from './pages/Services';


function App() {
  return (
   <BrowserRouter>
   <Header/>
    <Routes>
      <Route path='/' element={<Home/>}/>
      <Route path='login' element={<Login/>}/>
      <Route path='signup' element={<Signup/>}/>
      <Route path='about' element={<About/>}/>
      <Route path='services' element={<Services/>}/>

    </Routes>
   </BrowserRouter>
  );
}

export default App;
