package com.fyp_poc.demo.services.buildTests;

import com.fyp_poc.demo.DTO.Build;
import com.fyp_poc.demo.DTO.BuildTests;

import java.util.List;

public interface IBuildTestsService {

    BuildTests findBuildTestByBuildId(long buildId);
}
