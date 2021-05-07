package com.fyp_poc.demo.services.cppCheck;

import com.fyp_poc.demo.DTO.CppCheck;

import java.util.List;
import java.util.Map;

public interface ICppCheckService {


    CppCheck addCheck(long buildId, CppCheck cppCheck);
    List<CppCheck> findAllChecks();
    CppCheck findCppCheck(Long cppCheckId) throws Exception;
    Map<String,CppCheck> cppCheckAggregation(List<String> aggregations, Long aggregationSize);
    List<CppCheck> findLastNChecks(long n);
    List<CppCheck> findCppCheckByBuildNames(List<String> buildNames);
    List<String> findLastNBuildNames(long number);
}
