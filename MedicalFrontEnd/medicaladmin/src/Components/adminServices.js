import axios from 'axios';

const API_BASE_ADMIN_URL = 'http://localhost:8080/api/v1/admin';

const authToken = localStorage.getItem("token");
const headers = {
                'Content-Type': 'multipart/form-data',
                "Authorization": authToken ? authToken : '',
            }


export const MedicalDetails = (MedicalData) => {
    const formData = new FormData();
    Object.entries(MedicalData).forEach(([key, value]) => {
        formData.append(key, value);
      });

    return axios.post(`${API_BASE_ADMIN_URL}/medical/medicaldata`, formData, {headers})
        
};

export const loginUser = (admin) => {
    return axios.post(`${API_BASE_ADMIN_URL}/login`, admin)
        
};

export const licenseData = () => {
    return axios.get(`${API_BASE_ADMIN_URL}/renewal/licenseData`, {headers})
        
};

export const LicenseNewUserAdd = (licenseNewUserData) => {
    const formData = new FormData();
    Object.entries(licenseNewUserData).forEach(([key, value]) => {
        formData.append(key, value);
      });
    return axios.post(`${API_BASE_ADMIN_URL}/renewal/newLicenseUser`, formData , {headers})
        
};
