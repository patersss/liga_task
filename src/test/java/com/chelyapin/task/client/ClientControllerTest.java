package com.chelyapin.task.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

	@Autowired
	MockMvc mvc;

	@MockitoBean
	ClientService service;

	@Test
	void createReturns201() throws Exception {
		Instant now = Instant.now();
		given(service.create(any())).willReturn(
				new ClientResponse(1L, "Иванов Иван", Gender.M, ClientStatus.ACTIVE, now, now));

		mvc.perform(post("/api/clients").contentType(MediaType.APPLICATION_JSON)
						.content("{\"fullName\":\"Иванов Иван\",\"gender\":\"M\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.status").value("ACTIVE"));
	}

	@Test
	void createWithBlankNameReturns400() throws Exception {
		mvc.perform(post("/api/clients").contentType(MediaType.APPLICATION_JSON)
						.content("{\"fullName\":\" \",\"gender\":\"M\"}"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void createWithUnknownGenderReturns400() throws Exception {
		mvc.perform(post("/api/clients").contentType(MediaType.APPLICATION_JSON)
						.content("{\"fullName\":\"Иванов\",\"gender\":\"X\"}"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void missingClientReturns404() throws Exception {
		ResponseStatusException notFound = new ResponseStatusException(HttpStatus.NOT_FOUND);
		given(service.findById(42)).willThrow(notFound);
		given(service.update(eq(42L), any())).willThrow(notFound);
		willThrow(notFound).given(service).delete(42);

		mvc.perform(get("/api/clients/42")).andExpect(status().isNotFound());
		mvc.perform(put("/api/clients/42").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isNotFound());
		mvc.perform(delete("/api/clients/42")).andExpect(status().isNotFound());
	}
}
