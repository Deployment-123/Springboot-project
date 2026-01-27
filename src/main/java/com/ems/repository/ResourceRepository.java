package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
