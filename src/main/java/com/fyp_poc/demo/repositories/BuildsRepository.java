package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.Build;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildsRepository   extends JpaRepository<Build, Long> {
}
