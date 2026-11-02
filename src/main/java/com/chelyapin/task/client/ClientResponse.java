package com.chelyapin.task.client;

import java.time.Instant;

public record ClientResponse(Long id, String fullName, Gender gender, ClientStatus status,
		Instant createDttm, Instant modifyDttm) {

	static ClientResponse from(ClientAccount c) {
		return new ClientResponse(c.getId(), c.getFullName(), c.getGender(), c.getStatus(),
				c.getCreateDttm(), c.getModifyDttm());
	}
}
