package com.openvidu_databases.openvidu_dbbackend.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "acc_auth")
public class AccountAuthEntity {
    @Id
    @Column(name = "auth_id")
    private int authId;

    @Column(nullable = false, name="account_id")
    private int accountId;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = false, name="auth_key")
    private String authKey;

    @Column(nullable = false,name="creation_date")
    private LocalDateTime creationDate;

    @Column(nullable = false,name="exp_date")
    private LocalDateTime expDate;

}
