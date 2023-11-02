import React from 'react';
import './Table.css';

const DataTable = () => {
  const data = [
    { licenseNo: 1,nic:98888888, name: 'John', add: 30 ,},
    { licenseNo: 2,nic:89999990, name: 'Alice', add: 25 },
    {licenseNo: 3, nic:78889999, name: 'Bob', add: 35 },
  ];

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
    </div>
  );
};

export default DataTable;
