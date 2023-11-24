import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from "./Components/Login";
import MedicalForm from "./Components/Medical-Form";
import TableComponent from "./Components/License-table";
import AdminForm from './Components/Admin-form';
import './App.css';
// import PrivateRoutes from './Components/PrivateRoute';  
import { jwtDecode } from 'jwt-decode';

function App() {
    return (
        <Router>
              <Routes>
              <Route path='/medical-admin' element={<MedicalAdmin> <MedicalForm /></MedicalAdmin>} />
              <Route path='/renewal-admin' element={<RenewalAdmin> <TableComponent /> </RenewalAdmin>} />
              <Route path='/LicenseData_form' element={<RenewalAdmin> <AdminForm /> </RenewalAdmin>} />
              <Route exact path='/' element={<Login />} />
              <Route exact path='*' element = {<h1>404</h1>}/>
              </Routes>                
        </Router>
    );
}

function RenewalAdmin({ children }) {
  if(jwtDecode(localStorage.getItem("token")).Role ==="RenewalAdmin"){
    return <> {children} </>
  }
  else{
    return (
      <div>
      <h1>You do not have access to this page</h1>
      <Navigate to="/"/>
      </div>
    )
      
  }
}

function MedicalAdmin ({children}){
  if(jwtDecode(localStorage.getItem("token")).Role === "MedicalAdmin"){
    return <>{children}</>
  }else{
    return (
      <div>
      <h1>You do not have access to this page</h1>
      <Navigate to="/"/>
      </div>
    )
  }
}

export default App;