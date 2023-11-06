import axios from 'axios';

const API_BASE_ADMIN_URL = 'http://localhost:8080/api/v1/admin';


export const MedicalDetails = (MedicalData) => {
    return axios.post(`${API_BASE_ADMIN_URL}/medical/medicaldata`, MedicalData);
};

export const loginUser = (admin) => {
    return axios.post(`${API_BASE_ADMIN_URL}/login`, admin);
};

export const licenseData = () => {
    return axios.get(`${API_BASE_ADMIN_URL}/renewal/licenseData`)
};

export const LicenseNewUserAdd = (licenseNewUserData) => {
    return axios.post(`${API_BASE_ADMIN_URL}/renewal/newLicenseUser`, licenseNewUserData)
};
