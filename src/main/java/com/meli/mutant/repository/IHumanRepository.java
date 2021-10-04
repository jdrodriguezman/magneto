package com.meli.mutant.repository;

import com.meli.mutant.model.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHumanRepository extends JpaRepository<Human, Integer> {
}
