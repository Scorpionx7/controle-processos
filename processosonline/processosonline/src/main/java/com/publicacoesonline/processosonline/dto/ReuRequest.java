package com.publicacoesonline.processosonline.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReuRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Long processoId;

}
