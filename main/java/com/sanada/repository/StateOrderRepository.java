package com.sanada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanada.entity.StateOrder;

public interface StateOrderRepository extends JpaRepository<StateOrder, Integer> {

	StateOrder findByCod(String cod);
}
