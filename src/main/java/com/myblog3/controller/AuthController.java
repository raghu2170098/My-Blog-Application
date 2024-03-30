package com.myblog3.controller;

import com.myblog3.entity.Role;
import com.myblog3.entity.User;
import com.myblog3.payload.LoginDto;
import com.myblog3.payload.SignUpDto;
import com.myblog3.repository.RoleRepository;
import com.myblog3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    /* you develop AuthenticationManager bean all the username and password through config file
     that gave it to spring security basically your authentication menager gets that detail.
    *
    *
    * */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    //http://localhost:9091/api/auth/signup
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
                (loginDto.getUsernameOremail(), loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //Session
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity("User signin successful",HttpStatus.OK);
    }
    /*
    How do i tell this is spring security
    this is my username and password refer to the during comparision so we go to the config class
     becasue anything that ready in the configuration class moment spring boot project
     started this all the detailed are given to spring boot before any job spring does
     its scan the spring configuration class and only after understanding the configuration class
    its does the required things */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto)
    {
        if(userRepository.existsByUsername(signUpDto.getUsername()))
        {
            return new ResponseEntity("Username is Already Exist", HttpStatus.BAD_REQUEST);
        }
        else if (userRepository.existsByEmail(signUpDto.getEmail()))
        {
            return new ResponseEntity("Email Id is Already Exist", HttpStatus.BAD_REQUEST);
        }
        User user=new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles = roleRepository.findByName(signUpDto.getRoleType()).get();
        Set<Role> convertRoleToSet=new HashSet<>();
        convertRoleToSet.add(roles);
        user.setRoles(convertRoleToSet);
        userRepository.save(user);
        return new ResponseEntity<>("User Register Successfully",HttpStatus.OK);
    }
}
