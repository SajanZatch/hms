package com.hms.controller;

import com.hms.Service.UserService;
import com.hms.entity.AppUser;
import com.hms.payload.LoginDto;
import com.hms.payload.TokenDto;
import com.hms.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private AppUserRepository appUserRepository;

    private UserService userSer;

    public UserController(AppUserRepository appUserRepository, UserService userSer) {
        this.appUserRepository = appUserRepository;
        this.userSer = userSer;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody AppUser user
    ) {
        Optional<AppUser> opUsername =
                appUserRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("username already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<AppUser> opEmail =
                appUserRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("Email already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //this is the method for encrypt the password
        //inside gensalt we can give the no.s of round the password should be encrypted
        String encryptedpwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(encryptedpwd);
        user.setRole("ROLE_USER");
        AppUser savedUser = appUserRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

       // http://localhost:8080/login
        @PostMapping("/login")

        public ResponseEntity<?> login(
                @RequestBody   LoginDto dto
        ){
            String token = userSer.verifyLogin(dto);

            if (token!=null){
                TokenDto tokenDto =new TokenDto();
                tokenDto.setToken(token);
                tokenDto.setType("JWT");
                return new ResponseEntity<>(tokenDto,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("invalid user/password",HttpStatus.FORBIDDEN);
            }
        }

    @PostMapping("/signup-property-owner")
    public ResponseEntity<?> createPropertyOwnerUser(
            @RequestBody AppUser user
    ) {
        Optional<AppUser> opUsername =
                appUserRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("username already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<AppUser> opEmail =
                appUserRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("Email already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String encryptedpwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(encryptedpwd);
        user.setRole("ROLE_OWNER");
        AppUser savedUser = appUserRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

}

