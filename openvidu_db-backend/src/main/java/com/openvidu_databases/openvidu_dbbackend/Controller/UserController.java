package com.openvidu_databases.openvidu_dbbackend.Controller;

import com.openvidu_databases.openvidu_dbbackend.Entity.UserAuthEntity;
import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
import com.openvidu_databases.openvidu_dbbackend.Exception.UserNotAuthorizedException;
import com.openvidu_databases.openvidu_dbbackend.Repository.UserAuthRepository;
import com.openvidu_databases.openvidu_dbbackend.Repository.UserRepository;
//import com.openvidu_databases.openvidu_dbbackend.Services.UserService;
import com.openvidu_databases.openvidu_dbbackend.Services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

//    @Autowired
//    private UserAuthEntity userAuthEntity;

    @Value("${secret.key}")
    private String secret;

    @Value("${access.time}")
    private int accessTime;

    @GetMapping("/getAll")
    public Object getAllUsers(HttpServletRequest request) {
        logger.info(getHeaders(request).toString());
        logger.info(request.getHeader("id"));
        logger.info(request.getHeader("token"));
        String id = request.getHeader("id");
        String token = request.getHeader("token");
        if (isValidToken(id,token)) {
            return userService.getAllUsers();
        }
        else{
            return new UserNotAuthorizedException("Access Denied");
        }
    }

    @GetMapping("/child/{id}")
    public List<UserEntity> getAllChildById(@PathVariable String id) {

        return userService.getAllChild(id);
    }

    @GetMapping("/getById/{id}")
    public UserEntity getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public UserEntity createUser(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        logger.info(getHeaders(request).toString());
        logger.info(String.valueOf(user));
        return userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public UserEntity updateUser(@PathVariable String id, @RequestBody UserEntity userDetails) {
        userDetails.setUserId(id);
        return userService.updateUser(userDetails);
    }

    /*@DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable String id) {
        userService.deleteUser(id);
    }*/

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> params) {
        String id = params.get("userId");
        String password = params.get("userPassword");

        UserAuthEntity user = userAuthRepository.findById(id);
        UserEntity user1 = userRepository.findByUserId(id);

        if (user1 != null && user1.getUserPassword().equals(password) && user1.getUserId().equals(id)) {
            if(isValidTokenLogin(id)){
                HashMap<String,String> response=new HashMap<>();
                response.put("token",user.getToken());
                return ResponseEntity.ok(response);
            }
            else {

                if (user1 != null && user1.getUserPassword().equals(password)) {
                    String token = generateToken(id);
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime newDateTime = now.plus(accessTime, ChronoUnit.HOURS);
                    UserAuthEntity ua = userAuthRepository.findById(id);

                    if(ua != null){
                        ua.setToken(token);
                        ua.setCreationDate(now);
                        ua.setExpDate(newDateTime);
                    }
                    else{
                        ua = new UserAuthEntity();
                        ua.setUserId(user1.getUserId());
                        ua.setUserCode(user1.getUserCode());
                        ua.setToken(token);
                        ua.setCreationDate(now);
                        ua.setExpDate(newDateTime);
                    }
                    userAuthRepository.save(ua);
                    return ResponseEntity.ok(token);
                }
           }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new java.sql.Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean isValidToken(String id,String token){
        UserAuthEntity user = userAuthRepository.findById(id);
        logger.info(token);
        logger.info(user.getToken());
        String t = (user.getToken());
        if(user == null || user.getExpDate().isBefore(LocalDateTime.now()) || token == null || !(t.equals(token)))
            return false;
        return true;
    }

    public Boolean isValidTokenLogin(String id){
        UserAuthEntity user = userAuthRepository.findById(id);
        if(user == null || user.getExpDate().isBefore(LocalDateTime.now()) )
            return false;
        return true;
    }

   private Map<String, String> getHeaders(HttpServletRequest request) {
       Enumeration<String> headers = request.getHeaderNames();
       Map<String, String> headerMap = new HashMap<>();
       while (headers.hasMoreElements()) {
           String header = headers.nextElement();
           headerMap.put(header, request.getHeader(header));
       }
       return headerMap;
   }
}

