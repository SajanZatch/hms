package com.hms.Service;

import com.hms.entity.AppUser;
import com.hms.payload.LoginDto;
import com.hms.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private AppUserRepository appRepo;
    private JWTService jwtService;

    public UserService(AppUserRepository appRepo, JWTService jwtService) {
        this.appRepo = appRepo;
        this.jwtService = jwtService;
    }

    public String verifyLogin(LoginDto dto){
        Optional<AppUser> opUser = appRepo.findByUsername(dto.getUsername());
        if(opUser.isPresent()){
            //it will fetch the appUser object
            AppUser appUser = opUser.get();

            //below code will check the entered password is same in DB or not
            //left-one is the password that we entered and right is the password that present in DB
            if(BCrypt.checkpw(dto.getPassword(),appUser.getPassword())){
                //generate token
                String token = jwtService.generateToken(appUser.getUsername());
                return token;
            }

        }
        else {
            return null;
        }
        return null;
    }
}
