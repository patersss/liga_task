package com.chelyapin.task.client;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {

	private final ClientRepository repository;

	public ClientService(ClientRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public ClientResponse create(CreateClientRequest request) {
		ClientAccount client = new ClientAccount();
		client.setFullName(request.fullName());
		client.setGender(request.gender());
		return ClientResponse.from(repository.saveAndFlush(client));
	}

	@Transactional(readOnly = true)
	public List<ClientResponse> findAll() {
		return repository.findAll(Sort.by("id")).stream().map(ClientResponse::from).toList();
	}

	@Transactional(readOnly = true)
	public ClientResponse findById(long id) {
		return ClientResponse.from(get(id));
	}

	@Transactional
	public ClientResponse update(long id, UpdateClientRequest request) {
		ClientAccount client = get(id);
		if (request.fullName() != null) client.setFullName(request.fullName());
		if (request.gender() != null) client.setGender(request.gender());
		if (request.status() != null) client.setStatus(request.status());
		return ClientResponse.from(repository.saveAndFlush(client));
	}

	@Transactional
	public void delete(long id) {
		repository.delete(get(id));
	}

	private ClientAccount get(long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client " + id + " not found"));
	}
}
