package com.finalproject.mvpass.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "LicenseDatas")
public class LicenseData {

    @Id
    @Column(name = "LicenseDataId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long licenseDataId;


    private String licenseNo;
    private String licenseType;
    private String nic;
    private String name;
    private String photo;
    private String address;
    private Date dob;
    private String bloodGroup;
    private Date dateOfIssue;
    private Date dateOfExpiry;

    public Long getLicenseDataId() {
        return licenseDataId;
    }

    public void setLicenseDataId(Long licenseDataId) {
        this.licenseDataId = licenseDataId;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(Date dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public LicenseData(String licenseNo, String licenseType, String nic, String name, String photo, String address, Date dob, String bloodGroup, Date dateOfIssue, Date dateOfExpiry) {
        this.licenseNo = licenseNo;
        this.licenseType = licenseType;
        this.nic = nic;
        this.name = name;
        this.photo = photo;
        this.address = address;
        this.dob = dob;
        this.bloodGroup = bloodGroup;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
    }

    public LicenseData() {
    }

    @Override
    public String toString() {
        return "LicenseData{" +
                "licenseDataId=" + licenseDataId +
                ", licenseNo='" + licenseNo + '\'' +
                ", licenseType='" + licenseType + '\'' +
                ", nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfExpiry=" + dateOfExpiry +
                '}';
    }
}
