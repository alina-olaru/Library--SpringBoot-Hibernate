package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.model.BookUser;
import com.alina.mylibrary.repository.Admin.BookUserRepository;
import com.alina.mylibrary.service.Interfaces.Admin.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService, JwtUserDetailsService {

    @Autowired
    private BookUserRepository bookUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<BookUser> users = this.bookUserRepository.findByEmailAdress(username);

        if(users.size() == 0){
            users = this.bookUserRepository.findByUsername(username);
        }

        if (users.size()>0) {
            BookUser user = users.stream().findFirst().orElse(null);
            String role = user.isAdminPrivilege() ? "ADMIN" : "BASIC";
            GrantedAuthority authority = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return role;
                }
            };

            return new User(username, passwordEncoder.encode(user.getPassword()),
                    new ArrayList<GrantedAuthority>(Arrays.asList(authority)));

        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
