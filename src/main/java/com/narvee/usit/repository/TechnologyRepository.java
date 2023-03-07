package com.narvee.usit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.narvee.usit.entity.Technologies;

public interface TechnologyRepository extends JpaRepository<Technologies, Serializable>{

}
