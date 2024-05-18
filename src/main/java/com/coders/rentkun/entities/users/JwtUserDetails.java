//package com.coders.rentkun.entities.users;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.Getter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Data
//public class JwtUserDetails implements UserDetails {
//
//    private final Long id;
//    private final String username;
//    private final String password;
//
////    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
////    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
////    @Column(name = "role", nullable = false)
////    @Enumerated(EnumType.STRING)
////    private Role role;
//
//    private JwtUserDetails(Long id, String username, String password, Role role) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }
//
//    public static JwtUserDetails fromUser(User user) {
//        List<GrantedAuthority> role = new ArrayList<>();
//        role.add(new SimpleGrantedAuthority("user"));
//        return new JwtUserDetails(user.getId(), user.getEmail(), user.getPassword(), role);
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
