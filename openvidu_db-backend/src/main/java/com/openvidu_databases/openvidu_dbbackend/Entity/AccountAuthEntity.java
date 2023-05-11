//package com.openvidu_databases.openvidu_dbbackend.Entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "acc_auth")
//public class AccountAuthEntity {
//    @Id
//    @Column(name = "auth_id")
//    private int authId;
//
//    @Column(nullable = false, name="account_id")
//    private int accountId;
//
//    @Column(nullable = false, name="name")
//    private String name;
//
//    @Column(nullable = false, name="auth_key")
//    private String authKey;
//
//    @Column(nullable = false,name="creation_date")
//    private LocalDateTime creationDate;
//
//    @Column(nullable = false,name="exp_date")
//    private LocalDateTime expDate;
//
//    public int getAuthId() {
//        return authId;
//    }
//
//    public void setAuthId(int authId) {
//        this.authId = authId;
//    }
//
//    public int getAccountId() {
//        return accountId;
//    }
//
//    public void setAccountId(int accountId) {
//        this.accountId = accountId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAuthKey() {
//        return authKey;
//    }
//
//    public void setAuthKey(String authKey) {
//        this.authKey = authKey;
//    }
//
//    public LocalDateTime getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(LocalDateTime creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    public LocalDateTime getExpDate() {
//        return expDate;
//    }
//
//    public void setExpDate(LocalDateTime expDate) {
//        this.expDate = expDate;
//    }
//
//    @Override
//    public String toString() {
//        return "AccountAuthEntity{" +
//                "authId=" + authId +
//                ", accountId=" + accountId +
//                ", name='" + name + '\'' +
//                ", authKey='" + authKey + '\'' +
//                ", creationDate=" + creationDate +
//                ", expDate=" + expDate +
//                '}';
//    }
//}
