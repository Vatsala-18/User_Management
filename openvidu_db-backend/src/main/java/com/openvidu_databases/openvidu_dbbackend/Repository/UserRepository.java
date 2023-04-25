package com.openvidu_databases.openvidu_dbbackend.Repository;

import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(nativeQuery=true, value = "select * from user_details")
    List<UserEntity> findAll();

    @Query(nativeQuery=true, value = "select * from user_details where user_id = :userId ")
    Optional<UserEntity> findById(@Param("userId") String userId);

    @Query(nativeQuery=true, value = "select * from user_details where user_id = :userId ")
    UserEntity findByUserId(@Param("userId") String userId);

    @Query(nativeQuery=true, value = "select * from user_details where parent_id = :parentId ")
    List<UserEntity> findAllChild(@Param("parentId") String parentId);
}
