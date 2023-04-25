//
//package com.openvidu_databases.openvidu_dbbackend.Services;
//
//import com.openvidu_databases.openvidu_dbbackend.Entity.UserAuthEntity;
//
//import com.openvidu_databases.openvidu_dbbackend.Entity.UserEntity;
//import com.openvidu_databases.openvidu_dbbackend.Repository.UserAuthRepository;
//import com.openvidu_databases.openvidu_dbbackend.Repository.UserRepository;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Date;
//import java.util.Map;
//
//
//public class LoginService {
//
//    @Autowired
//    UserAuthRepository userAuthRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Map<String, String> params) {
//        // UserAuthEntity user = userAuthRepository.findByUserName(userName);
//        String userId = params.get("userId");
//        String userPassword = params.get("userPassword");
//        UserEntity user = userRepository.findByUserId(userId);
//
//        if (user != null && user.getUserPassword().equals(userPassword)) {
//            String token = generateToken(userId);
//            // user.setToken(token);
//            // userAuthRepository.save(user);
//            return ResponseEntity.ok(token);
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
//
//    private String generateToken(String userId) {
//        return Jwts.builder()
//                .setSubject(userId)
//                .setIssuedAt(new Date())
//                .setExpiration(new java.sql.Date(System.currentTimeMillis() + 86400000))
//                .signWith(SignatureAlgorithm.HS256, "secret")
//                .compact();
//    }
//
//}
//
////    @PostMapping("/login")
////    public ResponseEntity<String> login(@RequestParam String userName, @RequestParam String password) {
////       // UserAuthEntity user = userAuthRepository.findByUserName(userName);
////
////        if (user != null && user.getUserPassword().equals(password)) {
////            String token = generateToken(userName);
////            user.setToken(token);
////            userAuthRepository.save(user);
////            return ResponseEntity.ok(token);
////        }
////
////        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
////    }
////
////    private String generateToken(String username) {
////        return Jwts.builder()
////                .setSubject(username)
////                .setIssuedAt(new Date())
////                .setExpiration(new java.sql.Date(System.currentTimeMillis() + 86400000))
////                .signWith(SignatureAlgorithm.HS256, "secret")
////                .compact();
////    }
//    /*public boolean isTokenExpired(String token) {
//        Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }*/
////}
