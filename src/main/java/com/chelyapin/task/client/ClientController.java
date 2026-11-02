package com.chelyapin.task.client;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	private final ClientService service;

	public ClientController(ClientService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientResponse create(@Valid @RequestBody CreateClientRequest request) {
		return service.create(request);
	}

	@GetMapping
	public List<ClientResponse> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ClientResponse findById(@PathVariable long id) {
		return service.findById(id);
	}

	@PutMapping("/{id}")
	public ClientResponse update(@PathVariable long id, @Valid @RequestBody UpdateClientRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id) {
		service.delete(id);
	}
}
