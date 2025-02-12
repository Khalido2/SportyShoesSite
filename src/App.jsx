import { useEffect, useState } from 'react';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from './pages/Home';
import Navbar from './components/Navbar/Navbar';

import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";

import Users from './pages/Users';
import PaymentHistory from './pages/PaymentHistory';
import usePersistState from './utils/usePersistState';
import { authenticate } from './services/UserService';

function App() {

  const [loggedIn, setLoginStatus] = usePersistState(false, "logInStatus");
  
  const logOut = () => {
    setLoginStatus(false)
  }

  const logIntoSuaveShoes = async(username, password) => {
    const result = await authenticate(username, password)
    setLoginStatus(result)
  }

  return (
    <>
    <Navbar logIn={logIntoSuaveShoes} loggedIn={loggedIn} logOut={logOut}/>

    <Router>
      <Routes>
        <Route exact path="/"
        element={<Home loggedIn={loggedIn}/>}
        />

        <Route
        path="/users"
        element={<Users loggedIn={loggedIn}/>}
        />

      <Route
        path="/payments"
        element={<PaymentHistory loggedIn={loggedIn}/>}
        />
      </Routes>
     
    </Router>

    </>
  )
}

export default App
