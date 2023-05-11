package com.openvidu_databases.openvidu_dbbackend.Controller;

import com.openvidu_databases.openvidu_dbbackend.Entity.UserAuthEntity;
import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
import com.openvidu_databases.openvidu_dbbackend.Exception.UserNotAuthorizedException;
import com.openvidu_databases.openvidu_dbbackend.Repository.UserAuthRepository;
import com.openvidu_databases.openvidu_dbbackend.Repository.UserRepository;
import com.openvidu_databases.openvidu_dbbackend.Services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${secret.key}")
    private String secret;

    @Value("${access.time}")
    private int accessTime;

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    @GetMapping("/getAll")
    public ResponseEntity<List<UserEntity>> getAllUsers(HttpServletRequest request) {
        logger.info(getHeaders(request).toString());
        logger.info(request.getHeader("id"));
        logger.info(request.getHeader("token"));
        String id = request.getHeader("id");
        String token = request.getHeader("token");
        if (isValidToken(id,token)) {
            return ResponseEntity.ok(userService.getAllUsers());
        }
        else {
            return  new ResponseEntity<List<UserEntity>>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/child/{id}")
    public ResponseEntity<List<UserEntity>> getAllChildById(@PathVariable String id, HttpServletRequest request) {

        String ID = request.getHeader("id");
        String token = request.getHeader("token");
        logger.info(ID);
        logger.info(token);
        if (isValidToken(ID,token)) {
            return ResponseEntity.ok(userService.getAllChild(id));
        }
        else{
            return  new ResponseEntity<List<UserEntity>>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<List<UserEntity>> getUserById(@PathVariable String id, HttpServletRequest request) {

        String ID = request.getHeader("id");
        String token = request.getHeader("token");
        if (isValidToken(ID,token)) {
            logger.info(String.valueOf(userService.getUserById(id)));
            return ResponseEntity.ok(userService.getUserById(id));
        }
        else{
            return  new ResponseEntity<List<UserEntity>>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        //logger.info(getHeaders(request).toString());
        logger.info(String.valueOf(user));
        String ID = request.getHeader("id");
        String token = request.getHeader("token");
        UserEntity u = userRepository.findByUserId(ID);
        logger.info(String.valueOf(user.getFeatures().getClass()));
        logger.info(user.getFeatures().toString());
      //  byte[] byteArray=user.getFeatures();
        user.setFeatures(Arrays.stream(user.getFeatures()).toArray());
        user.setAccessId(Arrays.stream(user.getAccessId()).toArray());
 //       logger.info(String.va(user.setAccessId(Arrays.stream(user.getAccessId()).toArray())));
        //logger.info(user.getFeatures())
//        if(isValidToken(ID,token)) {
            String creation = LocalDateTime.now().format(formatter);
            user.setCreationDate(creation);
            String mypass = passwordEncoder.encode(user.getPassword());
            user.setPassword(mypass);
              return ResponseEntity.ok(userService.createUser(user));
 //           }

//            return  new ResponseEntity<UserEntity>(HttpStatus.UNAUTHORIZED);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable String id, @RequestBody UserEntity user, HttpServletRequest request) {
        String ID = request.getHeader("id");
        String token = request.getHeader("token");

        if(isValidToken(ID,token)) {
            return ResponseEntity.ok(userService.updateUser(user, id));
        }
        else{
            return  new ResponseEntity<UserEntity>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id, @RequestBody UserEntity user, HttpServletRequest request) {
        String ID = request.getHeader("id");
        String token = request.getHeader("token");
        if(isValidToken(ID,token)) {
            return ResponseEntity.ok(userService.deleteUser(id));
        }
        else{
            return new ResponseEntity<UserEntity>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> params) {
        String id = params.get("userId");
        String password = params.get("userPassword");

        logger.info("sessionId : "+id);
        logger.info("password : "+password);

        UserAuthEntity user = userAuthRepository.findById(id);
        UserEntity user1 = userRepository.findByUserId(id);

        if (user1 != null && passwordEncoder.matches(password,user1.getPassword()) && user1.getLoginId().equals(id)) {
            if(isValidTokenLogin(id)){
                HashMap<String,String> response=new HashMap<>();
                response.put("token",user.getToken());
                response.put("user_data",user1.toString());
                userRepository.setLastLogin(id);
                return ResponseEntity.ok(response);
            }
            else {

                if (user1 != null && passwordEncoder.matches(password,user1.getPassword())) {
                    String token = generateToken(id);
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime newDateTime = now.plus(accessTime, ChronoUnit.HOURS);
                    UserAuthEntity ua = userAuthRepository.findById(id);
                    userRepository.setLastLogin(id);

                    if(ua != null){
                        ua.setToken(token);
                        ua.setCreationDate(now);
                        ua.setExpDate(newDateTime);
                    }
                    else{
                        ua = new UserAuthEntity();
                        ua.setLoginId(user1.getLoginId());
                        ua.setUserId(user1.getUserId());
                        ua.setToken(token);
                        ua.setCreationDate(now);
                        ua.setExpDate(newDateTime);
                    }

                    userAuthRepository.save(ua);
                    Map<String,String> res = new HashMap<>();
                    res.put("token",token);
                    res.put("user_data",user1.toString());
                    return new ResponseEntity<>(res, HttpStatus.OK);
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
        //logger.info(token);
        //logger.info(user.getToken());
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

