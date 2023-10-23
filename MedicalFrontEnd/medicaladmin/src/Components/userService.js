import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/v1/user';

export const registerUser = (user) => {
    return axios.post(`${API_BASE_URL}/register`, user);
};

export const loginUser = (user) => {
    return axios.post(`${API_BASE_URL}/login`, user);
};
