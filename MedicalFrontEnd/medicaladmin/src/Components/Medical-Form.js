import React , {useState} from 'react';
import { useEffect } from 'react';
import './medical-form.css';

function MedicalForm() {

  const [formData, setFormData] = useState({
    FullName: '',
    BookingId: '',
    phoneNumber: '',
    Address: '',
    NicNumber: '',
    DateOfExamination: '',
    DOB:'',
    Age:'',
    sex:'',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // const handleCheckboxChange = (e) => {
  //   const { name, checked } = e.target;
  //   if (checked) {
  //     setFormData({ ...formData, reasonForNotTakingTreatment: [...formData.reasonForNotTakingTreatment, name] });
  //   } else {
  //     setFormData({
  //       ...formData,
  //       reasonForNotTakingTreatment: formData.reasonForNotTakingTreatment.filter((item) => item !== name),
  //     });
  //   }
  // };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Handle form submission here
  };
  const sessionTimeout = 60 * 60 * 1000;
  const userToken = localStorage.getItem('token')
  const logout = () => {
        localStorage.removeItem("token")
        window.location.href = '/'

    }
  useEffect(() => {
        if (userToken) {
            const sessionExpireTimeout = setTimeout(() => {
                localStorage.removeItem("token");
                window.location.href = '/';     
            }, sessionTimeout);
            return () => clearTimeout(sessionExpireTimeout);
        }
    }, [userToken, sessionTimeout])
  
  if(userToken !== null && userToken !== ""){
    return (
        // <div>
        //     <button onClick={logout}>logout</button>
            
        // </div>
        <div className="container">
        <form className="contact" onSubmit={handleSubmit}>
          <h3>Medical Form</h3>
          <div className="col50 colleft">
            <div className="col50 colleft">
            <div className="wd50">
                <label htmlFor="BookingId">Booked ID</label>
                <input
                  type="text"
                  id="BookingId"
                  name="BookingId"
                  value={formData.BookingId}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="wd50">
                <label htmlFor="FullName">Full Name</label>
                <input
                  type="text"
                  id="FullName"
                  name="FullName"
                  value={formData.FullName}
                  onChange={handleChange}
                  required
                  autoFocus
                />
              </div>
              <div className="wd50">
                <label htmlFor="Address">Address</label>
                <textarea
                  type="url"
                  id="Address"
                  name="Address"
                  value={formData.Address}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>
            <div className="col50 colright">
            <div className="wd50">
                <label htmlFor="DateOfExamination">Examination Date</label>
                <input
                  type="text"
                  id="DateOfExamination"
                  name="DateOfExamination"
                  value={formData.DateOfExamination}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="wd50">
                <label htmlFor="phoneNumber">Phone number</label>
                <input
                  type="tel"
                  id="phoneNumber"
                  name="phoneNumber"
                  value={formData.phoneNumber}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="wd50">
                <label htmlFor="Age">Age</label>
                <input
                  type="tel"
                  id="Age"
                  name="Age"
                  value={formData.Age}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>
          </div>
  
          <div className="col50 colright">
            <div className="col50 colleft">
            <div className="wd50">
                <label htmlFor="NicNumber">NIC</label>
                <input
                  type="text"
                  id="NicNumber"
                  name="NicNumber"
                  value={formData.NicNumber}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="wd50">
                <label htmlFor="DOB">DOB</label>
                <input
                  type="date"
                  id="DOB"
                  name="DOB"
                  value={formData.DOB}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="wd50">
              <label htmlFor="sex">Gender</label>
              <select
                id="sex"
                name="sex"
                value={formData.sex}
                onChange={handleChange}
              >
                <option value=""></option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="others">Others</option>
              </select>
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
  }
  else {
    // login page
    window.location.href = '/';
  }
}

export default MedicalForm