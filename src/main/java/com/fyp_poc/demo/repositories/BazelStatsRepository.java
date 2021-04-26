package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.BazelStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BazelStatsRepository extends JpaRepository<BazelStats, UUID> {
}