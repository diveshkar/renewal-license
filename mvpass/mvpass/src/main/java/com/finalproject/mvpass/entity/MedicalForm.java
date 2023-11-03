package com.finalproject.mvpass.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MedicalForm")
public class MedicalForm {

    @Id
    @Column(name = "MedicalId" , length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalId;

    @Column(name = "FullName")
    private String fullname;

    @Column(name = "BookingId")
    private String bookId;

    @Column(name = "ExaminationDate")
    private Date examinationDate;

    @Column(name = "phoneNumber")
    private Long phoneNumber;

    @Column(name = "Address")
    private String address;

    @Column(name = "NicNumber",unique = true)
    private String nicNumber;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "Age")
    private Long age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "BMI")
    private String bmi;

    @Column(name = "Weight")
    private String weight;

    @Column(name = "lims")
    private String lims;

    @Column(name = "vision")
    private String vision;

    @Column(name = "Hearing")
    private String hearing;

    @Column(name = "Pulse")
    private String pulse;

    @Column(name = "BloodPressure")
    private String bloodpressure;

    @Column(name = "Xray" )
    private String xray;

    @Column(name = "Blood")
    private String blood;

    @Column(name = "RBS" )
    private String rbs;

    @Column(name = "SkelDefuse")
    private String skelDefuse;

    @Column(name = "ProblemInVision")
    private String problemInVision;

    @Column(name = "HeartMurmus" )
    private String heartMurmus;

    @Column(name = "LungDisease")
    private String lungDisease;

    @Column(name = "PsychologicalStatus")
    private String psychologicalStatus;

    @Column(name = "CentralNervousSystem")
    private String centralNervousSystem;

    @Column(name = "MedicalFromApproved" )
    private Boolean medicalFromApproved;

    @Column(name = "text")
    private String text;

    @Column(name = "vehicles")
    private String vehicles;

    @Column(name = "special")
    private String special;

    @Column(name = "height")
    private String height;

    public Long getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(Long medicalId) {
        this.medicalId = medicalId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNicNumber() {
        return nicNumber;
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLims() {
        return lims;
    }

    public void setLims(String lims) {
        this.lims = lims;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getHearing() {
        return hearing;
    }

    public void setHearing(String hearing) {
        this.hearing = hearing;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getBloodpressure() {
        return bloodpressure;
    }

    public void setBloodpressure(String bloodpressure) {
        this.bloodpressure = bloodpressure;
    }

    public String getXray() {
        return xray;
    }

    public void setXray(String xray) {
        this.xray = xray;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getRbs() {
        return rbs;
    }

    public void setRbs(String rbs) {
        this.rbs = rbs;
    }

    public String getSkelDefuse() {
        return skelDefuse;
    }

    public void setSkelDefuse(String skelDefuse) {
        this.skelDefuse = skelDefuse;
    }

    public String getProblemInVision() {
        return problemInVision;
    }

    public void setProblemInVision(String problemInVision) {
        this.problemInVision = problemInVision;
    }

    public String getHeartMurmus() {
        return heartMurmus;
    }

    public void setHeartMurmus(String heartMurmus) {
        this.heartMurmus = heartMurmus;
    }

    public String getLungDisease() {
        return lungDisease;
    }

    public void setLungDisease(String lungDisease) {
        this.lungDisease = lungDisease;
    }

    public String getPsychologicalStatus() {
        return psychologicalStatus;
    }

    public void setPsychologicalStatus(String psychologicalStatus) {
        this.psychologicalStatus = psychologicalStatus;
    }

    public String getCentralNervousSystem() {
        return centralNervousSystem;
    }

    public void setCentralNervousSystem(String centralNervousSystem) {
        this.centralNervousSystem = centralNervousSystem;
    }

    public Boolean getMedicalFromApproved() {
        return medicalFromApproved;
    }

    public void setMedicalFromApproved(Boolean medicalFromApproved) {
        this.medicalFromApproved = medicalFromApproved;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getVehicles() {
        return vehicles;
    }

    public void setVehicles(String vehicles) {
        this.vehicles = vehicles;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public MedicalForm(String fullname, String bookId, Date examinationDate, Long phoneNumber, String address, String nicNumber, Date dob, Long age, String sex, String bmi, String weight, String lims, String vision, String hearing, String pulse, String bloodpressure, String xray, String blood, String rbs, String skelDefuse, String problemInVision, String heartMurmus, String lungDisease, String psychologicalStatus, String centralNervousSystem, Boolean medicalFromApproved, String text, String vehicles, String special, String height) {
        this.fullname = fullname;
        this.bookId = bookId;
        this.examinationDate = examinationDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.nicNumber = nicNumber;
        this.dob = dob;
        this.age = age;
        this.sex = sex;
        this.bmi = bmi;
        this.weight = weight;
        this.lims = lims;
        this.vision = vision;
        this.hearing = hearing;
        this.pulse = pulse;
        this.bloodpressure = bloodpressure;
        this.xray = xray;
        this.blood = blood;
        this.rbs = rbs;
        this.skelDefuse = skelDefuse;
        this.problemInVision = problemInVision;
        this.heartMurmus = heartMurmus;
        this.lungDisease = lungDisease;
        this.psychologicalStatus = psychologicalStatus;
        this.centralNervousSystem = centralNervousSystem;
        this.medicalFromApproved = medicalFromApproved;
        this.text = text;
        this.vehicles = vehicles;
        this.special = special;
        this.height = height;
    }

    public MedicalForm() {
    }

    @Override
    public String toString() {
        return "MedicalForm{" +
                "medicalId=" + medicalId +
                ", fullname='" + fullname + '\'' +
                ", bookId='" + bookId + '\'' +
                ", examinationDate=" + examinationDate +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", nicNumber='" + nicNumber + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", bmi='" + bmi + '\'' +
                ", weight='" + weight + '\'' +
                ", lims='" + lims + '\'' +
                ", vision='" + vision + '\'' +
                ", hearing='" + hearing + '\'' +
                ", pulse='" + pulse + '\'' +
                ", bloodpressure='" + bloodpressure + '\'' +
                ", xray='" + xray + '\'' +
                ", blood='" + blood + '\'' +
                ", rbs='" + rbs + '\'' +
                ", skelDefuse='" + skelDefuse + '\'' +
                ", problemInVision='" + problemInVision + '\'' +
                ", heartMurmus='" + heartMurmus + '\'' +
                ", lungDisease='" + lungDisease + '\'' +
                ", psychologicalStatus='" + psychologicalStatus + '\'' +
                ", centralNervousSystem='" + centralNervousSystem + '\'' +
                ", medicalFromApproved=" + medicalFromApproved +
                ", text='" + text + '\'' +
                ", vehicles='" + vehicles + '\'' +
                ", special='" + special + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
