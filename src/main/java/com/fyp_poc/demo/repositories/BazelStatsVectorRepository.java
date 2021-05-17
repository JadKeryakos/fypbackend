package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.BazelStatsVector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface BazelStatsVectorRepository extends JpaRepository<BazelStatsVector, UUID> {

    @Query(value ="select * from bazel_stats_vector BSV right join (select * from bazel_stats order by id desc limit :number ) X on X.id = BSV.bazel_stats_id; "
            , nativeQuery = true)
    List<BazelStatsVector> findTheLatestNBazelStats(@Param("number") long number);


    @Query(value =" select * from bazel_stats_vector BSV right join bazel_stats BS on BSV.bazel_stats_id = bs.id right join ( select * from builds where builds.build_name in (:listOfBuildNames) ) X on X.id = BS.build_id ;"
            , nativeQuery = true)
    List<BazelStatsVector> findBazelStatsByBuildNames(@Param("listOfBuildNames") List<String> listOfBuildNames);

    @Modifying
    @Transactional
    @Query(value="delete from bazel_stats_vector where bazel_stats_id = :id ;" , nativeQuery = true)
    void removeVectorsForBuild(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "delete from bazel_stats_vector" , nativeQuery = true)
    void removeAll();

}
