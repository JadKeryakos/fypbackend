package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.CppCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface CppCheckRepository extends JpaRepository<CppCheck, Long> {

    @Query(value ="select * from cpp_check order by id desc limit :number ;" , nativeQuery = true)
    List<CppCheck> findLastNChecks( @Param("number") long number);
}