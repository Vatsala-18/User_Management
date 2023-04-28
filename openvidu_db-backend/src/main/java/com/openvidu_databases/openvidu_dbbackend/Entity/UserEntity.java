package com.openvidu_databases.openvidu_dbbackend.Entity;
import java.time.LocalDateTime;

import lombok.Data;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@EntityScan
@Data
@Table(name = "user_details")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_code")
    private int userCode;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "user_fname")
    private String userFname;

    @Column(name = "user_lname")
    private String userLname;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "group_permission")
    private String groupPermission;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "parent_code")
    private int parentCode;

    @Column(name = "max_users")
    private int maxUsers;

    @Column(name = "max_participants")
    private int maxParticipants;

    @Column(name = "active_sessions")
    private int activeSessions;

    @Column(name = "max_duration")
    private int maxDuration;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "acc_exp_date")
    private LocalDateTime accExpDate;

    @Column(name = "acc_status")
    private String accStatus;

 /*   @Type(type = "json")
    @Column(name = "features", columnDefinition = "json")
    private JsonNode features;

    public JsonNode getFeatures() { return features; }

    public void setFeatures(JsonNode features) { this.features = features; }

*/
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUserLname() {
        return userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserPassword() { return userPassword; }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getGroupPermission() {
        return groupPermission;
    }

    public void setGroupPermission(String groupPermission) {
        this.groupPermission = groupPermission;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getParentCode() {
        return parentCode;
    }

    public void setParentCode(int parentCode) {
        this.parentCode = parentCode;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getActiveSessions() {
        return activeSessions;
    }

    public void setActiveSessions(int activeSessions) {
        this.activeSessions = activeSessions;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getAccExpDate() {
        return accExpDate;
    }

    public void setAccExpDate(LocalDateTime accExpDate) {
        this.accExpDate = accExpDate;
    }

    public String getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "user_id='" + userId + '\'' +
                ", user_code=" + userCode +
                ", user_fname='" + userFname + '\'' +
                ", user_lname='" + userLname + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", user_password='" + userPassword + '\'' +
                ", group_permission='" + groupPermission + '\'' +
                ", user_type='" + userType + '\'' +
                ", service_type='" + serviceType + '\'' +
                ", parent_id='" + parentId + '\'' +
                ", parent_code=" + parentCode +
                ", max_users=" + maxUsers +
                ", max_participants=" + maxParticipants +
                ", active_sessions=" + activeSessions +
                ", max_duration=" + maxDuration +
                ", creation_date=" + creationDate +
                ", last_login=" + lastLogin +
                ", acc_exp_date=" + accExpDate +
                ", acc_status='" + accStatus + '\'' +
                '}';
    }
}
