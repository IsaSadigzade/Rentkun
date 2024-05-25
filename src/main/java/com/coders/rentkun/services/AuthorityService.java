package com.coders.rentkun.services;

import com.coders.rentkun.entities.users.Authority;
import com.coders.rentkun.entities.users.enums.EAuthority;
import com.coders.rentkun.repositories.AuthorityRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority findByName(String authorityName) {
        return authorityRepository.findByName(EAuthority.valueOf(authorityName));
    }
}
