package com.openvidu_databases.openvidu_dbbackend.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_auth")
public class UserAuthEntity {

    @Id
    @Column(name = "user_code")
    private int userCode;

    @Column(name = "user_id",nullable = false)
    private String userId;

    @Column(nullable = false, name="token")
    private String token;

    @Column(nullable = false,name="creation_date")
    private LocalDateTime creationDate;

    @Column(nullable = false,name="exp_date")
    private LocalDateTime expDate;

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public LocalDateTime getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDateTime expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "UserAuthEntity{" +
                "userCode='" + userCode + '\'' +
                ", userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", creationDate=" + creationDate +
                ", expDate=" + expDate +
                '}';
    }
}
