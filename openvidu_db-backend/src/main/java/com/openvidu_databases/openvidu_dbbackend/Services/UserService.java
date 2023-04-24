package com.openvidu_databases.openvidu_dbbackend.Services;

import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
import com.openvidu_databases.openvidu_dbbackend.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger= LoggerFactory.getLogger(UserService.class);
    public List<UserEntity> getAllUsers() {
        /*List<UserEntity> userDatas=userRepository.findAll();
        logger.info("User data {}",userDatas);
        for(UserEntity userData:userDatas){
            logger.info("User id {},Address {}",userData.getUser_id(),userData.getAddress());}*/

        return userRepository.findAll();
    }

    public List<UserEntity> getAllChild(String id) {
        /*List<UserEntity> userDatas=userRepository.findAll();
        logger.info("User data {}",userDatas);
        for(UserEntity userData:userDatas){
            logger.info("User id {},Address {}",userData.getUser_id(),userData.getAddress());}*/

         return userRepository.findAllChild(id);
    }



    public UserEntity getUserById(String id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    public UserEntity createUser(UserEntity user) {
        logger.info("User details {}",user.toString());
        return userRepository.save(user);
    }

    public UserEntity updateUser(String id, UserEntity userDetails) {
        UserEntity user = userRepository.findById(id)
                .orElse(null);

       // user.setUserfname(userDetails.getUserfname());
        user.setEmail(userDetails.getEmail());

        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


}

