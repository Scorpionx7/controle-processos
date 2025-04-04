package com.publicacoesonline.processosonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.publicacoesonline.processosonline.entity.Reu;

@Repository
public interface ReuRepository extends JpaRepository<Reu, Long> {
    

}
