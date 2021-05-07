package com.fyp_poc.demo.controllers.buildTests;

import com.fyp_poc.demo.AggObjects.TestsAgg;
import com.fyp_poc.demo.DTO.BuildTests;
import com.fyp_poc.demo.DTO.CppCheck;
import com.fyp_poc.demo.controllers.cppCheck.CppCheckAggregationRequest;
import com.fyp_poc.demo.controllers.cppCheck.CppCheckResponse;
import com.fyp_poc.demo.services.buildTests.IBuildTestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController

public class BuildTestsController {

    private final IBuildTestsService buildTestsService;

    @Autowired
    public BuildTestsController(IBuildTestsService buildTestsService) {
        this.buildTestsService = buildTestsService;
    }


    @GetMapping("/tests/{testId}")
    public ResponseEntity findTestById(@PathVariable("buildId") long buildId) {
        try {
            BuildTests buildTest = buildTestsService.findBuildTestByBuildId(buildId);
            BuildTestApiResponse response = buildResponseFromBuildTest(buildTest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/tests")
    public ResponseEntity findAllTests() {
        try {
            List<BuildTests> buildTest = buildTestsService.findAllTests();
            List<BuildTestApiResponse> response = buildListOFBuildTestsResponseFrom(buildTest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/builds/{buildId}/test")
    public ResponseEntity addTestForBuildUsingBuildId(@PathVariable("buildId") long buildId, @RequestBody BuildTestApiRequest request) {
        try {
            System.out.println(buildId);
            System.out.println(request.getTestFailed());
            System.out.println(request.getTestPassed());
            System.out.println("------------------------------------------------------------------------------");
            BuildTests buildTest = buildTestsService.addTestForBuildUsingBuildId(buildId, buildBuildTestsFrom(request));
            BuildTestApiResponse response = buildResponseFromBuildTest(buildTest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private BuildTests buildBuildTestsFrom(BuildTestApiRequest request) {
        return BuildTests.builder()
                .testFailed(request.getTestFailed())
                .testPassed(request.getTestPassed())
                .build();
    }


    @GetMapping("/builds/{buildId}/test")
    public ResponseEntity findBuildTestsByBuildId(@PathVariable("buildId") long buildId) {
        try {
            BuildTests buildTest = buildTestsService.findBuildTestByBuildId(buildId);
            BuildTestApiResponse response = buildResponseFromBuildTest(buildTest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("builds/test/agg")
    public ResponseEntity findBuildTestAggregations(@RequestBody CppCheckAggregationRequest request) {
        try {
            Map<String, TestsAgg> res = buildTestsService.generateTestsAgg(request.getAggregations(),request.getAggregationSize());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("build/tests/last/{n}")
    public ResponseEntity<?> findLastNBuildTests(@PathVariable("n") long n){
        try {
            List<BuildTests> buildTest = buildTestsService.findLastNTests(n);
            Collections.reverse(buildTest);
            List<BuildTestApiResponse> response = buildListOFBuildTestsResponseFrom(buildTest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("build/tests/names/")
    public ResponseEntity<?> findTestBuildsNamesIn(@RequestBody NamesListRequest names){
        try {
            List<BuildTests> buildTest = buildTestsService.findNamesIn(names.getNames());
            Collections.reverse(buildTest);
            List<BuildTestApiResponse> response = buildListOFBuildTestsResponseFrom(buildTest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private List<BuildTestApiResponse> buildListOFBuildTestsResponseFrom(List<BuildTests> buildTests) {
        List<BuildTestApiResponse> responseList = new ArrayList<>();
        for (BuildTests entry : buildTests) {
            responseList.add(buildResponseFromBuildTest(entry));
        }
        return responseList;
    }

    private BuildTestApiResponse buildResponseFromBuildTest(BuildTests buildTest) {
        return BuildTestApiResponse.builder()
                .id(buildTest.getId())
                .testFailed(buildTest.getTestFailed())
                .testPassed(buildTest.getTestPassed())
                .build(buildTest.getBuild())
                .build();
    }

}
