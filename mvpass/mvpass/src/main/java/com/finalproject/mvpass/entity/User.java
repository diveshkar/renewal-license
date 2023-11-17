package com.finalproject.mvpass.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.joda.time.DateTime;


@Entity
@Table(name = "mvpassuserr")
public class User {

    @Id
    @Column(name = "user_id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    @NotBlank(message = "First name is required")
    @Size(max = 255, message = "First name cannot be longer than 255 characters")
    @Column(name = "userfname", length = 255, nullable = false)
    private String userfname;

    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Last name cannot be longer than 255 characters")
    @Column(name = "userlname", length = 255, nullable = false)
    private String userlname;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot be longer than 255 characters")
    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @NotBlank(message = "NIC is required")
    @Column(name = "NIC", unique = true, nullable = false)
    private String nic;

    @NotBlank(message = "License number is required")
    @Column(name = "LicenseNo", unique = true, nullable = false)
    private String licenceno;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Gender is required")
    @Column(name = "gender", nullable = false)
    private String gender;

    @Min(value = 1000000000, message = "Mobile number must be at least 10 digits")
    @Column(name = "mobileNo", nullable = false, unique = true)
    private int mobile;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",
            message = "Password must be at least 8 characters, including one uppercase letter and one symbol")
    @NotBlank(message = "Password is required")
    @Column(name = "Password", nullable = false, unique = true, length = 60)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private DateTime loginAt;
    @Temporal(TemporalType.TIMESTAMP)
    private DateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private DateTime updateAt;
    private String role;

    @AssertTrue(message = "User must be enabled")
    private boolean enabled = false;

    public long getUserid() {

        return userid;
    }

    public void setUserid(long userid) {

        this.userid = userid;
    }

    public String getUserfname() {
        return userfname;
    }

    public void setUserfname(String userfname) {

        this.userfname = userfname;
    }

    public String getUserlname() {

        return userlname;
    }

    public void setUserlname(String userlname) {

        this.userlname = userlname;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getNic() {

        return nic;
    }

    public void setNic(String nic) {

        this.nic = nic;
    }

    public String getLicenceno() {

        return licenceno;
    }

    public void setLicenceno(String licenceno) {

        this.licenceno = licenceno;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    public int getMobile() {

        return mobile;
    }

    public void setMobile(int mobile) {

        this.mobile = mobile;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    public boolean isEnabled() {

        return enabled;
    }

    public void setEnabled(boolean enabled) {

        this.enabled = enabled;
    }

    public DateTime getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(DateTime loginAt) {
        this.loginAt = loginAt;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(DateTime updateAt) {
        this.updateAt = updateAt;
    }

    public User(String userfname, String userlname, String address, String nic, String licenceno, String email, String gender, int mobile, String password) {
        this.userfname = userfname;
        this.userlname = userlname;
        this.address = address;
        this.nic = nic;
        this.licenceno = licenceno;
        this.email = email;
        this.gender = gender;
        this.mobile = mobile;
        this.password = password;
    }

    public User() {
    }

    @PrePersist
    public void before(){
        DateTime currentDareTime = new DateTime();

        this.createdAt = currentDareTime;
        this.updateAt = currentDareTime;
    }

    @PostPersist
    public void onupdate(){
        DateTime currentDateTime = new DateTime();
        this.updateAt = currentDateTime;
    }
}