package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.BazelStatsVector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public interface BazelStatsRepository extends JpaRepository<BazelStats, UUID> {



    @Query(value ="select * from bazel_stats BS right join (select * from builds where build_name in (:listOfBuildNames)) X on X.id = BS.build_id ;"
            , nativeQuery = true)
    List<BazelStats> findBazelStatsByBuildNames(@Param("listOfBuildNames") List<String> listOfBuildNames);

/*
   BazelStats findByBuildName (String buildName);

   @Query(value ="select build_name from bazel_stats order by id desc limit :numberOfRows"
           , nativeQuery = true)
   List<String> findLastNBazelBuildNames(@Param("numberOfRows") long numberOfRows);
 */
}


