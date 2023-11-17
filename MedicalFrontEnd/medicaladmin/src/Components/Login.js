import React, { useState } from 'react';
import IconButton from '@mui/material/IconButton';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import TextField from '@mui/material/TextField';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Radio from '@mui/material/Radio';
import SendIcon from '@mui/icons-material/Send';
import FormControlLabel from '@mui/material/FormControlLabel';
import Button from '@mui/material/Button';
import Alert from '@mui/material/Alert';
import RadioGroup from '@mui/material/RadioGroup';
import './login.css';
import { loginUser } from './adminServices';

function Login() {
    const [loginForm, setLoginForm] = useState({
        adminname: "",
        password: "",
        role:null
    });

    const [successMessage, setSuccessMessage] = useState(null);
    const [errorMessage, setErrorMessage] = useState(null);
    
    

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;

        if (type === 'radio') {
            if (checked) {
                setLoginForm({ ...loginForm, role: name});
            }
        } else {
            setLoginForm({ ...loginForm, [name]: value });
        }
    };

    const styles = {
        alert: {
            position: 'fixed',
            top: '10px', 
            left: '10px', 
            fontSize: '20px',
            padding: '20px',
        },
    };

    const handleLogin = (e) => {
        e.preventDefault();
        // console.log(loginForm);
        loginUser(loginForm)
            .then((response) => {
                
                // console.log(response)
              if (response.data.status === true && response.data.role === "MedicalAdmin") {
                    // console.log('User logged in:', response.data);
                    setSuccessMessage(response.data.message + " " + response.data.role);
                    // window.alert(response.data.message);
                    setTimeout(() => {setSuccessMessage(null)}, 5000);
                    localStorage.setItem("token", (response.data.token))
                    localStorage.setItem("role", (response.data.role))
                    // console.log("Token and role set:", response.data.token, response.data.role);
                    // window.location.href = '/medical-admin'; 
                    // window.alert(response.data.medicalAdminToken)
                }
            else if(response.data.status === true && response.data.role === "RenewalAdmin"){
               
                // console.log('User logged in:', response.data);
                setSuccessMessage(response.data.message + " " + response.data.role);
                // window.alert(response.data.message)
                setTimeout(() => {setSuccessMessage(null)}, 5000);
                localStorage.setItem("token" , (response.data.token))
                localStorage.setItem("role", (response.data.role))
                // window.location.href = '/renewal-admin';
                console.log("Token and role set:", response.data.token, response.data.role);
            
                
            }
            else{
                setErrorMessage(response.data.message );
                // window.Error(response.data.message)
                setTimeout(() => setErrorMessage(null), 50000);
                window.location.reload();
            }
            }
            )
    };

    const [showPassword, setShowPassword] = useState(false);
    const handleClickShowPassword = () => setShowPassword((show) => !show);

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };
    // const MedicalAdminToken = localStorage.getItem('MedicalAdminToken')
    const token = localStorage.getItem('token');
    const role = localStorage.getItem("role")
   
    if(token){
        if(role === "MedicalAdmin"){
            window.location.href = '/medical-admin';
        }
    else{
            window.location.href = '/renewal-admin';
        }
    } 
    else{
        return (
            <div className="login-container">
                {/* {MedicalAdminToken} */}
                <div className="login-form">
                    <h2 className="login-heading">Login Form</h2>
                    <form onSubmit={handleLogin}>
                    <TextField
                        label="AdminName"
                        className="login-input"
                        variant="outlined"
                        name="adminname"
                        value={loginForm.adminname}
                        style={{ marginTop: '35px', marginBottom: '15px' }}
                        onChange={handleChange}
                    />
                    <FormControl className="login-input" variant="outlined" style={{ marginTop: '25px' }}>
                        <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
                        <OutlinedInput
                            id="password"
                            type={showPassword ? 'text' : 'password'}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={handleClickShowPassword}
                                        onMouseDown={handleMouseDownPassword}
                                        edge="end"
                                    >
                                        {showPassword ? <VisibilityOff /> : <Visibility />}
                                    </IconButton>
                                </InputAdornment>
                            }
                            label="Password"
                            name="password" 
                            value={loginForm.password}
                            onChange={handleChange}
                        />
                    </FormControl>
                   
                    {/* <label>Role:</label><br />
                    <label>RenewalAdmin</label> */}
                <br/><br/>
               
                    <FormControl>
          
          <RadioGroup
            row
            aria-labelledby="demo-row-radio-buttons-group-label"
            name="row-radio-buttons-group"
          >
            <FormControlLabel name='RenewalAdmin' checked={loginForm.role === "RenewalAdmin"} control={<Radio />} label="RenewalAdmin" onChange={handleChange} />
            <FormControlLabel name='MedicalAdmin' checked={loginForm.role === "MedicalAdmin"} control={<Radio />} label="MedicalAdmin" onChange={handleChange}/>
          </RadioGroup>
        </FormControl>
    
    
                    <Button variant="contained" endIcon={<SendIcon />} className="login-button" style={{ marginTop: '40px' }} type='submit'>
                        Login
                    </Button>
                    </form>
                </div>
                {successMessage && (
                        <Alert severity="success" style={styles.alert}>
                            {successMessage}
                        </Alert>
                    )}
                    {errorMessage && (
                        <Alert severity="error"style={styles.alert}>
                            {errorMessage}
    
                        </Alert>
                    )}
            </div>
        );

    }
    
}
export default Login;
