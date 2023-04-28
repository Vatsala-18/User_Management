package com.openvidu_databases.openvidu_dbbackend.Services;

import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
import com.openvidu_databases.openvidu_dbbackend.Repository.UserAuthRepository;
import com.openvidu_databases.openvidu_dbbackend.Repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthRepository userAuthRepository;

    private static final Logger logger= LoggerFactory.getLogger(UserService.class);
    public List<UserEntity> getAllUsers() {
        /*List<UserEntity> userDatas=userRepository.findAll();
        logger.info("User data {}",userDatas);
        for(UserEntity userData:userDatas){
            logger.info("User id {},Address {}",userData.getUser_id(),userData.getAddress());}*/
        logger.info(String.valueOf(userRepository.findAll()));
        return userRepository.findAll();
    }

    public List<UserEntity> getAllChild(String id) {
         return userRepository.findAllChild(id);
    }

    public List<UserEntity> getUserById(String id) {
        logger.info(String.valueOf(userRepository.findById(id)));
        return  userRepository.findById(id);
    }

    public UserEntity createUser(UserEntity user) {
        logger.info("User details {}",user.toString());
       // user.setUserPassword(user.getUserPassword());
        return userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity userDetails) {

        return userRepository.save(userDetails);
    }

    /*public void deleteUser(String id) {
        userRepository.deleteById(id);
    }*/




}

