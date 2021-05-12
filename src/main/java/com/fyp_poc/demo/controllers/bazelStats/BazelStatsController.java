package com.fyp_poc.demo.controllers.bazelStats;

import com.fyp_poc.demo.DTO.BazelStats;


import com.fyp_poc.demo.AggObjects.BazelStatsAgg;
import com.fyp_poc.demo.DTO.BazelStatsVector;
import com.fyp_poc.demo.DTO.CppCheck;
import com.fyp_poc.demo.controllers.cppCheck.CppCheckBuildNamesRequest;
import com.fyp_poc.demo.controllers.cppCheck.CppCheckResponse;
import com.fyp_poc.demo.services.bazelStats.BazelStatsService;
import com.fyp_poc.demo.services.bazelStatsVector.BazelStatsVectorService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@RestController
@RequestMapping("")
public class BazelStatsController {


    private BazelStatsService bazelStatsService;
    private BazelStatsVectorService bazelStatsVectorService;

    @Autowired
    public BazelStatsController(BazelStatsService bazelStatsService, BazelStatsVectorService bazelStatsVectorService) {
        this.bazelStatsService = bazelStatsService;
        this.bazelStatsVectorService = bazelStatsVectorService;
    }



    /***
     *
     * For Later, Map the BazelStats Entity with a BazelStatsResponse Object.
     * For Now, Return the persisted Object without mapping, In both the POST and GET Endpoints.
     * Only a BazelStatsPostRequest is used to map the request to the persisted entity in our domain.
     *
     */

    @PostMapping("/builds/{buildId}/bazel-stats")
    public ResponseEntity<?> addStat(@PathVariable("buildId") long buildId, @RequestBody BazelStatsPostRequest request){
        try{
            BazelStats bazelStats = bazelStatsService.addStat(buildId,buildBazelStatsFrom(request));
            return ResponseEntity.status(HttpStatus.OK).body(bazelStats);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/bazel-stats")
    public ResponseEntity<?> findAllBazelStats(){
        try{
            List<BazelStats> bazelStatsList = bazelStatsService.findAllBazelStats();
            return ResponseEntity.status(HttpStatus.OK).body(bazelStatsList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/bazel-stats/{n}")
    public ResponseEntity<?> findTheLatestNBazelStats(@PathVariable long n){
        try{
            List<BazelStatsVector> bazelStatsList = bazelStatsVectorService.findTheLatestNBazelStats(n);
            return ResponseEntity.status(HttpStatus.OK).body(bazelStatsList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/builds-name/bazel-stats")
    public ResponseEntity<?> findBazelStatsByBuildNames(@RequestBody ListOfBazelStatsBuildNameRequest request){
        try{
            List<BazelStats> BazelStatsVectorList = bazelStatsService.findBazelStatsByBuildNames(request.getListOfBuildNames());
            return ResponseEntity.status(HttpStatus.OK).body(BazelStatsVectorList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("bazel-stats/agg")
    public Map<String, BazelStatsAgg> getAggregations(@RequestBody BazelStatsAggregationRequest request){
        return bazelStatsVectorService.generateAggregations(request.getAggregations(),request.getAggregationSize());
    }

    private BazelStats buildBazelStatsFrom(BazelStatsPostRequest request) {
        return BazelStats.builder()
                .bazelStatsVectorList(buildStatsVectorListFrom(request.getBazelStatsVectorList()))
                .build();
    }

    private List<BazelStatsVector> buildStatsVectorListFrom(List<BazelStatsVectorPostRequest> requestList) {
        List<BazelStatsVector> responseList = new ArrayList<>();
            for (BazelStatsVectorPostRequest bazelStatPostRequestEntry : requestList) {
                responseList.add(buildSingleStat(bazelStatPostRequestEntry));
            }
            return responseList;
    }

    private BazelStatsVector buildSingleStat(BazelStatsVectorPostRequest bazelStatPostRequestEntry) {
        return   BazelStatsVector.builder()
                .name(bazelStatPostRequestEntry.getName())
                .percentage(bazelStatPostRequestEntry.getPercentage())
                .time(bazelStatPostRequestEntry.getTime())
                .build();
    }

}
