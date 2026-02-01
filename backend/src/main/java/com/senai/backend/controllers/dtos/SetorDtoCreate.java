package com.senai.backend.controllers.dtos;

import jakarta.validation.constraints.NotBlank;

public record SetorDtoCreate(@NotBlank String nome) {
}
