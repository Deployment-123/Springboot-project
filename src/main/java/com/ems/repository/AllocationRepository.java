package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.entity.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation,Long> {

}
