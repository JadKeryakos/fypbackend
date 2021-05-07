package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.BazelStatsVector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface BazelStatsVectorRepository extends JpaRepository<BazelStatsVector, UUID> {

    @Query(value ="select name, percentage, time, BSV.id as id from bazel_stats_vector BSV " +
            "join (Select id from bazel_stats order by id desc limit :number) BS on BSV.build_id= BS.id ;"
            , nativeQuery = true)
    List<BazelStatsVector> findTheLatestNBazelStats(@Param("number") long number);


    @Query(value =" select * from bazel_stats_vector BSV right join bazel_stats BS on BSV.bazel_stats_id = bs.id right join ( select * from builds where builds.build_name in (:listOfBuildNames) ) X on X.id = BS.build_id ;"
            , nativeQuery = true)
    List<BazelStatsVector> findBazelStatsByBuildNames(@Param("listOfBuildNames") List<String> listOfBuildNames);
}
