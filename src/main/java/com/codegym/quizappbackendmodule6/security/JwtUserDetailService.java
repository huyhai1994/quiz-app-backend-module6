package com.codegym.quizappbackendmodule6.security;

import com.codegym.quizappbackendmodule6.model.Role;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.repository.UserRepository;
import com.codegym.quizappbackendmodule6.service.Impl.AuthServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class JwtUserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempting to load user: {}", username);
        User user = userRepository.findByEmail(username);

        if (user == null) {
            logger.error("User not found with email: {}", username);
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        logger.info("User found: {}", user.getEmail());

        Role role = user.getRole();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (role != null) {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            logger.info("User role: {}", role.getName());
        } else {
            logger.warn("User has no role assigned");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
