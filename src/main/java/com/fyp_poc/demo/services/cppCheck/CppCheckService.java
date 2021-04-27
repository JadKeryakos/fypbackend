package com.fyp_poc.demo.services.cppCheck;

import com.fyp_poc.demo.DTO.CppCheck;
import com.fyp_poc.demo.repositories.CppCheckRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CppCheckService implements ICppCheckService {


    private final CppCheckRepository cppCheckRepository;
    private final Map<String, Function<Long,CppCheck>> aggregatorMap;
    public CppCheckService(CppCheckRepository cppCheckRepository, Map<String, Function<Long, CppCheck>> aggregatorMap) {
        this.cppCheckRepository = cppCheckRepository;
        this.aggregatorMap = aggregatorMap;
        aggregatorMap.put("sum",this::getCppCheckSum);
        aggregatorMap.put("max",this::getCppCheckMax);
        aggregatorMap.put("min",this::getCppCheckMin);
        aggregatorMap.put("avg",this::getCppCheckAvg);
    }

    @Override
    public CppCheck addCheck(CppCheck cppCheck) {
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

    private CppCheck getCppCheckSum(Long aggregationSize) {
        List<CppCheck> checks = cppCheckRepository.findLastNChecks(aggregationSize);
        return checks.stream().reduce(CppCheck.cppCheckNil(),(x,y)->x.cppCheckReduceSingle(y,Double::sum));
    }
    private CppCheck getCppCheckAvg(Long aggregationSize) {
        List<CppCheck> checks = cppCheckRepository.findLastNChecks(aggregationSize);
        return CppCheck.cppCheckReduceList(checks,x->x.stream().mapToDouble(y->y).average().orElse(0.0));

    }
    private CppCheck getCppCheckMax(Long aggregationSize) {
        List<CppCheck> checks = cppCheckRepository.findLastNChecks(aggregationSize);
        return CppCheck.cppCheckReduceList(checks,x->x.stream().mapToDouble(y->y).max().orElse(0.0));
    }
    private CppCheck getCppCheckMin(Long aggregationSize) {
        List<CppCheck> checks= cppCheckRepository.findLastNChecks(aggregationSize);
        return CppCheck.cppCheckReduceList(checks,x->x.stream().mapToDouble(y->y).min().orElse(0.0));
    }

    @Override
    public Map<String,CppCheck> cppCheckAggregation(List<String> aggregations, Long aggregationSize) {
        Map<String,CppCheck> map = new HashMap<>();
        for(String agg : aggregations){
            map.put(agg,this.aggregatorMap.get(agg).apply(aggregationSize));
        }
        return map;
    }
}
