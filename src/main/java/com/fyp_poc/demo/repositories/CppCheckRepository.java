package com.fyp_poc.demo.repositories;

import com.fyp_poc.demo.DTO.CppCheck;
import com.fyp_poc.demo.controllers.cppCheck.CppCheckBuildNamesRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CppCheckRepository extends JpaRepository<CppCheck, Long> {

    @Query(value ="select * from cpp_check order by id desc limit :number ;" , nativeQuery = true)
    List<CppCheck> findLastNChecks( @Param("number") long number);

    @Query(value = " select * from cpp_check where build_name in (:names) ", nativeQuery = true)
    List<CppCheck> findChecksByBuildNames (@Param("names") List<String> names);

    @Query(value = " select build_name from cpp_check order by id desc limit :number ", nativeQuery = true)
    List<String> findLastCheckNames (@Param("number") long number);

    @Query(value = " select C.* from cpp_check C right join (select * from builds where build_name in (:listOfBuildNames) ) X on X.id = C.build_id; ", nativeQuery = true)
    List<CppCheck> findCppChecksByBuildNames(@Param("listOfBuildNames") List<String> listOfBuildNames);
}