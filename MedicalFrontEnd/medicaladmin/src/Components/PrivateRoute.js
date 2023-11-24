import { jwtDecode } from "jwt-decode";
import { Outlet, Navigate } from "react-router-dom";

const PrivateRoutes = () => {
    const Role = jwtDecode(localStorage.getItem("token")).Role;
    let PageAccess = {};

    if (Role === "MedicalAdmin") {
        PageAccess = { "MedicalPages": true };
    } else if (Role === "RenewalAdmin") {
        PageAccess = { "RenewalPages": true };
    }

    return (
        PageAccess.MedicalPages || PageAccess.RenewalPages ? <Outlet /> : <Navigate to="/" />
    );
};

export default PrivateRoutes;