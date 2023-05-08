package com.openvidu_databases.openvidu_dbbackend.Repository;

import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(nativeQuery=true, value = "select * from user_details")
    List<UserEntity> findAll();

    @Query(nativeQuery = true, value = "select exists(select 1 from user_details where user_id = :userId")
    public int ifIdExists(@Param("userId") String userId);

    @Query(nativeQuery=true, value = "select * from user_details where user_id = :userId ")
    List<UserEntity> findById(@Param("userId") String userId);

    @Query(nativeQuery=true, value = "select * from user_details where user_id = :userId ")
    UserEntity findByUserId(@Param("userId") String userId);

    @Query(nativeQuery=true, value = "select * from user_details where parent_id = :parentId ")
    List<UserEntity> findAllChild(@Param("parentId") String parentId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update user_details set last_login = sysdate() where user_id = :userId")
    void setLastLogin(@Param("userId") String userId);

    @Modifying
    @Transactional
    @Query(nativeQuery=true, value = "delete from user_details where user_id = :userId ")
    void deleteById(@Param("userId") String userId);

}
