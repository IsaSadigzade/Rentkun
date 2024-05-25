package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.users.Authority;
import com.coders.rentkun.entities.users.Role;
import com.coders.rentkun.entities.users.enums.EAuthority;
import com.coders.rentkun.entities.users.enums.ERole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, AuthorityRepository authorityRepository) {
        return args -> {
            Arrays.stream(ERole.values()).forEach(role -> {
                if (!roleRepository.existsByName(role)) {
                    roleRepository.save(new Role(null, role));
                }
            });

            Arrays.stream(EAuthority.values()).forEach(authority -> {
                if (!authorityRepository.existsByName(authority)) {
                    authorityRepository.save(new Authority(null, authority));
                }
            });
        };
    }
}

