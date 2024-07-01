package com.management.candidate.application.port.out;

import com.management.candidate.domain.Candidate;
import java.util.List;
import java.util.Optional;

public interface CandidateRepositoryPort {

    List<Candidate> findAll();

    Optional<Candidate> findById(Long id);

    Candidate save(Candidate candidate);

    void deleteById(Long id);

    Optional<Candidate> findByEmail(String email);
}
