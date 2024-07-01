package com.management.candidate.application.port.in;

import com.management.candidate.domain.Candidate;
import java.util.List;
import java.util.Optional;

public interface  CandidateUseCase {

    List<Candidate> findAllCandidates();

    Optional<Candidate> findCandidateById(Long id);

    Candidate createCandidate(Candidate candidate);

    void deleteCandidateById(Long id);

    Optional<Candidate> findByEmail(String email);
}
