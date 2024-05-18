package com.coders.rentkun.services;

import com.coders.rentkun.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

//    public UserDetails loadUserById(Long id) {
//        return JwtUserDetails.fromUser(findUserById(id));
//    }
//
//    protected User findUserById(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
}
