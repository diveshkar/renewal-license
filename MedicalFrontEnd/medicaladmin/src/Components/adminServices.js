import axios from 'axios';

const API_BASE_ADMIN_URL = 'http://localhost:8080/api/v1/admin';


export const MedicalDetails = (MedicalData) => {
    return axios.post(`${API_BASE_ADMIN_URL}/medicaldata`, MedicalData);
};

export const loginUser = (admin) => {
    return axios.post(`${API_BASE_ADMIN_URL}/login`, admin);
};

