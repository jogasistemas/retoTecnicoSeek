package com.management.candidate.application.service;

import com.management.candidate.application.port.in.CandidateUseCase;
import com.management.candidate.application.port.out.CandidateRepositoryPort;
import com.management.candidate.domain.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService implements CandidateUseCase {

    private final CandidateRepositoryPort candidateRepositoryPort;

    @Autowired
    public CandidateService(CandidateRepositoryPort candidateRepositoryPort) {
        this.candidateRepositoryPort = candidateRepositoryPort;
    }

    @Override
    public List<Candidate> findAllCandidates() {
        return candidateRepositoryPort.findAll();
    }

    @Override
    public Optional<Candidate> findCandidateById(Long id) {
        return candidateRepositoryPort.findById(id);
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        return candidateRepositoryPort.save(candidate);
    }

    @Override
    public void deleteCandidateById(Long id) {
        candidateRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<Candidate> findByEmail(String email) {
        return candidateRepositoryPort.findByEmail(email);
    }
}
