import Login from "./Components/Login";
import {BrowserRouter,Routes, Route} from 'react-router-dom';
import MedicalForm from "./Components/Medical-Form";
import TableComponent from "./Components/License-table"
import './App.css';
import CameraAccess from "./Components/CameraAccess";


function App() {
  return (

      <BrowserRouter>
      <Routes>
         <Route exact path ='/' element={<Login/>}/>
         <Route exact path ='/medical-admin' element={<MedicalForm/>}/>
         <Route exact path ='/renewal-admin' element={<TableComponent/>}/>
         <Route exact path ='/cam' element={<CameraAccess/>}/>
         

      </Routes>
      </BrowserRouter>
    
  );
}

export default App;
