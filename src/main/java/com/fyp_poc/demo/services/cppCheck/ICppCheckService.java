package com.fyp_poc.demo.services.cppCheck;

import com.fyp_poc.demo.DTO.CppCheck;

import java.util.List;
import java.util.Map;

public interface ICppCheckService {


    CppCheck addCheck(CppCheck cppCheck);
    List<CppCheck> findAllChecks();
    CppCheck findCppCheck(Long cppCheckId) throws Exception;
    Map<String,CppCheck> cppCheckAggregation(List<String> aggregations, Long aggregationSize);

}
