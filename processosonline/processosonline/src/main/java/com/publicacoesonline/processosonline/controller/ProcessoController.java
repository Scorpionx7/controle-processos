package com.publicacoesonline.processosonline.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publicacoesonline.processosonline.dto.ProcessoRequest;
import com.publicacoesonline.processosonline.entity.Processo;
import com.publicacoesonline.processosonline.service.ProcessoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/processos")
@RequiredArgsConstructor
public class ProcessoController {

    private final ProcessoService processoService;

    @PostMapping
    public Processo criar(@RequestBody @Valid ProcessoRequest dto) {
        return processoService.criarProcesso(dto);
    }

    @GetMapping
    public List<Processo> listar() {
        return processoService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        processoService.excluirPorId(id);
    }

}
