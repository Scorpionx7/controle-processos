package com.publicacoesonline.processosonline.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ProcessoRequest {

    @NotBlank
    private String numeroProcesso;

    private String descricao;

    @NotNull
    private LocalDate dataAbertura;



}
