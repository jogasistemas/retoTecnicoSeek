package com.management.candidate.application.port.out;

import com.management.candidate.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    List<User> findAll();
    Optional<User> findByUsername(String username);
    User saveUser(User user);
}
