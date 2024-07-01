package com.management.candidate.infrastructure.adapter.repository;

import com.management.candidate.application.port.out.CandidateRepositoryPort;
import com.management.candidate.domain.Candidate;
import com.management.candidate.infrastructure.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CandidateRepositoryAdapter implements CandidateRepositoryPort {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateRepositoryAdapter(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidate> findById(Long id) {
        return candidateRepository.findById(id);
    }

    @Override
    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public void deleteById(Long id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public Optional<Candidate> findByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }
}
