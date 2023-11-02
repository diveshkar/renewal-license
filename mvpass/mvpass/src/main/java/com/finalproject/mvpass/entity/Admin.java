package com.finalproject.mvpass.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin {

    @Id
    @Column(name = "Admin_id" , length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AdminId;

    @Column(name = "Admin_Name", unique = true)
    private String adminname;

    @Column(name = "Admin_Password", unique = true)
    private String adminpassword;

    @Column(name = "Role")
    private String role;

    public Long getAdminId() {
        return AdminId;
    }

    public void setAdminId(Long adminId) {
        AdminId = adminId;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Admin(String adminname, String adminpassword, String role) {
        this.adminname = adminname;
        this.adminpassword = adminpassword;
        this.role = role;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "AdminId=" + AdminId +
                ", adminname='" + adminname + '\'' +
                ", adminpassword='" + adminpassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
