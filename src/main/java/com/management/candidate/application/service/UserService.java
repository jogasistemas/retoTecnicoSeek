package com.management.candidate.application.service;

import com.management.candidate.application.port.in.UserUseCase;
import com.management.candidate.application.port.out.UserRepositoryPort;
import com.management.candidate.domain.Authority;
import com.management.candidate.domain.RegisterRequest;
import com.management.candidate.domain.User;
import com.management.candidate.infrastructure.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public UserService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder,AuthorityRepository authorityRepository) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository=authorityRepository;
    }

    @Override
    public List<User> findAllUser() {
        return userRepositoryPort.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepositoryPort.findByUsername(username);
    }

    @Override
    public User saveUser(RegisterRequest user) {
        User usuario = new User();
        Set<Authority> authorities = new HashSet<>();

        Authority authority = authorityRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        authorities.add(authority);

        usuario.setUsername(user.getUsername());
        usuario.setPassword(passwordEncoder.encode(user.getPassword()));
        usuario.setEnabled(true);
        usuario.setAuthorities(authorities);
        return userRepositoryPort.saveUser(usuario);
    }
}
