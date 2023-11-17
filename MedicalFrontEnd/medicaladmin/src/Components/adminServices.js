import axios from 'axios';

const API_BASE_ADMIN_URL = 'http://localhost:8080/api/v1/admin';

// Create a function to get the authorization headers dynamically
// const getAuthorizationConfig = () => {
//     const authToken = localStorage.getItem("token");
//     return {
//         headers: {
//             'Content-Type': 'multipart/form-data',
//             "Authorization": authToken ? authToken : '',
//         },
//     };
// };

// // Set up a request interceptor for setting headers
// axios.interceptors.request.use(
//     (config) => {
//         const authToken = localStorage.getItem("token");
//         if (authToken) {
//             config.headers.Authorization = authToken;
//         }
//         return config;
//     },
//     (error) => {
//         return Promise.reject(error);
//     }
// );

const authToken = localStorage.getItem("token");
const headers = {
                'Content-Type': 'multipart/form-data',
                "Authorization": authToken ? authToken : '',
            }

export const MedicalDetails = (MedicalData) => {
    const formData = new FormData();
    // formData.append('medicalFormData', JSON.stringify(MedicalData));
    // formData.append('image', MedicalData.image);
    Object.entries(MedicalData).forEach(([key, value]) => {
        formData.append(key, value);
      });

    return axios.post(`${API_BASE_ADMIN_URL}/medical/medicaldata`, formData, {headers})
        
};

export const loginUser = (admin) => {
    return axios.post(`${API_BASE_ADMIN_URL}/login`, admin)
        
};

export const licenseData = () => {
    return axios.get(`${API_BASE_ADMIN_URL}/renewal/licenseData`)
        
};

export const LicenseNewUserAdd = (licenseNewUserData) => {
    return axios.post(`${API_BASE_ADMIN_URL}/renewal/newLicenseUser`, licenseNewUserData)
        
};
