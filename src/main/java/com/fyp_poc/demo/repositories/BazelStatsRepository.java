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



    @Query(value =" select build_id , BS.id as id, BSV.id, name, percentage, time  from bazel_stats_vector BSV right join bazel_stats BS on BSV.bazel_stats_id = bs.id right join ( select * from builds where builds.build_name in ('build01','build02')) X on X.id = BS.build_id ;"
            , nativeQuery = true)
    List<BazelStats> findBazelStatsByBuildNames(@Param("listOfBuildNames") List<String> listOfBuildNames);

/*
   BazelStats findByBuildName (String buildName);

   @Query(value ="select build_name from bazel_stats order by id desc limit :numberOfRows"
           , nativeQuery = true)
   List<String> findLastNBazelBuildNames(@Param("numberOfRows") long numberOfRows);
 */
}


