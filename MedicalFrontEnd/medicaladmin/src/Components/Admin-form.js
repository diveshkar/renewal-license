import React , {useState} from 'react';
import { useEffect } from 'react';
import './Admin-form.css';

function AdminForm() {

  const [licenseData, setLicenseData] = useState({
    //
    LicenceNumber: '',
    NicNumber: '',
    Name: '',
    ExaminationDate:new Date(),
    Address: '',
    DOB: new Date(),
    DOI: new Date(),
    DOE:new Date(),
    BloodGroup:'',

  });

  const handleChange = (e) => {
    const { name, value} = e.target;
      setLicenseData({...licenseData,[name]:value})  
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
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
                <label htmlFor="LicenceNumber">Licence Number</label>
                <input
                  type="text"
                  id="LicenceNumber"
                  name="LicenceNumber"
                  value={licenseData.LicenceNumber}
                  onChange={handleChange}
                  required
                  autoFocus
                />
              </div>
              <div className="wd50">
                <label htmlFor="NicNumber">NIC Number</label>
                <input
                  type="text"
                  id="NicNumber"
                  name="NicNumber"
                  value={licenseData.NicNumber}
                  onChange={handleChange}
                  required
                  autoFocus
                />
              </div>
              <div className="wd50">
              <label htmlFor="FullName">Full Name</label>
                <input
                  type="text"
                  id="FullName"
                  name="FullName"
                  value={licenseData.FullName}
                  onChange={handleChange}
                  required
                  autoFocus
                />
              </div>
              <div className="wd50">
                <label htmlFor="DOB">Date Of Birth</label>
                <input
                  type="date"
                  id="DOB"
                  name="DOB"
                  value={licenseData.DOB}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="wd50">
                <label htmlFor="DOI">Date Of Issue</label>
                <input
                  type="date"
                  id="DOI"
                  name="DOI"
                  value={licenseData.DOI}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="wd50">
                <label htmlFor="DOE">Date Of Expire</label>
                <input
                  type="date"
                  id="DOE"
                  name="DOE"
                  value={licenseData.DOE}
                  onChange={handleChange}
                  required
                />
              </div>
            <div className="col50 colright">
            
            
            </div>
          </div>
  
          </div>
  
          <div className="wd100">
            <hr />
          </div>
          <div className="wd30">
          <label htmlFor="">Blood Group</label>
              <select
                id="blood"
                name="blood"
                value={licenseData.Blood}
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