package com.fyp_poc.demo.controllers.cppCheck;

import com.fyp_poc.demo.DTO.CppCheck;
import com.fyp_poc.demo.services.cppCheck.CppCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cppcheck")
public class CppCheckController {

     private final CppCheckService cppCheckService;

     @Autowired
    public CppCheckController(CppCheckService cppCheckService) {
        this.cppCheckService = cppCheckService;
    }


    @PostMapping("")
    public ResponseEntity<?> addCheck(@RequestBody CppCheckPostRequest request){
         try{
             CppCheck cppCheck = cppCheckService.addCheck(buildFromRequest(request));
             CppCheckResponse response = buildFromCppCheck(cppCheck);
             return ResponseEntity.status(HttpStatus.OK).body(response);
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
         }
    }

    @PostMapping("/agg")
    public Map<String,CppCheck> cppCheckAggregations (@RequestBody CppCheckAggregationRequest request){
         return cppCheckService.cppCheckAggregation(request.getAggregations(), request.getAggregationSize());
    }

    @GetMapping("/{cppCheckId}")
    public ResponseEntity<?> findCheckById(@PathVariable Long cppCheckId){
        try{
            CppCheck cppCheck = cppCheckService.findCppCheck(cppCheckId);
            CppCheckResponse response = buildFromCppCheck(cppCheck);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> findAllChecks(){
        try{
            List<CppCheck> cppCheck = cppCheckService.findAllChecks();
            List<CppCheckResponse> responseList = buildListFromCppCheck(cppCheck);
            return ResponseEntity.status(HttpStatus.OK).body(responseList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/last/{n}")
    public ResponseEntity<?> findNChecks(@RequestParam Long n){
         try{
             List<CppCheck> cppChecks = cppCheckService.findLastNChecks(n).stream().sorted(Comparator.comparing(CppCheck::getId)).collect(Collectors.toList());
             List<CppCheckResponse> responseList = buildListFromCppCheck(cppChecks);
             return ResponseEntity.status(HttpStatus.OK).body(responseList);
         }
         catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
         }
    }

    @PostMapping("/build-names")
    public ResponseEntity<?> buildListByBuildNames(@RequestBody CppCheckBuildNamesRequest buildNamesRequest){
         try{
             List<CppCheckResponse> responseList = buildListFromCppCheck(cppCheckService.findCppCheckByBuildNames(buildNamesRequest.getBuildNames()));
             return ResponseEntity.ok(responseList);
         }
         catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
         }
    }

    @GetMapping("/build-names/last/{n}")
    public ResponseEntity<?> lastNbuildNames(@PathVariable long n){
         try{
             return ResponseEntity.ok(cppCheckService.findLastNBuildNames(n));
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
                .buildName(cppCheck.getBuildName())
                .error(cppCheck.getError())
                .performance(cppCheck.getPerformance())
                .portability(cppCheck.getPortability())
                .warning(cppCheck.getWarning())
                .style(cppCheck.getStyle())
                .build();
     }

    private CppCheck buildFromRequest(CppCheckPostRequest request) {
        return CppCheck.builder()
                .buildName(request.getBuildName())
                .error(request.getError())
                .performance(request.getPerformance())
                .portability(request.getPortability())
                .warning(request.getWarning())
                .style(request.getStyle())
                .build();
     }
}
