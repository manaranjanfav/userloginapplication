package com.loginandregistration.loginandregistration.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.loginandregistration.loginandregistration.Dto.registrationdto;
import com.loginandregistration.loginandregistration.model.Role;
import com.loginandregistration.loginandregistration.model.User;
import com.loginandregistration.loginandregistration.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Userserviceimpl implements Userservice {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(registrationdto userdto) {
        User user = new User(userdto.getFirstname(), userdto.getLastname(), userdto.getEmail(), passwordEncoder.encode(userdto.getPassword()), Arrays.asList(new Role("Role User")));
        return userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(null == user){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRoleAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoleAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        
    }
    
    
}
