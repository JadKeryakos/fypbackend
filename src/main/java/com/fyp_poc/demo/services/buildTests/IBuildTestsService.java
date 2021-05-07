package com.fyp_poc.demo.services.buildTests;

import com.fyp_poc.demo.AggObjects.TestsAgg;
import com.fyp_poc.demo.DTO.Build;
import com.fyp_poc.demo.DTO.BuildTests;

import java.util.List;
import java.util.Map;

public interface IBuildTestsService {

    BuildTests findBuildTestByBuildId(long buildId);
    BuildTests addTestForBuildUsingBuildId(long buildId, BuildTests buildTests);
    Map<String,TestsAgg> generateTestsAgg(List<String> aggregations,long aggregationSize);
}
