import React, { useState, useEffect } from 'react';
import './Table.css';
import { licenseData } from './adminServices';
import Navbar from './Navbar';

const formatDate = (dateString) => {
  const options = { year: 'numeric', month: 'numeric', day: 'numeric' };
  return new Date(dateString).toLocaleDateString(undefined, options);
};

const DataTable = () => {
  const [licenseUsersDatas, setLicenseUsersDatas] = useState([]);
  const sessionTimeout = 60 * 60 * 1000;
  const token = localStorage.getItem('token');

  const logout = () => {
    localStorage.clear();
    window.location.href = '/';
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await licenseData();
        setLicenseUsersDatas(response.data.licenseDataList);
      } catch (error) {
        console.error('Error:', error);
      }
    };

    if (token) {
      fetchData();
      const sessionExpireTimeout = setTimeout(() => {
        localStorage.clear();
        window.location.href = '/';
      }, sessionTimeout);
      return () => clearTimeout(sessionExpireTimeout);
    }
  }, [token, sessionTimeout]);

  const role = localStorage.getItem("role")

  if (role === "RenewalAdmin") {
    return (
      <div className="data-table">
        {licenseUsersDatas.length > 0 ? (
          <table>
            <thead>
              <tr>
                <th>License Number</th>
                <th>License Type</th>
                <th>NIC Number</th>
                <th>Name</th>
                <th>Photo</th>
                <th>Address</th>
                <th>DOB</th>
                <th>Blood Group</th>
                <th>Date of Issue</th>
                <th>Date of Expiry</th>
              </tr>
            </thead>
            <tbody>
              {licenseUsersDatas.map((LicenseUserData) => (
                <tr key={LicenseUserData.licenseDataId}>
                  <td>{LicenseUserData.licenseNo}</td>
                  <td>{LicenseUserData.licenseType}</td>
                  <td>{LicenseUserData.nic}</td>
                  <td>{LicenseUserData.name}</td>
                  <td>{LicenseUserData.photo}</td>
                  <td>{LicenseUserData.address}</td>
                  <td>{formatDate(LicenseUserData.dob)}</td>
                  <td>{LicenseUserData.bloodGroup}</td>
                  <td>{formatDate(LicenseUserData.dateOfIssue)}</td>
                  <td>{formatDate(LicenseUserData.dateOfExpiry)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <div>No license data available.</div>
        )}
        <div>
          <button onClick={logout}>Logout</button>
        </div>
        <div>
         <Navbar />
         
        </div>
      </div>
    );
  } else {
    window.location.href = '/';
    return null;
  }
};

export default DataTable;
