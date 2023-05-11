package com.openvidu_databases.openvidu_dbbackend.Entity;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashMap;

@Entity
@EntityScan
@Data
@Table(name = "account_data")
public class AccountEntity {

    @Id
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "creation_date")
    private String creationDate;

    @Column(name = "max_admin")
    private int maxAdmin;

    @Column(name = "max_user")
    private int maxUser;

    @Column(name="session",columnDefinition="text")
    @Type(type="com.openvidu_databases.openvidu_dbbackend.Utils.MapType")
    private HashMap<String, String> session = new HashMap<String, String>(0);

    @Column(name = "features")
    private int[] features;

    @Column(name="features_meta",columnDefinition="text")
    @Type(type="com.openvidu_databases.openvidu_dbbackend.Utils.MapType")
    private HashMap<String, String> featuresMeta = new HashMap<String, String>(0);

    @Column(name = "access_id")
    private int[] accessId;
//    @Column(name="features",columnDefinition="text")
//    @Type(type="com.openvidu_databases.openvidu_dbbackend.Utils.MapType")
//    private HashMap<String, String> features = new HashMap<String, String>(0);
//
//    @Column(name="features_meta",columnDefinition="text")
//    @Type(type="com.openvidu_databases.openvidu_dbbackend.Utils.MapType")
//    private HashMap<String, String> featuresMeta = new HashMap<String, String>(0);
//
//    @Column(name="access_id",columnDefinition="text")
//    @Type(type="com.openvidu_databases.openvidu_dbbackend.Utils.MapType")
//    private HashMap<String, String> accessId = new HashMap<String, String>(0);

    @Column(name = "status")
    private int status;

    @Column(name = "exp_date")
    private String expDate;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getMaxAdmin() {
        return maxAdmin;
    }

    public void setMaxAdmin(int maxAdmin) {
        this.maxAdmin = maxAdmin;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(int maxUser) {
        this.maxUser = maxUser;
    }

    public HashMap<String, String> getSession() {
        return session;
    }

    public void setSession(HashMap<String, String> session) {
        this.session = session;
    }

    public int[] getFeatures() {
        return features;
    }

    public void setFeatures(int[] features) {
        this.features = features;
    }

    public HashMap<String, String> getFeaturesMeta() {
        return featuresMeta;
    }

    public void setFeaturesMeta(HashMap<String, String> featuresMeta) {
        this.featuresMeta = featuresMeta;
    }

    public int[] getAccessId() {
        return accessId;
    }

    public void setAccessId(int[] accessId) {
        this.accessId = accessId;
    }
//    public HashMap<String, String> getFeatures() {
//        return features;
//    }
//
//    public void setFeatures(HashMap<String, String> features) {
//        this.features = features;
//    }
//
//    public HashMap<String, String> getFeaturesMeta() {
//        return featuresMeta;
//    }
//
//    public void setFeaturesMeta(HashMap<String, String> featuresMeta) {
//        this.featuresMeta = featuresMeta;
//    }
//
//    public HashMap<String, String> getAccessId() {
//        return accessId;
//    }
//
//    public void setAccessId(HashMap<String, String> accessId) {
//        this.accessId = accessId;
//    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", logo=" + Arrays.toString(logo) +
                ", creationDate='" + creationDate + '\'' +
                ", maxAdmin='" + maxAdmin + '\'' +
                ", maxUser='" + maxUser + '\'' +
                ", session=" + session +
                ", features=" + features +
                ", featuresMeta=" + featuresMeta +
                ", accessId=" + accessId +
                ", status=" + status +
                ", expDate='" + expDate + '\'' +
                '}';
    }
}


