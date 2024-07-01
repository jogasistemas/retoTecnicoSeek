package com.management.candidate;

import com.management.candidate.application.service.CandidateService;
import com.management.candidate.domain.Candidate;
import com.management.candidate.infrastructure.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CandidateApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CandidateService candidateService;

	@MockBean
	private JwtTokenProvider jwtTokenProvider;

	@Test
	public void testGetAllCandidates() throws Exception {
		Mockito.when(jwtTokenProvider.validateToken(anyString())).thenReturn(true);
		Mockito.when(candidateService.findAllCandidates()).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/api/candidates")
						.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcnVlYmEiLCJpYXQiOjE3MTk3ODk0MDIsImV4cCI6MTcxOTc4OTcwMn0.te9LGkSPA-Lxh--L0_T2l9ILrxGJAg_o661azn4JI9FulOMwH4rn_rCRJt0A5TmfoBlYO2mBmGRqZ-d7j17inA"))
				.andExpect(status().isForbidden());
	}

	@Test
	public void testCreateCandidate() throws Exception {
		Mockito.when(jwtTokenProvider.validateToken(anyString())).thenReturn(true);
		Candidate candidate = new Candidate();
		candidate.setId(1L);
		candidate.setFullname("John Doe");
		candidate.setEmail("john.doe@example.com");
		Mockito.when(candidateService.createCandidate(any(Candidate.class))).thenReturn(candidate);
		Mockito.when(candidateService.findByEmail(anyString())).thenReturn(Optional.empty());

		String candidateJson = "{\"fullname\": \"John Doe\", \"email\": \"john.doe@example.com\"}";

		mockMvc.perform(post("/api/candidates")
						.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcnVlYmEiLCJpYXQiOjE3MTk3ODc1OTksImV4cCI6MTcxOTc4Nzg5OX0.KFmzFxrkSAYvt_T-mAukEQG6cMU5jPBopzX3lP7fLFVTZ4diytRJpGUg5p-8qk-8eJGq36KnxvoG2w7eAgd6HA")
						.contentType(MediaType.APPLICATION_JSON)
						.content(candidateJson))
				.andExpect(status().isForbidden());
	}


	@Test
	public void testDeleteCandidate() throws Exception {
		Mockito.when(jwtTokenProvider.validateToken(anyString())).thenReturn(true);
		Mockito.doNothing().when(candidateService).deleteCandidateById(1L);

		mockMvc.perform(delete("/api/candidates/1")
						.header("Authorization", "Bearer valid-token"))
				.andExpect(status().isForbidden());
	}

}
