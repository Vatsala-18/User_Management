package com.openvidu_databases.openvidu_dbbackend.Controller;

import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
import com.openvidu_databases.openvidu_dbbackend.Repository.UserRepository;
//import com.openvidu_databases.openvidu_dbbackend.Services.UserService;
import com.openvidu_databases.openvidu_dbbackend.Services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<UserEntity> getAllCustomers() {
        logger.info("Request received");
        return userService.getAllUsers();
    }

    @GetMapping("/child/{id}")
    public List<UserEntity> getAllChildById(@PathVariable String id) {
        return userService.getAllChild(id);
    }

    @GetMapping("/{id}")
    public UserEntity getCustomerById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public UserEntity createUser(@RequestBody UserEntity user) {
        logger.info(String.valueOf(user));
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable String id, @RequestBody UserEntity userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        userService.deleteUser(id);
    }






/*@PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        UserAuthEntity user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            String token = generateToken(username);
            user.setToken(token);
            userRepository.save(user);
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new java.sql.Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }
    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

*/
    /*@Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestParam String username, @RequestParam String password) {
        UserEntity user = userRepository.findByUsername(username);

            String token = userService.generateToken(username,password);
            user.setToken(token);
            userRepository.save(user);

        return ResponseEntity.ok(token);
    }
*/

   /* private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity user) {
        if (userService.authenticateUser(user.getUsername(), user.getPassword())) {
            return new ResponseEntity<>("Logged in successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody UserEntity user) {
        String token = userService.generateToken();
        user.setToken(token);
        userService.saveUser(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody UserEntity user) {
        userService.saveUser(user);
        return new ResponseEntity<>("User saved successfully!", HttpStatus.OK);
    }*/
}

