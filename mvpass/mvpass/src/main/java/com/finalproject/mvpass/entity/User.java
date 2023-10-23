package com.finalproject.mvpass.entity;



import jakarta.persistence.*;


@Entity
@Table(name = "mvpassuserr")
public class User {

    @Id
    @Column(name = "user_id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    @Column(name = "userfname", length = 255, nullable = false)
    private String userfname;

    @Column(name = "userlname", length = 255, nullable = false)
    private String userlname;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "NIC", unique = true, nullable = false)
    private String nic;

    @Column(name = "LicenseNo", unique = true, nullable = false)
    private String licenceno;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "mobileNo", nullable = false , unique = true)
    private int mobile;

    @Column(name = "Password", nullable = false, unique = true,length = 60)
    private String password;

    private String role;
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
}