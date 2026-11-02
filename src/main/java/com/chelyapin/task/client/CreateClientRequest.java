package com.chelyapin.task.client;

import jakarta.validation.constraints.NotBlank;

public record CreateClientRequest(@NotBlank String fullName, Gender gender) {
}
