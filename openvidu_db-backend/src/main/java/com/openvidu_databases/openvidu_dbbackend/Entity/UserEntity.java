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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;

    @Column(name = "user_code")
    private int user_code;

    @Column(name = "user_fname")
    private String user_fname;

    @Column
    private String user_lname;

    @Column
    private String email;

    @Column
    private String mobile;

    @Column
    private String address;

    @Column
    private String user_password;

    @Column
    private String group_permission;

    @Column
    private String user_type;

    @Column
    private String service_type;

    @Column
    private String parent_id;

    @Column
    private int parent_code;

    @Column
    private int max_users;

    @Column
    private int max_participants;

    @Column
    private int active_sessions;

    @Column
    private int max_duration;

    @Column
    private LocalDateTime creation_date;

    @Column
    private LocalDateTime last_login;

    @Column
    private LocalDateTime acc_exp_date;

    @Column
    private String acc_status;


    public String getUser_id() { return user_id; }

    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUserfname() { return user_fname; }

    public void setUserfname(String username) { this.user_fname = username; }

    public String getPassword() { return user_password; }

    public void setPassword(String password) { this.user_password = password; }

    public int getUser_code() { return user_code; }

    public void setUser_code(int user_code) { this.user_code = user_code; }

    public String getUser_fname() { return user_fname; }

    public void setUser_fname(String user_fname) { this.user_fname = user_fname; }

    public String getUser_lname() { return user_lname; }

    public void setUser_lname(String user_lname) { this.user_lname = user_lname; }

    public String getMobile() { return mobile; }

    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getUser_password() { return user_password; }

    public void setUser_password(String user_password) { this.user_password = user_password; }

    public String getGroup_permission() { return group_permission; }

    public void setGroup_permission(String group_permission) { this.group_permission = group_permission; }

    public String getUser_type() { return user_type; }

    public void setUser_type(String user_type) { this.user_type = user_type; }

    public String getService_type() { return service_type; }

    public void setService_type(String service_type) { this.service_type = service_type; }

    public String getParent_id() { return parent_id; }

    public void setParent_id(String parent_id) { this.parent_id = parent_id; }

    public int getParent_code() { return parent_code; }

    public void setParent_code(int parent_code) { this.parent_code = parent_code; }

    public int getMax_users() { return max_users; }

    public void setMax_users(int max_users) { this.max_users = max_users; }

    public int getMax_participants() { return max_participants; }

    public void setMax_participants(int max_participants) { this.max_participants = max_participants; }

    public int getActive_sessions() { return active_sessions; }

    public void setActive_sessions(int active_sessions) { this.active_sessions = active_sessions; }

    public int getMax_duration() { return max_duration; }

    public void setMax_duration(int max_duration) { this.max_duration = max_duration; }

    public LocalDateTime getCreation_date() { return creation_date; }

    public void setCreation_date(LocalDateTime creation_date) { this.creation_date = creation_date; }

    public LocalDateTime getLast_login() { return last_login; }

    public void setLast_login(LocalDateTime last_login) { this.last_login = LocalDateTime.now(); }

    public LocalDateTime getAcc_exp_date() { return acc_exp_date; }

    public void setAcc_exp_date(LocalDateTime acc_exp_date) { this.acc_exp_date = LocalDateTime.now().plusYears(1); }

    public String getAcc_status() { return acc_status; }

    public void setAcc_status(String acc_status) { this.acc_status = acc_status; }

    @Override
    public String toString() {
        return "UserEntity{" +
                "user_id='" + user_id + '\'' +
                ", user_code=" + user_code +
                ", user_fname='" + user_fname + '\'' +
                ", user_lname='" + user_lname + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", user_password='" + user_password + '\'' +
                ", group_permission='" + group_permission + '\'' +
                ", user_type='" + user_type + '\'' +
                ", service_type='" + service_type + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", parent_code=" + parent_code +
                ", max_users=" + max_users +
                ", max_participants=" + max_participants +
                ", active_sessions=" + active_sessions +
                ", max_duration=" + max_duration +
                ", creation_date=" + creation_date +
                ", last_login=" + last_login +
                ", acc_exp_date=" + acc_exp_date +
                ", acc_status='" + acc_status + '\'' +
                '}';
    }
}
