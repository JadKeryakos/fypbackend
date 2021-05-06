package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.BuildTests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BuildTestsRepository extends JpaRepository<BuildTests, Long> {


}
