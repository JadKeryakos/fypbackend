package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.BuildTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface BuildTestsRepository extends JpaRepository<BuildTests, Long> {

    @Query(value="select * from build_tests order by id desc limit :number ;", nativeQuery = true)
    List<BuildTests> getLastNBuildTests(@Param("number") long number);


    @Query(value = "select T.* from build_tests T right join (select * from builds where build_name in (:namesList ) ) X on X.id = T.build_id; " , nativeQuery = true)
    List<BuildTests> getBuildTestsFromNames(@Param("namesList") List<String> namesList);

    @Modifying
    @Transactional
    @Query(value="delete from build_tests where build_id = :id ", nativeQuery = true)
    void removeBuildTestsForBuild(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value="delete from build_tests" , nativeQuery = true)
    void removeAll();
}
