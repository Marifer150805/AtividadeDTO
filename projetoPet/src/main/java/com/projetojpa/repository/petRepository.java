package com.projetojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetojpa.entities.pet;

public interface petRepository extends JpaRepository<pet, Long> {

}
