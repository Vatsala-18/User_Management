package com.openvidu_databases.openvidu_dbbackend.Repository;

import com.openvidu_databases.openvidu_dbbackend.Entity.UserAuthEntity;
import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Integer> {

    @Query(nativeQuery = true,value = "SELECT EXISTS(SELECT 1 from user_auth WHERE user_id=:userId)")
    Boolean isValid(@Param("userId") String userId);
    @Query(nativeQuery = true, value = "DELETE FROM user_auth WHERE exp_date <= CURRENT_TIMESTAMP")
    UserAuthEntity deleteIfExpired();
}
