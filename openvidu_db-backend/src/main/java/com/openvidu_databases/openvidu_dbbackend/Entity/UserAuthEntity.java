package com.openvidu_databases.openvidu_dbbackend.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_auth")
public class UserAuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String user_password;

    @Column(nullable = false)
    private String user_type;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime creation_date;


    public String getUserId() { return user_id; }

    public void setUserId(String user_id) { this.user_id = user_id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getUserPassword() { return user_password; }

    public void setUserPassword(String user_password) { this.user_password = user_password; }

    public String getUserType() { return user_type; }

    public void setUserType(String user_type) { this.user_type = user_type; }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public LocalDateTime getCreationDate() { return creation_date; }

    public void setCreationDate(LocalDateTime creation_date) { this.creation_date = creation_date; }



}
