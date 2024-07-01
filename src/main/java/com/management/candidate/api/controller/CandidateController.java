package com.management.candidate.api.controller;

import com.management.candidate.application.service.CandidateService;
import com.management.candidate.domain.Candidate;
import com.management.candidate.infrastructure.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidate")
@Tag(name = "Candidate", description = "API de Gestion de Candidatos")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping
    @Operation(summary = "Obtener todos los candidatos", description = "Devuelve una lista de todos los candidatos")
    public ResponseEntity<List<Candidate>> getAllCandidates(@RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String jwt = token.substring(7);

        if (!jwtTokenProvider.validateToken(jwt)) {
            return ResponseEntity.status(401).build();
        }
        List<Candidate> candidates = candidateService.findAllCandidates();
        return ResponseEntity.ok(candidates);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener todos los candidatos por ID", description = "Devuelve un candidato")
    public ResponseEntity<Candidate> getCandidateById(@RequestHeader("Authorization") String token, @PathVariable Long id) {

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String jwt = token.substring(7);

        if (!jwtTokenProvider.validateToken(jwt)) {
            return ResponseEntity.status(401).build();
        }

        Optional<Candidate> candidate = candidateService.findCandidateById(id);
        return candidate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo candidato", description = "Crea un nuevo candidato y lo guarda en la base de datos")
    public ResponseEntity<?> createCandidate(@RequestHeader("Authorization") String token, @Valid @RequestBody Candidate candidate) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String jwt = token.substring(7);

        if (!jwtTokenProvider.validateToken(jwt)) {
            return ResponseEntity.status(401).build();
        }
        //Validacion si existe un usurio
        Optional<Candidate> existingCandidate = candidateService.findByEmail(candidate.getEmail());
        if (existingCandidate.isPresent()) {
            return ResponseEntity.status(409).body("Candidato ya registrado con el mismo email"); // Conflict if candidate already exists
        }

        Candidate createdCandidate = candidateService.createCandidate(candidate);
        return ResponseEntity.ok(createdCandidate);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un candidato", description = "Elimina un candidato y lo guarda en la base de datos")
    public ResponseEntity<String> deleteCandidate(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String jwt = token.substring(7);

        if (!jwtTokenProvider.validateToken(jwt)) {
            return ResponseEntity.status(401).build();
        }

        candidateService.deleteCandidateById(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}
