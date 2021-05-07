package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.Build;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuildsRepository   extends JpaRepository<Build, Long> {

    @Query(value ="select build_name from builds order by id desc limit :numberOfRows"
            , nativeQuery = true)
    List<String> findLastNBazelBuildNames(@Param("numberOfRows") long numberOfRows);
}
