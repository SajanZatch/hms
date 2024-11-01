package com.hms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    //the moment the project starts running this config file will runs first and
    // we want a the below method to return a object with configruration details(here all the config details will store in http object)
    @Bean
    //It's a builtin class from security
    // this SecurityFilterChain is the method where we configure that which url can be accessed by whom
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
            //csrf() will throws a compile time exception so for deal with this we have to throws a exception
    ) throws Exception
    {
        //h(cd)2
        //cross site request forgery . if we enable this will avoid the csrf but here we disable it
        //api will access from a frontend framework
        //if we enable cors then we can specify the particular domain to access this api but
        //here we want all domain to access the url so make it disable
        // this function will only works with max 3.0.0 version of spring and java 8
        http.csrf().disable().cors().disable();

        //below will helps run the jwtFilter then the AuthorizationFilter method
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

        //haap
        //it will make all the url open for everyone
          http.authorizeHttpRequests().anyRequest().permitAll();
//        http.authorizeHttpRequests().
//                requestMatchers("/api/v1/users/login","/api/v1/users/signup","/api/v1/users/signup-property-owner")
//                .permitAll()
//                .requestMatchers("/api/v1/country/addCountry").hasAnyRole("OWNER","ADMIN")
//                 .anyRequest().authenticated();

        //this will built a http object with abobe config details that have been given
        // if we use return then it will hand over the object to spring IOC
       return http.build();
    }

}