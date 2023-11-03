import React from 'react';
import './Table.css';
import { useEffect } from 'react';

const DataTable = () => {
  const data = [
    { licenseNo: 1,nic:98888888, name: 'John', add: 30 ,},
    { licenseNo: 2,nic:89999990, name: 'Alice', add: 25 },
    {licenseNo: 3, nic:78889999, name: 'Bob', add: 35 },
  ];
  const sessionTimeout = 60 * 60 * 1000;
  const RenewalAdminToken = localStorage.getItem('RenewalAdminToken')
  const logout = () => {
        localStorage.removeItem("RenewalAdminToken")
        window.location.href = '/'

    }
  useEffect(() => {
        if (RenewalAdminToken) {
            const sessionExpireTimeout = setTimeout(() => {
                localStorage.clear()
                window.location.href = '/';     
            }, sessionTimeout);
            return () => clearTimeout(sessionExpireTimeout);
        }
    }, [RenewalAdminToken, sessionTimeout])

  if(RenewalAdminToken !== null && RenewalAdminToken !== ""){
  return (
    <div className="data-table">
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
          {data.map((item) => (
            <tr key={item.id}>
              <td>{item.licenseNo}</td>
              <td>{item.licensetype}</td>
              <td>{item.nic}</td>
              <td>{item.name}</td>
              <td>{item.photo}</td>
              <td>{item.add}</td>
              <td>{item.dob}</td>
              <td>{item.bloodgroup}</td>
              <td>{item.dateofissue}</td>
              <td>{item.dateofexpiry}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div>
          <button onClick={logout}>logout</button>
        </div>
    </div>
  );}
  else {
    // login page
  window.location.href = '/';
 }
};

export default DataTable;
