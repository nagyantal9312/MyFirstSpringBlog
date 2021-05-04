package com.example.springblog.service;

import com.example.springblog.model.Blogger;
import com.example.springblog.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private BloggerRepository bloggerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Blogger blogger = bloggerRepository.findByUsername(username);
        if (blogger == null) {
            throw new UsernameNotFoundException("Not able to find the user named: " + username);
        }

        return (UserDetails) blogger;
    }


}
