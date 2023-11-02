import React , {useState} from 'react';
import { useEffect } from 'react';
import './medical-form.css';

function MedicalForm() {

  const [formData, setFormData] = useState({
    //medicalform
    FullName: '',
    BookingId: '',
    ExaminationDate:'',
    phoneNumber: '',
    Address: '',
    NicNumber: '',
    DOB:'',
    Age:'',
    sex:'',
    //image:'',

    //physcial exam
   BMI:'',
   Weight:'',
   skelpresent:false,
   skelAbsent:false,
   lims:'',
   Right: false,
   Left: false,
   vision:'',
   Hearing:'',
   Pulse:'',
   BloodPressure:'',
   HmPresent:false,
   HmAbsent:false,
   lungPresent:false,
   lungAbsent:false,
   stable:false,
   unstable:false,
   Normal:false,
   upNormal:false,

   Xray:'',
   Blood:'',
   RBS:'',


  });

  const handleChange = (e) => {
    const { name, value, type , checked} = e.target;
    if(type === "checkbox"){
      setFormData({...formData,[name]:checked})
    }else{
      setFormData({ ...formData, [name]: value });
    }    
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
    console.log(formData)
  };
  const sessionTimeout = 60 * 60 * 1000;
  const MedicalAdminToken = localStorage.getItem('MedicalAdminToken')
  const logout = () => {
        localStorage.removeItem("MedicalAdminToken")
        window.location.href = '/'

    }
  useEffect(() => {
        if (MedicalAdminToken) {
            const sessionExpireTimeout = setTimeout(() => {
                localStorage.removeItem("token");
                window.location.href = '/';     
            }, sessionTimeout);
            return () => clearTimeout(sessionExpireTimeout);
        }
    }, [MedicalAdminToken, sessionTimeout])
  
 //if(MedicalAdminToken !== null && MedicalAdminToken !== ""){
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
                  type="date"
                  id="DateOfExamination"
                  name="ExaminationDate"
                  value={formData.ExaminationDate}
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
            <div className="col50 colright">
            <div className="wd50">
                <label htmlFor="image">Upload the image</label>
                
                <input
                  type="alt"
                  id="image"
                  name="image"
                  value={formData.image}
                  onChange={handleChange}
                  required
                />
              </div>
              </div>
              </div>
          
  
          <div className="wd100">
            <hr />
          </div>
          {/* <div className="wd100">
            <button name="submit" type="submit" id="" data-submit="...Sending">
              Submit
            </button>
            </div> */}



          <h3>Physical Examination</h3>
          <h4>1. General Exam</h4>
          <div className="col50 colleft">
            <div className="col50 colleft">
            <div className="wd50">
                <label htmlFor="BookingId">Pulse</label>
                <input
                  type="text"
                  id="Pulse"
                  name="Pulse"
                  value={formData.Pulse}
                  onChange={handleChange}
                  required
                />
              </div>
              
              </div>
              <div className="col50 colright">
            <div className="wd50">
                <label htmlFor="BMI">BMI</label>
                <input
                  type="text"
                  id="BMI"
                  name="BMI"
                  value={formData.BMI}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>
          
  
             
              <div className="wd50">
                <label>Any Skeletal / limb deformities: </label>
              
                <label>Present</label>
                <input
                type="checkbox"
                  //id="Present"
                  style={{
                    width: '15px', // Adjust the width as needed
          Pulse: '15px',
                    }}
                  name="skelpresent"
                  checked={formData.skelpresent}
                  onChange={handleChange}
                  
                  />
                   <label>Absent</label>
                <input
                type="checkbox"
                style={{
                  width: '15px', // Adjust the width as needed
        Pulse: '15px',
                  }}
                  //id="Absent"
                  name="skelAbsent"
                  checked={formData.skelAbsent}
                  onChange={handleChange}
                  
                  />
                  
              </div>
              <br/>
              <br/>
              <br/><br/>
              
            <div className="wd50">
              <label htmlFor="lims">Partial or Complete amputation of finger or lims </label>
              <input
                  type="text"
                  id="lims"
                  name="lims"
                  value={formData.lims}
                  onChange={handleChange}
                  required
                />
                <br/>
            </div>
            
            
            <div className="wd50">
            <div className="wd50">
                <label>Vision:     </label>
              
                <label>Right</label>
                <input
                type="checkbox"
                  style={{
                    width: '15px', // Adjust the width as needed
          Pulse: '15px',
                    }}
                  name="Right"
                  checked={formData.Right}
                  onChange={handleChange}
                  
                  />
                   <label>Left</label>
                <input
                type="checkbox"
                style={{
                  width: '15px', // Adjust the width as needed
        Pulse: '15px',
                  }}
                 
                  name="Left"
                  checked={formData.Left}
                  onChange={handleChange}
                  
                  />
                  
              </div>
              
              
              <br/><br/>
              <div className="col50 colleft">
              <label htmlFor="Vision"><c>Vision corrected by</c> </label>
              <select
                id="vision"
                name="vision"
                value={formData.vision}
                onChange={handleChange}
              >
                <option value=""></option>
                <option value="None">None</option>
                <option value="Spectacle">Spectacle</option>
                <option value="Contact Lenses">Contact Lenses</option>
                <option value="Squint">Squint</option>
              </select>
            </div>
            </div>
            <div className="wd50">
            <div className="col50 colleft">
              <label htmlFor="">Hearing</label>
              <select
                id="Hearing"
                name="Hearing"
                value={formData.Hearing}
                onChange={handleChange}
              >
                <option value=""></option>
                <option value="Satisfactory">Satisfactory</option>
                <option value="Un Satisfactory">Un Satisfactory</option>
                <option value="Satisfactory with the aid">Satisfactory with the aid </option>
              </select>
            </div>
         </div>
            </div>
  
          <div className="col50 colright">
            <div className="col50 colleft">
            <div className="wd50">
                <label htmlFor="weight">Weight</label>
                <input
                  type="text"
                  id="Weight"
                  name="Weight"
                  value={formData.Weight}
                  onChange={handleChange}
                  required
                />
              </div>
             
             
            </div>
          </div>
          
          <div className="wd100">
            <hr />
          </div>

          <h4>2. Cardiovscular System </h4>
          <div className="col50 colleft">
          <div className="col50 colleft">
            
            <div className="wd50">
                <label htmlFor="Pulse">Pulse</label>
                <input
                  type="text"
                  id="Pulse"
                  name="Pulse"
                  value={formData.Pulse}
                  onChange={handleChange}
                  required
                />

              </div>
              <div className="col80 colleft">
            
            <div className="wd50">
                <label htmlFor="BP">Blood Pressure</label>
                <input
                  type="text"
                  id="BP"
                  name="BloodPressure"
                  value={formData.BloodPressure}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>
            </div>

            <div className="wd50">
            <div className="wd50">
                <label>Heart Murmus:     </label>
              
                <label>Present</label>
                <input
                type="checkbox"
                  style={{
                    width: '15px', // Adjust the width as needed
          Pulse: '15px',
                    }}
                  name="HmPresent"
                  checked={formData.HmPresent}
                  onChange={handleChange}
                  
                  />
                   <label>Absent</label>
                <input
                type="checkbox"
                style={{
                  width: '15px', // Adjust the width as needed
        Pulse: '15px',
                  }}
                 
                  name="HmAbsent"
                  checked={formData.HmAbsent}
                  onChange={handleChange}
                  
                  />
              </div>
              </div>
            </div>
            <div className="wd100">
            <hr />
          </div>
          
           <h4>3.Respiratory System</h4>
           <div className="wd50">
                <label>Any lung disease : </label>
              
                <label>Present</label>
                <input
                type="checkbox"
                  //id="Present"
                  style={{
                    width: '15px', // Adjust the width as needed
          Pulse: '15px',
                    }}
                  name="lungPresent"
                  checked={formData.lungPresent}
                  onChange={handleChange}
                  
                  />
                   <label>Absent</label>
                <input
                type="checkbox"
                style={{
                  width: '15px', // Adjust the width as needed
        Pulse: '15px',
                  }}
                  //id="Absent"
                  name="lungAbsent"
                  checked={formData.lungAbsent}
                  onChange={handleChange}
                  
                  />
                  
              </div>
              <br/>
              <div className="wd100">
            <hr />
          </div>
          <h4>5.Psychological Status</h4>
          <div className="wd50">
                <label>Stable</label>
                <input
                type="checkbox"
                  
                  style={{
                    width: '15px', // Adjust the width as needed
          Pulse: '15px',
                    }}
                  name="stable"
                  checked={formData.stable}
                  onChange={handleChange}
                  
                  />
                   <label>Unstable</label>
                <input
                type="checkbox"
                style={{
                  width: '15px', // Adjust the width as needed
        Pulse: '15px',
                  }}
                  
                  name="unstable"
                  checked={formData.unstable}
                  onChange={handleChange}
                  
                  />
                  
              </div>
              <br/>
              <div className="wd100">
            <hr />
          </div>
          <h4>4.Central Nervous System</h4>
          <div className="wd50">
                <label>Normal</label>
                <input
                type="checkbox"
                  
                  style={{
                    width: '15px', // Adjust the width as needed
          Pulse: '15px',
                    }}
                  name="Normal"
                  checked={formData.Normal}
                  onChange={handleChange}
                  
                  />
                   <label>Upnormal</label>
                <input
                type="checkbox"
                style={{
                  width: '15px', // Adjust the width as needed
        Pulse: '15px',
                  }}
                  
                  name="upNormal"
                  checked={formData.upNormal}
                  onChange={handleChange}
                  
                  />
                  
              </div>
              <br/>
              <div className="wd100">
            <hr />
          </div>

          <h3> Special Laboratory Investigations</h3>
          <div className="col50 colleft">
            <div className="col50 colleft">
            <div className="wd50">
            <label htmlFor=" RBS">RBS</label>
                <input
                  type="text"
                  id="RBS"
                  name="RBS"
                  value={formData.RBS}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="wd50">
                <label htmlFor=" Xray">Chest Xray/ ECG</label>
                <input
                  type="text"
                  id="Xray"
                  name="Xray"
                  value={formData.Xray}
                  onChange={handleChange}
                  required
                  autoFocus
                />
              </div>
            </div>
            <div className="col50 colright">
            <div className="wd50">
                <label htmlFor="Blood">Blood Group</label>
                <input
                  type="text"
                  id="Blood"
                  name="Blood"
                  value={formData.Blood}
                  onChange={handleChange}
                  required
                />
              </div>
             
             
            </div>
          </div>
  
          <div className="wd100">
            <hr />
          </div>
          <h3> Recommendations of the Consultant / Technical Assessor (if only) </h3>
          <div className="col100 colleft">
            <div className="wd500">
                <input
                  type="text"
                  id="text"
                  name="text"
                  value={formData.text}
                  onChange={handleChange}
                />
              </div>
              </div>
              <div className="wd100">
            <hr />
          </div>

          <h3> Recommendations of the Medical Officer</h3>

          <p> <input
                type="checkbox"
                style={{
                  width: '15px', // Adjust the width as needed
        Pulse: '15px',
                  }}
                  
                  name="vechicles "
                  checked={formData.vehicles}
                  onChange={handleChange}
                  
                  />I accept her for driving the follwing vehicles on public highway. </p>
          <div className="wd30">
          <label htmlFor="">vehicles</label>
              <select
                id="Vehicles"
                name="Vehicles"
                value={formData.Vehicles}
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
              <br/><br/>
              <div className="wd30">
          <label htmlFor="">Special Conditions</label>
              <select
                id="Special"
                name="Special"
                value={formData.Special}
                onChange={handleChange}
              >
                <option value=""></option>
                <option value="none">None</option>
                <option value="Spectacle">Must wear Spectacle</option>
                <option value="Musthearings">Must wear hearings and while driving</option>
                <option value="medicalexam">Periodic medical Examination required</option>
                <option value="regulartreat">Regular treatment required</option>
                
              </select>
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
 // else {
    // login page
  // window.location.href = '/';
 // }
//}

export default MedicalForm  