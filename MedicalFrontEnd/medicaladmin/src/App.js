import Login from "./Components/Login";
import {BrowserRouter,Routes, Route} from 'react-router-dom';
import MedicalForm from "./Components/Medical-Form";

function App() {
  return (

      <BrowserRouter>
      <Routes>
         <Route exact path ='/' element={<Login/>}/>
         <Route exact path ='/medical-form' element={<MedicalForm/>}/>
      </Routes>
      </BrowserRouter>
    
  );
}

export default App;
