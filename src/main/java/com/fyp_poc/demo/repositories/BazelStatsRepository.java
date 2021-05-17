package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.BazelStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BazelStatsRepository extends JpaRepository<BazelStats, Long> {



    @Query(value ="select * from bazel_stats BS right join (select * from builds where build_name in (:listOfBuildNames)) X on X.id = BS.build_id ;"
            , nativeQuery = true)
    List<BazelStats> findBazelStatsByBuildNames(@Param("listOfBuildNames") List<String> listOfBuildNames);

    @Query(value = "select * from bazel_stats order by build_id desc limit :number ;", nativeQuery = true)
    List<BazelStats> lastNBazelStats(@Param("number") long number);


    @Query(value="select * from bazel_stats where build_id = :id" , nativeQuery = true)
    BazelStats findBazelStatsByBuildId(long id);

    @Modifying
    @Transactional
    @Query(value = "delete from bazel_stats where build_id = :id ", nativeQuery = true)
    void removeBazelStatsForBuild(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "delete from bazel_stats" , nativeQuery = true)
    void removeAll();
/*
   BazelStats findByBuildName (String buildName);

   @Query(value ="select build_name from bazel_stats order by id desc limit :numberOfRows"
           , nativeQuery = true)
   List<String> findLastNBazelBuildNames(@Param("numberOfRows") long numberOfRows);
 */
}


