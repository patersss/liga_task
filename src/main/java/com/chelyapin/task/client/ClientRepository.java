package com.chelyapin.task.client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientAccount, Long> {
}
