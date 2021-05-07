package com.fyp_poc.demo.AggObjects;

import com.fyp_poc.demo.DTO.BuildTests;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class TestsAgg {
    private double testFailed;
    private double testSucceeded;

    public TestsAgg(double testFailed , double testSucceeded){
        this.testSucceeded=testSucceeded;
        this.testFailed=testFailed;
    }
    public static TestsAgg testReduceList(List<BuildTests> buildTests, Function<List<Long>,Double> lambda){
        return new TestsAgg(
                lambda.apply(buildTests.stream().map(BuildTests::getTestFailed).collect(Collectors.toList())),
                lambda.apply(buildTests.stream().map(BuildTests::getTestPassed).collect(Collectors.toList())));
    }


}


