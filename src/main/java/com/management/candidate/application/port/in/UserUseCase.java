package com.management.candidate.application.port.in;

import com.management.candidate.domain.Candidate;
import com.management.candidate.domain.RegisterRequest;
import com.management.candidate.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserUseCase {

    List<User> findAllUser();
    Optional<User> findByUsername(String username);
    User saveUser(RegisterRequest registerRequest);
}
