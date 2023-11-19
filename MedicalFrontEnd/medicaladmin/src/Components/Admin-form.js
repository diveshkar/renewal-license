import React , {useState} from 'react';
import { useEffect } from 'react';
import './Admin-form.css';
import { LicenseNewUserAdd } from './adminServices';
import Navbar from './Navbar';

function AdminForm() {

  const [licenseData, setLicenseData] = useState({
    //
    licenseNo: '',
    licenseType:'',
    nic: '',
    name: '',
    photo:"",
    address: '',
    dob: new Date(),
    bloodGroup:'',
    dateOfIssue: new Date(),
    dateOfExpiry:new Date(),
  });

  const[imageUrl,setImageURL]= useState(null)

  const handleChange = (e) => {
    const { name, value , type, files } = e.target;
   if (type === "file") {
      setLicenseData({ ...licenseData, [name]: files[0] });
    
      if (files[0]) {
        const reader = new FileReader();
    
        reader.onload = () => {
          // Do something with the reader result, e.g., update state or display the image
          const imageUrl = reader.result;
          setImageURL(imageUrl);
        };
    
        reader.readAsDataURL(files[0]);
      }
    } 
    else{
      setLicenseData({...licenseData,[name]:value}) 
      }
      
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    LicenseNewUserAdd(licenseData)
    .then((response) => {
      window.alert(response.data);
  })
  .catch((error) => {
      window.alert("An error occurred:", error);
      
  });
    
    console.log(licenseData)
  };
  const sessionTimeout = 60 * 60 * 1000;
  const token = localStorage.getItem('token')
  const role = localStorage.getItem("role");
  const logout = () => {
        localStorage.clear();
        window.location.href = '/'

    }
  useEffect(() => {
        if (token) {
            const sessionExpireTimeout = setTimeout(() => {
                localStorage.clear();
                window.location.href = '/';     
            }, sessionTimeout);
            return () => clearTimeout(sessionExpireTimeout);
        }
    }, [token, sessionTimeout])
  
 if(role === "RenewalAdmin"){
    return (
        // <div>
        //     <button onClick={logout}>logout</button>
            
        // </div>
        <div className="container">
        <form className="contact" onSubmit={handleSubmit}>
          <h3>User Details</h3>
          <div className="col50 colleft">
            <div className="col50 colleft">
            <div className="wd50">
                <label htmlFor="licenseNo">Licence Number</label>
                <input
                  type="text"
                  id="licenseNo"
                  name="licenseNo"
                  value={licenseData.licenseNo}
                  onChange={handleChange}
                  required
                  autoFocus
                />
              </div>
              
              <div className="wd100">
                <hr />
              </div>
            
              <div className="wd30">
              <label htmlFor="">LicenseType</label>
              <select
                id="licenseType"
                name="licenseType"
                value={licenseData.licenseType}
                onChange={handleChange}
              >
                <option value=""></option>
                <option value="Mc">Motor cycles <b>A</b></option>
                <option value="Lmc">Light motor cycles <b>A1</b></option>
                <option value="Car">All cars/dual purpose <b>B</b></option>
                <option value="Car">Tricycles/tricycles van <b>B1</b></option>
                <option value="Car">Land vehicles <b>G</b></option>
                <option value="Car">Hand tractors <b>G1</b></option>
              </select>
              </div>

              <div className="wd100">
                <hr />
              </div>

              <div className="wd50">
                <label htmlFor="nic">NIC Number</label>
                <input
                  type="text"
                  id="nic"
                  name="nic"
                  value={licenseData.nic}
                  onChange={handleChange}
                  required
                  autoFocus
                />
              </div>

              <div className="wd100">
                <hr />
              </div>


              <div className="wd50">
              <label htmlFor="FullName">Full Name</label>
                <input
                  type="text"
                  id="FullName"
                  name="name"
                  value={licenseData.name}
                  onChange={handleChange}
                  required
                  autoFocus
                />
              </div>

              <div className="wd100">
                <hr />
              </div>


              <div className="wd50">
              <label htmlFor="Photo">Photo</label>
              <br />
              <img className = "photo" src={imageUrl} />
                <input
                  type="file"
                  id="photo"
                  name="photo"
                  onChange={handleChange}
                  
                  autoFocus
                />
              
              </div>

              <div className="wd100">
                <hr />
              </div>


              <div className="wd50">
                <label htmlFor="address">Address</label>
                <input
                  type="text"
                  id="address"
                  name="address"
                  value={licenseData.address}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="wd100">
                <hr />
              </div>


              <div className="wd50">
                <label htmlFor="dob">Date Of Birth</label>
                <input
                  type="date"
                  id="dob"
                  name="dob"
                  value={licenseData.dob}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="wd100">
                <hr />
              </div>


              <div className="wd30">
              <label htmlFor="">BloodGroup</label>
              <select
                id="bloodGroup"
                name="bloodGroup"
                value={licenseData.bloodGroup}
                onChange={handleChange}
              >
                <option value=""></option>
                <option value="blood">O+ </option>
                <option value="blood">O- </option>
                <option value="blood">A+ </option>
                <option value="blood">A- </option>
                <option value="blood">B+ </option>
                <option value="blood">B- </option>
                <option value="blood">AB+ </option>
                <option value="blood">AB- </option>
              </select>
              </div>

              <div className="wd100">
                <hr />
              </div>


              <div className="wd50">
                <label htmlFor="dateOfIssue">Date Of Issue</label>
                <input
                  type="date"
                  id="dateOfIssue"
                  name="dateOfIssue"
                  value={licenseData.dateOfIssue}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="wd100">
                <hr />
              </div>


              <div className="wd50">
                <label htmlFor="dateOfExpiry">Date Of Expire</label>
                <input
                  type="date"
                  id="dateOfExpiry"
                  name="dateOfExpiry"
                  value={licenseData.dateOfExpiry}
                  onChange={handleChange}
                  required
                />
              </div>
          </div>
          </div>


          <div className="wd100">
                <hr />
              </div>


          <div className="wd100">
            <button name="submit" type="submit" id="" data-submit="...Sending">
              Submit
            </button>
            </div>



        </form>

        <div>
          <button onClick={logout}>logout</button>
        </div>
        <div>
          <Navbar />
        </div>
      </div>

      
  
      )
  }
  else {
    // login page
   window.location.href = '/';
  }
}

export default AdminForm  