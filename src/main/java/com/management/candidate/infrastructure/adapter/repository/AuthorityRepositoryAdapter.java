package com.management.candidate.infrastructure.adapter.repository;

import com.management.candidate.application.port.out.AuthorityRepositoryPort;
import com.management.candidate.domain.Authority;
import com.management.candidate.infrastructure.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class AuthorityRepositoryAdapter implements AuthorityRepositoryPort {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityRepositoryAdapter(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Optional<Authority> findByName(String name) {
        return authorityRepository.findByName(name);
    }
}
