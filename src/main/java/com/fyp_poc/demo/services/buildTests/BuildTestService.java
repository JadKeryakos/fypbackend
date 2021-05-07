package com.fyp_poc.demo.services.buildTests;

import com.fyp_poc.demo.AggObjects.CppCheckAgg;
import com.fyp_poc.demo.AggObjects.TestsAgg;
import com.fyp_poc.demo.DTO.BazelStatsVector;
import com.fyp_poc.demo.DTO.Build;
import com.fyp_poc.demo.DTO.BuildTests;
import com.fyp_poc.demo.repositories.BuildTestsRepository;
import com.fyp_poc.demo.repositories.BuildsRepository;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class BuildTestService implements IBuildTestsService {

    private final BuildTestsRepository buildTestsRepository;
    private final BuildsRepository buildsRepository;
    private final Map<String, Function<List<BuildTests>, TestsAgg>> aggregationMap;
    @Autowired
    public BuildTestService(BuildTestsRepository buildTestsRepository, BuildsRepository buildsRepository) {
        this.buildTestsRepository = buildTestsRepository;
        this.buildsRepository = buildsRepository;
        aggregationMap = new HashMap<>();
        aggregationMap.put("sum",this::sum);
        aggregationMap.put("max",this::max);
        aggregationMap.put("min",this::min);
        aggregationMap.put("avg",this::avg);
    }

    @Override
    public BuildTests findBuildTestByBuildId(long buildId) {
        return buildTestsRepository.findAll().get(0);
    }

    @Override
    public BuildTests addTestForBuildUsingBuildId(long buildId, BuildTests buildTest) {
        Build specifiedBuild = buildsRepository.findById(buildId).get();
        System.out.println("=====================================================================================");

        System.out.println(specifiedBuild);
        System.out.println("=====================================================================================");

        buildTest.setBuild(specifiedBuild);
        return buildTestsRepository.save(buildTest);
    }

    private TestsAgg sum(List<BuildTests> buildTestsList){
        return TestsAgg.testReduceList(buildTestsList,x->x.stream().mapToDouble(y->y).sum());
    }
    private TestsAgg max(List<BuildTests> buildTestsList){
        return TestsAgg.testReduceList(buildTestsList,x->x.stream().mapToDouble(y->y).max().orElse(0.0));
    }
    private TestsAgg min(List<BuildTests> buildTestsList){
        return TestsAgg.testReduceList(buildTestsList,x->x.stream().mapToDouble(y->y).min().orElse(0.0));
    }
    private TestsAgg avg(List<BuildTests> buildTestsList){
        return TestsAgg.testReduceList(buildTestsList,x->x.stream().mapToDouble(y->y).average().orElse(0.0));
    }

    @Override
    public Map<String, TestsAgg> generateTestsAgg(List<String> aggregations, long aggregationSize) {
        List<BuildTests> buildTestsList = buildTestsRepository.getLastNBuildTests(aggregationSize);
        HashMap<String, TestsAgg> res = new HashMap<>();
        for(String agg : aggregations){
            res.put(agg,aggregationMap.get(agg).apply(buildTestsList));
        }
        return res;
    }
}
