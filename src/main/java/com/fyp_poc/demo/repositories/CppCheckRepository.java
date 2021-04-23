package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.CppCheckTest;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CppCheckRepository extends CrudRepository<CppCheckTest, UUID> {
}