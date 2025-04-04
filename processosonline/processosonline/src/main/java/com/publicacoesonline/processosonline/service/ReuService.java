package com.publicacoesonline.processosonline.service;

import org.springframework.stereotype.Service;

import com.publicacoesonline.processosonline.dto.ReuRequest;
import com.publicacoesonline.processosonline.entity.Processo;
import com.publicacoesonline.processosonline.entity.Reu;
import com.publicacoesonline.processosonline.exception.RecursoNaoEncontradoException;
import com.publicacoesonline.processosonline.repository.ProcessoRepository;
import com.publicacoesonline.processosonline.repository.ReuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReuService {

    private final ReuRepository reuRepository;
    private final ProcessoRepository processoRepository;

    public Reu adicionarReu(ReuRequest dto) {
        Processo processo = processoRepository.findById(dto.getProcessoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Processo não encontrado"));

        Reu reu = new Reu();
        reu.setNome(dto.getNome());
        reu.setProcesso(processo);

        return reuRepository.save(reu);
    }

}
