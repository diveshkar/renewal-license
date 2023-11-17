import React from 'react';
import { Link } from 'react-router-dom';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Chip from '@mui/material/Chip';


function CustomizedBreadcrumbs() {
  return (
    <Breadcrumbs aria-label="breadcrumb">
      <Chip
        component={Link}
        to="/renewal-admin"
        label="LicenseAdminHome"
        className="breadcrumb-link"
      />
      <Chip
        component={Link}
        to="/LicenseData_form"
        label="LicenseNewUser"
        className="breadcrumb-link"
      />
    </Breadcrumbs>
  );
}

export default CustomizedBreadcrumbs;
