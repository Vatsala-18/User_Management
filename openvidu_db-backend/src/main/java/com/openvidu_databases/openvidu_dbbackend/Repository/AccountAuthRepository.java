package com.openvidu_databases.openvidu_dbbackend.Repository;

import com.openvidu_databases.openvidu_dbbackend.Entity.AccountAuthEntity;
import com.openvidu_databases.openvidu_dbbackend.Entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAuthRepository extends JpaRepository<AccountAuthEntity, Integer> {

}
