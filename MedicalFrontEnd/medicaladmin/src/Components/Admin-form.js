import React , {useState} from 'react';
import { useEffect } from 'react';
import './Admin-form.css';
import { LicenseNewUserAdd } from './adminServices';

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

  const handleChange = (e) => {
    const { name, value} = e.target;
      setLicenseData({...licenseData,[name]:value})  
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    LicenseNewUserAdd(licenseData)
    .then((response) => {
      window.alert(response.data);
  })
  .catch((error) => {
      alert.error("An error occurred:", error);
      
  });
    
    console.log(licenseData)
  };
  const sessionTimeout = 60 * 60 * 1000;
  const RenewalAdminToken = localStorage.getItem('RenewalAdminToken')
  const logout = () => {
        localStorage.clear();
        window.location.href = '/'

    }
  useEffect(() => {
        if (RenewalAdminToken) {
            const sessionExpireTimeout = setTimeout(() => {
                localStorage.clear();
                window.location.href = '/';     
            }, sessionTimeout);
            return () => clearTimeout(sessionExpireTimeout);
        }
    }, [RenewalAdminToken, sessionTimeout])
  
//  if(RenewalAdminToken !== null && RenewalAdminToken !== ""){
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
              <label htmlFor="Phoyo">Photo</label>
                <input
                  type="file"
                  id="photo"
                  name="photo"
                  value={licenseData.photo}
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
      </div>

      
  
      )
  // }
  // else {
  //   // login page
  //  window.location.href = '/';
  // }
}

export default AdminForm  