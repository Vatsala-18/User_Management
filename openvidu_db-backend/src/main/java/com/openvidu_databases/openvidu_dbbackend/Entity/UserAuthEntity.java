package com.openvidu_databases.openvidu_dbbackend.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_auth")
public class UserAuthEntity {

    @Id
    @Column(name = "user_code")
    private String userCode;

    @Column(name = "user_id",nullable = false)
    private String userId;

    @Column(nullable = false,name = "username")
    private String userName;

    @Column(nullable = false,name = "password")
    private String userPassword;

    @Column(nullable = false, name = "user_type")
    private String userType;

    @Column(nullable = false, name="token")
    private String token;

    @Column(nullable = false,name="creation_date")
    private LocalDateTime creationDate;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "UserAuthEntity{" +
                "userCode='" + userCode + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userType='" + userType + '\'' +
                ", token='" + token + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
