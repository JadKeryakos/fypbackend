package com.fyp_poc.demo.controllers.cppCheck;

import com.fyp_poc.demo.AggObjects.CppCheckAgg;
import com.fyp_poc.demo.DTO.CppCheck;
import com.fyp_poc.demo.services.cppCheck.CppCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class CppCheckController {

     private final CppCheckService cppCheckService;

     @Autowired
    public CppCheckController(CppCheckService cppCheckService) {
        this.cppCheckService = cppCheckService;
    }


    @PostMapping("/builds/{buildId}/cppChecks")
    public ResponseEntity<?> addCheck(@RequestBody CppCheckPostRequest request, @PathVariable long buildId){
         try{
             CppCheck cppCheck = cppCheckService.addCheck(buildId,buildFromRequest(request));
             CppCheckResponse response = buildFromCppCheck(cppCheck);
             return ResponseEntity.status(HttpStatus.OK).body(response);
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
         }
    }

    @PostMapping("/builds/cppCheck-agg")
    public Map<String, CppCheckAgg> cppCheckAggregations (@RequestBody CppCheckAggregationRequest request){
         return cppCheckService.cppCheckAggregation(request.getAggregations(), request.getAggregationSize());
    }

    @GetMapping("/cppChecks/{cppCheckId}")
    public ResponseEntity<?> findCheckById(@PathVariable Long cppCheckId){
        try{
            CppCheck cppCheck = cppCheckService.findCppCheck(cppCheckId);
            CppCheckResponse response = buildFromCppCheck(cppCheck);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/cppChecks")
    public ResponseEntity<?> findAllChecksForAllBuilds(){
        try{
            List<CppCheck> cppCheck = cppCheckService.findAllChecks();
            List<CppCheckResponse> responseList = buildListFromCppCheck(cppCheck);
            return ResponseEntity.status(HttpStatus.OK).body(responseList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/cppChecks/last/{n}")
    public ResponseEntity<?> findLastNCppChecks(@PathVariable("n") long n){
         try{
            List<CppCheck> cppChecks = cppCheckService.findLastNChecks(n);
            Collections.reverse(cppChecks);
            return ResponseEntity.ok(cppChecks);
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
         }
    }

    @PostMapping("/builds-name/cppChecks")
    public ResponseEntity<?> findCppChecksByBuildNames(@RequestBody CppCheckBuildNamesRequest request){
        try{
            List<CppCheck> cppChecks = cppCheckService.findCppChecksByBuildNames(request.getBuildNames());
            List<CppCheckResponse> responseList = buildListFromCppCheck(cppChecks);
            return ResponseEntity.status(HttpStatus.OK).body(responseList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    private List<CppCheckResponse> buildListFromCppCheck(List<CppCheck> listOfCppChecks) {
        List<CppCheckResponse> responseList = new ArrayList<>();
        for (CppCheck cppCheck : listOfCppChecks) {
            responseList.add(buildFromCppCheck(cppCheck));
        }
        return responseList;
    }

    private CppCheckResponse buildFromCppCheck(CppCheck cppCheck) {
        return  CppCheckResponse.builder()
                .id(cppCheck.getId())
                .build(cppCheck.getBuild())
                .error(cppCheck.getError())
                .performance(cppCheck.getPerformance())
                .portability(cppCheck.getPortability())
                .warning(cppCheck.getWarning())
                .style(cppCheck.getStyle())
                .build();
     }

    private CppCheck buildFromRequest(CppCheckPostRequest request) {
        return CppCheck.builder()
                .error(request.getError())
                .performance(request.getPerformance())
                .portability(request.getPortability())
                .warning(request.getWarning())
                .style(request.getStyle())
                .build();
     }
}
