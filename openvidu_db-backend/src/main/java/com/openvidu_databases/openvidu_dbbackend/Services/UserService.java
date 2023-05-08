package com.openvidu_databases.openvidu_dbbackend.Services;

import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
import com.openvidu_databases.openvidu_dbbackend.Repository.UserAuthRepository;
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
    @Autowired
    private UserAuthRepository userAuthRepository;

    private static final Logger logger= LoggerFactory.getLogger(UserService.class);
    public List<UserEntity> getAllUsers() {
        //logger.info(String.valueOf(userRepository.findAll()));
        return userRepository.findAll();
    }

    public List<UserEntity> getAllChild(String id) {
         return userRepository.findAllChild(id);
    }

    public List<UserEntity> getUserById(String id) {
        //logger.info(String.valueOf(userRepository.findById(id)));
        return  userRepository.findById(id);
    }

    public UserEntity createUser(UserEntity user) {
        logger.info("User details {}",user.toString());
       // user.setUserPassword(user.getUserPassword());

        return userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity user, String id) {
        UserEntity existing = userRepository.findByUserId(id);
        existing.setAddress(user.getAddress());
        existing.setEmail(user.getEmail());
        existing.setMobile(user.getMobile());
        existing.setServiceType(user.getServiceType());
        existing.setUserFname(user.getUserFname());
        existing.setUserLname(user.getUserLname());
        existing.setAccExpDate(user.getAccExpDate());
        return userRepository.save(existing);
    }

    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User successfully deleted.";
    }
    
}

