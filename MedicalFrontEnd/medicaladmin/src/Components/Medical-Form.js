import React from 'react'
import { useEffect } from 'react'

function MedicalForm() {


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
        <div>
            <button onClick={logout}>logout</button>
        </div>
      )
  }
  else {
    // login page
    window.location.href = '/';
  }
}

export default MedicalForm