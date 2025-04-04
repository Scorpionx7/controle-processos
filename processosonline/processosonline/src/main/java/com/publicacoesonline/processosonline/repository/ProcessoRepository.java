package com.publicacoesonline.processosonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.publicacoesonline.processosonline.entity.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long>{
    
    boolean existsByNumeroProcesso(String numeroProcesso);
    

}
