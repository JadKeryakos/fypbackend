package com.fyp_poc.demo.controllers.cppCheck;

import com.fyp_poc.demo.DTO.CppCheck;
import com.fyp_poc.demo.services.cppCheck.CppCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cppcheck")
public class CppCheckController {

     private final CppCheckService cppCheckService;

     @Autowired
    public CppCheckController(CppCheckService cppCheckService) {
        this.cppCheckService = cppCheckService;
    }


    @PostMapping("")
    public ResponseEntity addCheck(@RequestBody CppCheckPostRequest request){
         try{
             CppCheck cppCheck = cppCheckService.addCheck(buildFromRequest(request));
             CppCheckResponse response = buildFromCppCheck(cppCheck);
             return ResponseEntity.status(HttpStatus.OK).body(response);
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
         }
    }

    @GetMapping("/{cppCheckId}")
    public ResponseEntity findCheckById(@PathVariable UUID cppCheckId){
        try{
            CppCheck cppCheck = cppCheckService.findCheckById(cppCheckId);
            CppCheckResponse response = buildFromCppCheck(cppCheck);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity findAllChecks(){
        try{
            List<CppCheck> cppCheck = cppCheckService.findAllChecks();
            List<CppCheckResponse> responseList = buildListFromCppCheck(cppCheck);
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
        return new CppCheckResponse().builder()
                .id(cppCheck.getId())
                .error(cppCheck.getError())
                .performance(cppCheck.getPerformance())
                .portability(cppCheck.getPortability())
                .warning(cppCheck.getWarning())
                .style(cppCheck.getStyle())
                .build();
     }

    private CppCheck buildFromRequest(CppCheckPostRequest request) {
        return new CppCheck().builder()
                .error(request.getError())
                .performance(request.getPerformance())
                .portability(request.getPortability())
                .warning(request.getWarning())
                .style(request.getStyle())
                .build();
     }

     /*    private final CppCheckRepository cppCheckRepository;

    public CppCheckController(CppCheckRepository cppCheckRepository) {
        this.cppCheckRepository = cppCheckRepository;
    }

    @PostMapping("")
    ResponseEntity<CppCheckTest> addCheck(@RequestBody CppCheckTest cppCheck){
        cppCheckRepository.save(cppCheck);
        return ResponseEntity.ok(cppCheck);
    }

    @GetMapping("")
    ResponseEntity<Iterable<CppCheckTest>> getCheck(){
        return ResponseEntity.ok(cppCheckRepository.findAll());
    }*/


}
