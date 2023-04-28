package com.openvidu_databases.openvidu_dbbackend.Repository;

import com.openvidu_databases.openvidu_dbbackend.Entity.UserAuthEntity;
import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM user_auth WHERE user_id=:userId")
    UserAuthEntity findById(@Param("userId") String userId);
//    @Query(nativeQuery = true, value = "DELETE FROM user_auth WHERE exp_date <= :expDate")
//    UserAuthEntity deleteIfExpired(@Param("expDate") LocalDateTime expDate);
//    @Query(nativeQuery = true, value = "DELETE FROM user_auth WHERE exp_date <= CURRENT_TIMESTAMP")
//    UserAuthEntity deleteIfExpired();
}
