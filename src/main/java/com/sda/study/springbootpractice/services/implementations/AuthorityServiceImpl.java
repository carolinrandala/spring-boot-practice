package com.sda.study.springbootpractice.services.implementations;

import com.sda.study.springbootpractice.exceptions.AuthorityNotFoundException;
import com.sda.study.springbootpractice.models.Authority;
import com.sda.study.springbootpractice.repositories.AuthorityRepository;
import com.sda.study.springbootpractice.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of Authorities
 */
@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public List<Authority> findAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority findAuthorityByName(String name) throws AuthorityNotFoundException {
        Optional<Authority> optionalAuthority = authorityRepository.findByName(name);

        if (optionalAuthority.isEmpty()) {
            throw new AuthorityNotFoundException(name);
        }

        return optionalAuthority.get();
    }

    @Override
    public void createAuthority(Authority authority) {
        authority.setActive(true);
        authorityRepository.save(authority);
    }
}
