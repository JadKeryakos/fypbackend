package com.fyp_poc.demo.services.buildTests;

import com.fyp_poc.demo.AggObjects.TestsAgg;
import com.fyp_poc.demo.DTO.BuildTests;
import com.fyp_poc.demo.controllers.buildTests.NamesListRequest;

import java.util.List;
import java.util.Map;

public interface IBuildTestsService {

    BuildTests findBuildTestByBuildId(long buildId);
    BuildTests addTestForBuildUsingBuildId(long buildId, BuildTests buildTests);
    Map<String,TestsAgg> generateTestsAgg(List<String> aggregations,long aggregationSize);
    List<BuildTests> findLastNTests(long n);
    List<BuildTests> findAllTests();

    List<BuildTests> findNamesIn(List<String> names);
}
