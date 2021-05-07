package com.fyp_poc.demo.services.cppCheck;

import com.fyp_poc.demo.DTO.Build;
import com.fyp_poc.demo.DTO.CppCheck;
import com.fyp_poc.demo.DTO.CppCheckAgg;
import com.fyp_poc.demo.controllers.cppCheck.CppCheckResponse;
import com.fyp_poc.demo.repositories.BuildsRepository;
import com.fyp_poc.demo.repositories.CppCheckRepository;
import com.fyp_poc.demo.utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
public class CppCheckService implements ICppCheckService {


    private final CppCheckRepository cppCheckRepository;
    private final BuildsRepository buildsRepository;
    private final Map<String, Function<Long, CppCheckAgg>> aggregatorMap;


    @Autowired
    public CppCheckService(CppCheckRepository cppCheckRepository, BuildsRepository buildsRepository, Map<String, Function<Long, CppCheckAgg>> aggregatorMap) {
        this.cppCheckRepository = cppCheckRepository;
        this.buildsRepository = buildsRepository;
        this.aggregatorMap = aggregatorMap;
        aggregatorMap.put("sum",this::getCppCheckSum);
        aggregatorMap.put("max",this::getCppCheckMax);
        aggregatorMap.put("min",this::getCppCheckMin);
        aggregatorMap.put("avg",this::getCppCheckAvg);
    }

    @Override
    public CppCheck addCheck(long buildId, CppCheck cppCheck) {
        Build specifiedBuild = buildsRepository.findById(buildId).get();
        cppCheck.setBuild(specifiedBuild);
        return cppCheckRepository.save(cppCheck);
    }

    @Override
    public List<CppCheck> findAllChecks() {
        return cppCheckRepository.findAll();
    }

    @Override
    public CppCheck findCppCheck(Long cppCheckId) throws Exception {
        Optional<CppCheck> cppCheck = cppCheckRepository.findById(cppCheckId);
        if(cppCheck.isPresent()){
            return cppCheck.get();
        }else {
            // TODO: Create Your own Exception
            throw new Exception("Cpp Check not Found");
        }

    }

    @Override
    public List<CppCheck> findLastNChecks(long n) {
        return cppCheckRepository.findLastNChecks(n);
    }

    @Override
    public List<CppCheck> findCppCheckByBuildNames(List<String> buildNames) {
        return cppCheckRepository.findChecksByBuildNames(buildNames);
    }

    @Override
    public List<String> findLastNBuildNames(long number) {
        return cppCheckRepository.findLastCheckNames(number);
    }

    private CppCheckAgg getCppCheckSum(Long aggregationSize) {
        List<CppCheck> checks = cppCheckRepository.findLastNChecks(aggregationSize);
        List<CppCheckAgg> cppCheckAggList = mapCppCheckToAgg(checks);
        return cppCheckAggList.stream().reduce(CppCheckAgg.cppCheckNil(),(x,y)->x.cppCheckReduceSingle(y,Double::sum));
    }


    private CppCheckAgg getCppCheckAvg(Long aggregationSize) {
        List<CppCheck> checks = cppCheckRepository.findLastNChecks(aggregationSize);
        List<CppCheckAgg> cppCheckAggList = mapCppCheckToAgg(checks);
        return CppCheckAgg.cppCheckReduceList(cppCheckAggList,x->x.stream().mapToDouble(y->y).average().orElse(0.0));

    }
    private CppCheckAgg getCppCheckMax(Long aggregationSize) {
        List<CppCheck> checks = cppCheckRepository.findLastNChecks(aggregationSize);
        List<CppCheckAgg> cppCheckAggList = mapCppCheckToAgg(checks);
        return CppCheckAgg.cppCheckReduceList(cppCheckAggList,x->x.stream().mapToDouble(y->y).max().orElse(0.0));
    }
    private CppCheckAgg getCppCheckMin(Long aggregationSize) {
        List<CppCheck> checks= cppCheckRepository.findLastNChecks(aggregationSize);
        List<CppCheckAgg> cppCheckAggList = mapCppCheckToAgg(checks);
        return CppCheckAgg.cppCheckReduceList(cppCheckAggList,x->x.stream().mapToDouble(y->y).min().orElse(0.0));
    }

    @Override
    public Map<String,CppCheckAgg> cppCheckAggregation(List<String> aggregations, Long aggregationSize) {
        Map<String,CppCheckAgg> map = new HashMap<>();
        for(String agg : aggregations){
            map.put(agg,this.aggregatorMap.get(agg).apply(aggregationSize));
        }
        return map;
    }

    private List<CppCheckAgg> mapCppCheckToAgg(List<CppCheck> listOfCppChecks) {
        List<CppCheckAgg> responseList = new ArrayList<>();
        for (CppCheck cppCheck : listOfCppChecks) {
            responseList.add(buildFromCppCheck(cppCheck));
        }
        return responseList;
    }

    private CppCheckAgg buildFromCppCheck(CppCheck cppCheck) {
        return  CppCheckAgg.builder()
                .error(cppCheck.getError())
                .performance(cppCheck.getPerformance())
                .portability(cppCheck.getPortability())
                .warning(cppCheck.getWarning())
                .style(cppCheck.getStyle())
                .build();
    }

}
