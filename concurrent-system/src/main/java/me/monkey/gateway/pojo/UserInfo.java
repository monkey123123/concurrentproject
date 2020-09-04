package me.monkey.gateway.pojo;

import java.sql.Timestamp;

/**
 * Created by renzhibin on 17-8-16.
 */
public class UserInfo {

    // info for bind dongle
    private Integer userId;
    private String dongleId;

    // info for login
    private String userName;
    private String password;

    // type for platform;
    private Integer userType;

    // user base info
    private String email;
    private String address;
    private String telephone;

    private Integer citizenType;
    private String citizenId;

    // info for x509 request
    private String commonName;
    private String countryName;
    private String stateOrProvinceName;
    private String localityName;
    // company name
    private String organizationName;
    private String organizationUnitName;

    // reset password need change password first
    private Integer needActive;

    private boolean lock;

    // base database information
    private Integer deleteFlag;
    private String operId;
    private Timestamp oeprTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDongleId() {
        return dongleId;
    }

    public void setDongleId(String dongleId) {
        this.dongleId = dongleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getCitizenType() {
        return citizenType;
    }

    public void setCitizenType(Integer citizenType) {
        this.citizenType = citizenType;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateOrProvinceName() {
        return stateOrProvinceName;
    }

    public void setStateOrProvinceName(String stateOrProvinceName) {
        this.stateOrProvinceName = stateOrProvinceName;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationUnitName() {
        return organizationUnitName;
    }

    public void setOrganizationUnitName(String organizationUnitName) {
        this.organizationUnitName = organizationUnitName;
    }

    public Integer getNeedActive() {
        return needActive;
    }

    public void setNeedActive(Integer needActive) {
        this.needActive = needActive;
    }

    public boolean getLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public Timestamp getOeprTime() {
        return oeprTime;
    }

    public void setOeprTime(Timestamp oeprTime) {
        this.oeprTime = oeprTime;
    }
}
