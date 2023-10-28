import React, { useState } from 'react';
import IconButton from '@mui/material/IconButton';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import TextField from '@mui/material/TextField';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';
import Alert from '@mui/material/Alert';
import './login.css';
import { loginUser } from './userService';

function Login() {
    const [loginForm, setLoginForm] = useState({
        "nic": "",
        "password": ""
    });

    const [successMessage, setSuccessMessage] = useState(null);
    const [errorMessage, setErrorMessage] = useState(null);
    
    

    const handleChange = (e) => {
        setLoginForm({ ...loginForm, [e.target.name]: e.target.value });
    }

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
        loginUser(loginForm)
            .then((response) => {
                console.log(response)
              if (response.data.status === true) {
                    localStorage.setItem("token" , "response.date.token")
                    // setSessionExpire(session)
                    console.log('User logged in:', response.data);
                    setSuccessMessage(response.data.message);
                    setTimeout(() => {setSuccessMessage(null)}, 50000);
                    window.location.href = '/medical-form'; // Redirect after successful login
                }
            else{
                setErrorMessage(response.data.message);
                setTimeout(() => setErrorMessage(null), 50000);
                window.location.reload();
            }
            })
    };

    const [showPassword, setShowPassword] = useState(false);
    const handleClickShowPassword = () => setShowPassword((show) => !show);

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };
    // if(sessionExpire === null){
    //     window.location.href = '/';
    // }
    return (
        <div className="login-container">
            <div className="login-form">
                <h2 className="login-heading">Login Form</h2>
                <form onSubmit={handleLogin}>
                <TextField
                    label="NIC"
                    className="login-input"
                    variant="outlined"
                    name="nic"
                    value={loginForm.nic}
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
export default Login;
