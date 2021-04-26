package com.fyp_poc.demo.services.cppCheck;

import com.fyp_poc.demo.DTO.CppCheck;
import com.fyp_poc.demo.DTO.CppCheckAggregation;
import com.fyp_poc.demo.controllers.cppCheck.CppCheckResponse;
import com.fyp_poc.demo.controllers.functional.CppCheckAggregator;
import com.fyp_poc.demo.repositories.CppCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CppCheckService implements ICppCheckService {


    private CppCheckRepository cppCheckRepository;

    @Autowired
    public CppCheckService(CppCheckRepository cppCheckRepository) {
        this.cppCheckRepository = cppCheckRepository;
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
    public CppCheck findCppCheck(UUID cppCheckId) throws Exception {
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
    public List<CppCheck> getCppCheckStats(List<String> aggregations) {
        return null;
    }

    private CppCheckAggregation cppCheckAggregationSum(long size){
        List<CppCheck> data = findLastNChecks(10);
        Long error = data.stream().map(CppCheck::getError).reduce(0L, Long::sum);
        Long performance = data.stream().map(CppCheck::getPerformance).reduce(0L, Long::sum);
        Long style = data.stream().map(CppCheck::getStyle).reduce(0L, Long::sum);
        Long warning = data.stream().map(CppCheck::getWarning).reduce(0L, Long::sum);
        Long portability = data.stream().map(CppCheck::getPortability).reduce(0L, Long::sum);
        return new CppCheckAggregation().builder()
                .error(error)
                .performance(performance)
                .style(style)
                .warning(warning)
                .portability(portability)
                .build();
    }
}
