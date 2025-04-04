package com.publicacoesonline.processosonline.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.publicacoesonline.processosonline.dto.ProcessoRequest;
import com.publicacoesonline.processosonline.entity.Processo;
import com.publicacoesonline.processosonline.exception.RecursoDuplicadoException;
import com.publicacoesonline.processosonline.exception.RecursoNaoEncontradoException;
import com.publicacoesonline.processosonline.repository.ProcessoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessoService {

     private final ProcessoRepository processoRepository;

    public Processo criarProcesso(ProcessoRequest dto) {
        if (processoRepository.existsByNumeroProcesso(dto.getNumeroProcesso())) {
            throw new RecursoDuplicadoException("Número de processo já cadastrado");
        }

        Processo processo = new Processo();
        processo.setNumeroProcesso(dto.getNumeroProcesso());
        processo.setDescricao(dto.getDescricao());
        processo.setDataAbertura(dto.getDataAbertura());

        return processoRepository.save(processo);
    }

    public List<Processo> listarTodos() {
        return processoRepository.findAll();
    }

    public void excluirPorId(Long id) {
        if (!processoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Processo não encontrado");
        }
        processoRepository.deleteById(id);
    }



}
