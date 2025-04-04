package com.publicacoesonline.processosonline.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "processos", uniqueConstraints = @UniqueConstraint(columnNames = "numero_processo"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_processo", nullable = false, unique = true)
    private String numeroProcesso;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_abertura", nullable = false)
    private LocalDate dataAbertura;



}
