import Login from "./Components/Login";
import {BrowserRouter,Routes, Route} from 'react-router-dom';
import MedicalForm from "./Components/Medical-Form";
import TableComponent from "./Components/License-table"
import './App.css';


function App() {
  return (

      <BrowserRouter>
      <Routes>
         <Route exact path ='/' element={<Login/>}/>
         <Route exact path ='/medical-form' element={<MedicalForm/>}/>
         <Route exact path ='/license-table' element={<TableComponent/>}/>

      </Routes>
      </BrowserRouter>
    
  );
}

export default App;
