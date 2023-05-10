package com.openvidu_databases.openvidu_dbbackend.Controller;

import com.openvidu_databases.openvidu_dbbackend.Entity.AccountEntity;
import com.openvidu_databases.openvidu_dbbackend.Entity.UserAuthEntity;
import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
import com.openvidu_databases.openvidu_dbbackend.Repository.AccountRepository;
import com.openvidu_databases.openvidu_dbbackend.Repository.UserAuthRepository;
import com.openvidu_databases.openvidu_dbbackend.Repository.UserRepository;
import com.openvidu_databases.openvidu_dbbackend.Services.AccountService;
import com.openvidu_databases.openvidu_dbbackend.Services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
import java.util.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private static final Logger logger= LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private  AccountRepository accountRepository;

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
    public ResponseEntity<List<AccountEntity>> getAllAccounts(HttpServletRequest request) {
        logger.info(getHeaders(request).toString());
        logger.info(request.getHeader("id"));
        logger.info(request.getHeader("token"));
        String id = request.getHeader("id");
        String token = request.getHeader("token");
        if (isValidToken(id,token)) {
            return ResponseEntity.ok(accountService.getAllAccounts());
        }
        else {
            return  new ResponseEntity<List<AccountEntity>>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<AccountEntity> getAccountById(@PathVariable String id, HttpServletRequest request) {

        String ID = request.getHeader("id");
        String token = request.getHeader("token");
        if (isValidToken(ID,token)) {
            logger.info(String.valueOf(userService.getUserById(id)));
            return ResponseEntity.ok(accountService.getAccountById(id));
        }
        else{
            return  new ResponseEntity<AccountEntity>(HttpStatus.UNAUTHORIZED);
        }
    }

  /*  @PostMapping("/create")
    public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountEntity account, HttpServletRequest request, HttpServletResponse response) {
        //logger.info(getHeaders(request).toString());
        //logger.info(String.valueOf(user));
        String ID = request.getHeader("id");
        String token = request.getHeader("token");
       // AccountEntity acc = accountRepository.findById(ID);

        if(isValidToken(ID,token)) {
            String creation = LocalDateTime.now().format(formatter);
            account.setCreationDate(creation);
            return ResponseEntity.ok(accountService.createAccount(account));
        }

        return  new ResponseEntity<AccountEntity>(HttpStatus.UNAUTHORIZED);

    }

   */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Map<String,Object> params, HttpServletRequest request, HttpServletResponse response) {
        String ID = request.getHeader("id");
        String token = request.getHeader("token");

        if(isValidToken(ID,token)) {

            String creation = LocalDateTime.now().format(formatter);
            AccountEntity acc = new AccountEntity();
            UserEntity user = new UserEntity();
            acc.setAccountId((Integer) params.get("accountId"));
            acc.setName(params.get("name").toString());
            acc.setAddress(params.get("address").toString());
            acc.setLogo((byte[]) params.get("logo"));
            acc.setCreationDate(creation);
            acc.setMaxAdmin((Integer) params.get("maxAdmin"));
            acc.setMaxUser((Integer) params.get("maxUser"));
            acc.setSession((HashMap<String, String>) params.get("session"));
//            acc.setFeatures((HashMap<String, String>) params.get("features"));
//            acc.setFeaturesMeta((HashMap<String, String>) params.get("featuresMeta"));
//            acc.setAccessId((HashMap<String, String>) params.get("accessId"));
            acc.setFeatures((params.get("features").toString()));
            //       acc.setFeaturesMeta(params.get("featuresMeta").toString());
            acc.setAccessId(params.get("accessId").toString());

            acc.setExpDate((String) params.get("expDate"));

            user.setAccountId((Integer) params.get("accountId"));
            user.setLoginId(params.get("loginId").toString());
            String pass = params.get("password").toString();
            String mypass = passwordEncoder.encode(pass);
            user.setPassword(mypass);
            user.setContact(params.get("contact").toString());
            user.setEmail(params.get("email").toString());
            user.setUserType(params.get("userType").toString());
            user.setCreationDate(creation);
            user.setSession((HashMap<String, String>) params.get("session2"));
//            user.setFeatures((HashMap<String, String>) params.get("features2"));
//            user.setFeaturesMeta((HashMap<String, String>) params.get("featuresMeta2"));
//            user.setAccessId((HashMap<String, String>) params.get("accessId2"));


            user.setFeatures((params.get("features2").toString()));
            //        user.setFeaturesMeta(params.get("featuresMeta2").toString());
            user.setAccessId(params.get("accessId2").toString());

            accountService.createAccount(acc);
            userService.createUser(user);

            return new ResponseEntity<>("Account Created",HttpStatus.CREATED);
        }

        return  new ResponseEntity<>("Unauthorised User",HttpStatus.UNAUTHORIZED);

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
