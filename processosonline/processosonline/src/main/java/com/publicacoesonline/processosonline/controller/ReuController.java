package com.publicacoesonline.processosonline.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publicacoesonline.processosonline.dto.ReuRequest;
import com.publicacoesonline.processosonline.entity.Reu;
import com.publicacoesonline.processosonline.service.ReuService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reus")
@RequiredArgsConstructor
public class ReuController {

    private final ReuService reuService;

    @PostMapping
    public Reu adicionar(@RequestBody @Valid ReuRequest dto) {
        return reuService.adicionarReu(dto);
    }

}
