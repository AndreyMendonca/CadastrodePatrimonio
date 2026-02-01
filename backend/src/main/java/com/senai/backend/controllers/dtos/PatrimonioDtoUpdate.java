package com.senai.backend.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatrimonioDtoUpdate(
        @NotBlank String nome,
        @NotBlank String numeroPatrimonio,
        @NotNull Long setor
){
}
