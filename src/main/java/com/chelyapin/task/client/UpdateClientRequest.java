package com.chelyapin.task.client;

import jakarta.validation.constraints.Pattern;

public record UpdateClientRequest(
		@Pattern(regexp = ".*\\S.*", message = "must not be blank") String fullName,
		Gender gender,
		ClientStatus status) {
}
