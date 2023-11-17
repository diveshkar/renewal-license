import React , {useRef, useState} from 'react';
import { useEffect } from 'react';
import './medical-form.css';
import { MedicalDetails } from './adminServices';
// import {UploadOutlined} from 'antd';



function MedicalForm() {

  const [medicalFormData, setMedicalFormData] = useState({
    //medicalform
    fullname: '',
    bookId: '',
    examinationDate:new Date(),
    phoneNumber:0,
    address: '',
    nicNumber: null,
    dob:new Date(),
    age:0,
    sex:'',
    licenseImgUrl:null,

    //physcial exam
   bmi:'',
   weight:'',
   lims:'',
   vision:'',
   hearing:'',
   pulse:'',
   bloodpressure:'',
   xray:'',
   blood:'',
   rbs:'',
   text:'',
   vehicles:'',
   special:'',
   height:'',
   image:null,

   //RadioInput
   skelDefuse:null,
   problemInVision:null,
   heartMurmus:null,
   lungDisease:null,
   psychologicalStatus:null,
   centralNervousSystem:null,
   medicalFromApproved:false

  });

  const [imageURL, setImageURL] = useState('');
  const inputRef = useRef(null);
  const handleChange = (e) => {
    const { name, value, type ,checked, files } = e.target;
    if(type === "radio"){
        setMedicalFormData({...medicalFormData, [name]:value})
       
    }else if(type === "checkbox"){
       setMedicalFormData({...medicalFormData, [name]:checked})
    }
    else if (type === "file") {
      setMedicalFormData({ ...medicalFormData, [name]: files[0] });
    
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
      setMedicalFormData({ ...medicalFormData, [name]: value });
    }   
  // setMedicalFormData({...medicalFormData, image:files[0]})
  };

  // const clearImage = (e) => {
  //   setMedicalFormData({...medicalFormData, image:''})
  //   setImageURL(null)
  
  //   if(inputRef.current){
  //     inputRef.current.value = '';
  //   }
  
  //   console.log(medicalFormData);
  // }
  const showImg = () =>{
    const uploadimg = document.querySelector(".licenseImg")
    // const uploadBUtton = document.getElementById("image")
    uploadimg.style.display = "flex"
  }
  const MedicalFormSubmit = (e) => {
    e.preventDefault();
    console.log(medicalFormData)
    MedicalDetails(medicalFormData)
        .then((response) => {
            window.alert(response.data.message);
            const url = response.data.imageUrl;
            // setImageURL(url);
            console.log(url);
            localStorage.setItem("img",JSON.stringify(url))
        })
        .catch((error) => {
            window.alert("An error occurred:", error);
            
        });
};

  const sessionTimeout = 60 * 60 * 1000;
  const token = localStorage.getItem('token')
  const role = localStorage.getItem("role")
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
  
 if(role === "MedicalAdmin"){
    return (
        // <div>
        //     <button onClick={logout}>logout</button>
            
        // </div>
        <div className="container">
          <h1>{role}</h1>
        <form className="contact" onSubmit={MedicalFormSubmit} enctype="multipart/form-data">
          <h3>Medical Form</h3>
          <div className="col50 colleft"  >
            <div className="col50 colleft">
            <div className="wd50" >
                <label htmlFor="bookId">Booked ID</label>
                <input
                  type="text"
                  id="bookId"
                  name="bookId"
                  value={medicalFormData.bookId}
                  onChange={handleChange}
                  // required
                />
              </div>
              <div className="wd50">
                <label htmlFor="fullname">Full Name</label>
                <input
                  type="text"
                  id="fullname"
                  name="fullname"
                  value={medicalFormData.fullname}
                  onChange={handleChange}
                  // required
                  autoFocus
                />
              </div>
              <div className="wd50">
                <label htmlFor="address">Address</label>
                <textarea
                  type="url"
                  id="address"
                  name="address"
                  value={medicalFormData.address}
                  onChange={handleChange}
                  // required
                ></textarea>
              </div>
            </div>
            <div className="col50 colright">
            <div className="wd50">
                <label htmlFor="DateOfExamination">Examination Date</label>
                <input
                  type="date"
                  id="DateOfExamination"
                  name="examinationDate"
                  value={medicalFormData.examinationDate
                    ? new Date(medicalFormData.examinationDate).toISOString().split('T')[0]
                    : ''}
                  onChange={handleChange}
                  // required
                />
              </div>
              <div className="wd50">
                <label htmlFor="phoneNumber">Phone number</label>
                <input
                  type="text"
                  id="phoneNumber"
                  name="phoneNumber"
                  value={medicalFormData.phoneNumber}
                  onChange={handleChange}
                  // required
                />
              </div>
              <div className="wd50">
                <label htmlFor="age">Age</label>
                <input
                  type="text"
                  id="age"
                  name="age"
                  value={medicalFormData.age}
                  onChange={handleChange}
                  // required
                />
              </div>
            </div>
          </div>
  
          <div className="col50 colright">
            <div className="col50 colleft">
            <div className="wd50" >
                <label htmlFor="nicNumber">NIC</label>
                <input
                  type="text"
                  id="nicNumber"
                  name="nicNumber"
                  value={medicalFormData.nicNumber}
                  onChange={handleChange}
                  // required
                />
              </div>
              <div className="wd50">
                <label htmlFor="dob">DOB</label>
                <input
                  type="date"
                  id="dob"
                  name="dob"
                  value={medicalFormData.dob
                    ? new Date(medicalFormData.dob).toISOString().split('T')[0]
                    : ''}
                  onChange={handleChange}
                  // required
                />
              </div>
              <div className="wd50">
              <label htmlFor="sex">Gender</label>
              <select
                id="sex"
                name="sex"
                value={medicalFormData.sex}
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
               <img className ="licenseImg" src={imageURL} alt='licenseImage'/>
               <div className="wd50">
                {/* <label htmlFor="image">Upload the image</label> */}
                {/* <div className='imgbtn'>
                  <div className='subimgbtn1'>
                
                  </div>
                  <div className='subimgbtn2'>

                  </div>
                   
                </div> */}
                <br />
                 <input
                  type="file"
                  id="image"
                  name="image"
                  onChange={handleChange}
                  ref={inputRef}
                  onClick={showImg}
                  
                  // required
                /> 
              </div>
               {/* <br />
               <button onClick={clearImage}>ClearImage</button> */}
              </div>
              </div>
          
  
          <div className="wd100">
            <hr />
          </div>
  
       <h3>Physical Examination</h3>
          <h4>1. General Exam</h4>
          <div className="col50 colleft">
            <div className="col50 colleft">
            <div className="wd50">
                <label htmlFor="Height">Height</label>
                <input
                  type="text"
                  id="height"
                  name="height"
                  value={medicalFormData.height}
                  onChange={handleChange}
                  // required
                />
              </div>
              
              </div>
              <div className="col50 colright">
            <div className="wd50">
                <label htmlFor="BMI">BMI</label>
                <input
                  type="text"
                  id="bmi"
                  name="bmi"
                  value={medicalFormData.bmi}
                  onChange={handleChange}
                  // required
                />
              </div>
            </div>
          
  
             
              <div className="wd50">
                <label>Any Skeletal / limb deformities: </label>
              
                <label>Present</label>
                <input
                type="radio"
                  //id="Present"
                  style={{
                    width: '15px', // Adjust the width as needed
          pulse: '15px',
                    }}
                  name="skelDefuse"
                  value="present"
                  checked={medicalFormData.skelDefuse === "present"}
                  onChange={handleChange}
                  
                  />
                   <label>Absent</label>
                <input
                type="radio"
                style={{
                  width: '15px', // Adjust the width as needed
        pulse: '15px',
                  }}
                  //id="Absent"
                  name="skelDefuse"
                  value="Absent"
                  checked={medicalFormData.skelDefuse === "Absent"}
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
                  value={medicalFormData.lims}
                  onChange={handleChange}
                  // required
                />
                <br/>
            </div>
            
            
            <div className="wd50">
            <div className="wd50">
                <label>Problem In Vision:     </label>
              
                <label>Right</label>
                <input
                type="radio"
                  style={{
                    width: '15px', // Adjust the width as needed
                    pulse: '15px',
                    }}
                  name="problemInVision"
                  value="Right"
                  checked={medicalFormData.problemInVision === "Right"}
                  onChange={handleChange}
                  
                  />
                   <label>Left</label>
                <input
                type="radio"
                style={{
                  width: '15px', // Adjust the width as needed
                  pulse: '15px',
                  }}
                 
                  name="problemInVision"
                  value="Left"
                  checked={medicalFormData.problemInVision === "Left"}
                  onChange={handleChange}
                  
                  />
                  <label>Both</label>
                  <input
                type="radio"
                  style={{
                    width: '15px', // Adjust the width as needed
                    pulse: '15px',
                    }}
                  name="problemInVision"
                  value="Both"
                  checked={medicalFormData.problemInVision === "Both"}
                  onChange={handleChange}
                  
                  />
                   <label>None</label>
                <input
                type="radio"
                style={{
                  width: '15px', // Adjust the width as needed
                  pulse: '15px',
                  }}
                 
                  name="problemInVision"
                  value="None"
                  checked={medicalFormData.problemInVision === "None"}
                  onChange={handleChange}
                  
                  />
              </div>
              
              
              <br/><br/>
              <div className="col50 colleft">
              <label htmlFor="Vision"><c>Vision corrected by</c> </label>
              <select
                id="vision"
                name="vision"
                value={medicalFormData.vision}
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
              <label htmlFor="Hearing">Hearing</label>
              <select
                id="hearing"
                name="hearing"
                value={medicalFormData.hearing}
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
                  id="weight"
                  name="weight"
                  value={medicalFormData.weight}
                  onChange={handleChange}
                  // required
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
                <label htmlFor="pulse">Pulse</label>
                <input
                  type="text"
                  id="pulse"
                  name="pulse"
                  value={medicalFormData.pulse}
                  onChange={handleChange}
                  // required
                />

              </div>
              <div className="col80 colleft">
            
            <div className="wd50">
                <label htmlFor="BP">Blood Pressure</label>
                <input
                  type="text"
                  id="BP"
                  name="bloodpressure"
                  value={medicalFormData.bloodpressure}
                  onChange={handleChange}
                  // required
                />
              </div>
            </div>
            </div>

            <div className="wd50">
            <div className="wd50">
                <label>Heart Murmus:     </label>
              
                <label>Present</label>
                <input
                type="radio"
                  style={{
                    width: '15px', // Adjust the width as needed
          Pulse: '15px',
                    }}
                  name="heartMurmus"
                  value="Present"
                  checked={medicalFormData.heartMurmus === "Present"}
                  onChange={handleChange}
                  
                  />
                   <label>Absent</label>
                <input
                type="radio"
                style={{
                  width: '15px', // Adjust the width as needed
        Pulse: '15px',
                  }}
                 
                  name="heartMurmus"
                  value="Absent"
                  checked={medicalFormData.heartMurmus === "Absent"}
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
                type="radio"
                  //id="Present"
                  style={{
                    width: '15px', // Adjust the width as needed
          Pulse: '15px',
                    }}
                  name="lungDisease"
                  value="Present"
                  checked={medicalFormData.lungDisease === "Present"}
                  onChange={handleChange}
                  
                  />
                   <label>Absent</label>
                <input
                type="radio"
                style={{
                  width: '15px', // Adjust the width as needed
        Pulse: '15px',
                  }}
                  //id="Absent"
                  name="lungDisease"
                  value="Absent"
                  checked={medicalFormData.lungDisease === "Absent"}
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
                type="radio"
                  
                  style={{
                    width: '15px', // Adjust the width as needed
          Pulse: '15px',
                    }}
                  name="psychologicalStatus"
                  value="Stable"
                  checked={medicalFormData.psychologicalStatus === "Stable"}
                  onChange={handleChange}
                  
                  />
                   <label>Unstable</label>
                <input
                type="radio"
                style={{
                  width: '15px', // Adjust the width as needed
        Pulse: '15px',
                  }}
                  
                  name="psychologicalStatus"
                  value="Unstable"
                  checked={medicalFormData.psychologicalStatus === "Unstable"}
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
                type="radio"
                  
                  style={{
                    width: '15px', // Adjust the width as needed
          Pulse: '15px',
                    }}
                  name="centralNervousSystem"
                  value="Normal"
                  checked={medicalFormData.centralNervousSystem === "Normal"}
                  onChange={handleChange}
                  
                  />
                   <label>UpNormal</label>
                <input
                type="radio"
                style={{
                  width: '15px', // Adjust the width as needed
        Pulse: '15px',
                  }}
                  
                  name="centralNervousSystem"
                  value="UpNormal"
                  checked={medicalFormData.centralNervousSystem === "UpNormal"}
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
            <label htmlFor=" rbs">RBS</label>
                <input
                  type="text"
                  id="rbs"
                  name="rbs"
                  value={medicalFormData.rbs}
                  onChange={handleChange}
                  // required
                />
              </div>
              <div className="wd50">
                <label htmlFor="Xray">Chest Xray/ ECG</label>
                <input
                  type="text"
                  id="xray"
                  name="xray"
                  value={medicalFormData.xray}
                  onChange={handleChange}
                  // required
                  autoFocus
                />
              </div>
            </div>
            <div className="col50 colright">
            <div className="wd50">
            <label htmlFor="blood">Blood Group</label>
          <select
            id="blood"
            name="blood"
            value={medicalFormData.blood}
            onChange={handleChange}
          >
            <option value=""></option>
            <option value="A+">A+</option>
            <option value="A-">A-</option>
            <option value="B+">B+</option>
            <option value="B-">B-</option>
            <option value="O+">O+</option>
            <option value="O-">O-</option>
            
          </select>
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
                  value={medicalFormData.text}
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
                  
                  name="medicalFromApproved"
                  checked={medicalFormData.medicalFromApproved}
                  onChange={handleChange}
                  
                  />I accept her for driving the follwing vehicles on public highway. </p>
          <div className="wd30">
          <label htmlFor="">vehicles</label>
              <select
                id="vehicles"
                name="vehicles"
                value={medicalFormData.vehicles}
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
          <label htmlFor="Special Conditions">Special Conditions</label>
              <select
                id="special"
                name="special"
                value={medicalFormData.special}
                onChange={handleChange}
              >
                <option value=""></option>
                <option value="none">None</option>
                <option value="Spectacle">Must wear Spectacle</option>
                <option value="Musthearings">Must wear hearings and while driving</option>
                {/* <option value="medicalexam">Periodic medical Examination required</option> */}
                {/* <option value="regulartreat">Regular treatment required</option> */}
                
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
        {/* <img src="C:/Users/Diveshkar/Desktop/DSC_2202.jpg"  /> */}
      </div>

      
  
      )
  }
 else {
    // login page
  window.location.href = '/';
 }
}

export default MedicalForm  