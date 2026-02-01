package com.senai.backend.controllers.dtos;

import jakarta.validation.constraints.NotBlank;

public record SetorDtoUpdate(@NotBlank String nome) {
}
