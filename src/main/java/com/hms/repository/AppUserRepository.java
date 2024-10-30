package com.hms.repository;

import com.hms.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {


    //this is the finder method for checking if user is present or not

    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String Email);

}
