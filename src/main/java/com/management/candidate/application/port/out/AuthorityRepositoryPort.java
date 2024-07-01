package com.management.candidate.application.port.out;

import com.management.candidate.domain.Authority;
import java.util.Optional;

public interface AuthorityRepositoryPort {

    Optional<Authority> findByName(String name);
}
