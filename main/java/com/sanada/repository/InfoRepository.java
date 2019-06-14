package com.sanada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanada.entity.InformazioniUtente;

public interface InfoRepository extends JpaRepository<InformazioniUtente, Integer> {

}
