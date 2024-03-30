package com.myblog3.security;

import com.myblog3.entity.Role;
import com.myblog3.entity.User;
import com.myblog3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsServices implements UserDetailsService {
    
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsServices(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    //loadByUsername:- it is one of method the spring security provide
    // which is present under the userDetailService interface so we override and complete the method and supply the username and email
    //UsernameNotFoundException:-This is the builtin class of spring security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users =userRepository.findByUsernameOrEmail(username,username).
                orElseThrow(() ->
        new UsernameNotFoundException("User is not found with this username or email"+username));
        return new  org.springframework.security.core.userdetails.
        User(users.getEmail(),users.getPassword(),mapRolesToAuthorities(users.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles)
    {
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
