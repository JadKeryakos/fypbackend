package com.fyp_poc.demo.controllers.bazelStats;

import com.fyp_poc.demo.DTO.BazelStats;


import com.fyp_poc.demo.DTO.BazelStatsVector;
import com.fyp_poc.demo.DTO.CppCheck;
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
import java.util.stream.Collectors;

@Getter
@Setter
@RestController
@RequestMapping("/bazel-stats")
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

    @PostMapping("")
    public ResponseEntity addStat(@RequestBody BazelStatsPostRequest request){
        try{
            BazelStats bazelStats = bazelStatsService.addStat(buildBazelStatsFrom(request));
            return ResponseEntity.status(HttpStatus.OK).body(bazelStats);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity findAllBazelStats(){
        try{
            List<BazelStats> bazelStatsList = bazelStatsService.findAllBazelStats();
            return ResponseEntity.status(HttpStatus.OK).body(bazelStatsList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{numberOfRows}")
    public ResponseEntity findTheLatestNBazelStats(@PathVariable long numberOfRows){
        try{
            List<BazelStatsVector> bazelStatsList = bazelStatsVectorService.findTheLatestNBazelStats(numberOfRows);
            return ResponseEntity.status(HttpStatus.OK).body(bazelStatsList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/build-names/{numberOfRows}")
    public ResponseEntity findLastNBazelBuildNames(@PathVariable long numberOfRows){
        try{
            List<String> bazelStatsList = bazelStatsService.findLastNBazelBuildNames(numberOfRows);
            List<BazelStatsBuildNamesResponse> responses= bazelStatsList.stream().map(name-> BazelStatsBuildNamesResponse.builder().buildName(name).build()).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/build")
    public ResponseEntity findListOfBazelStatsByBuildName(@RequestBody ListOfBazelStatsRequest request){
        try{
            System.out.println(request.getListOfBuildNames().get(0));
            List<BazelStats> bazelStatsList = bazelStatsService.findListOfBazelStatsByBuildName(request.getListOfBuildNames());
            return ResponseEntity.status(HttpStatus.OK).body(bazelStatsList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/agg")
    public Map<String,BazelStats> getAggregations(@RequestBody BazelStatsAggregationRequest request){
        return bazelStatsVectorService.generateAggregations(request.getAggregations(),request.getAggregationSize());
    }

    private BazelStats buildBazelStatsFrom(BazelStatsPostRequest request) {
        return new BazelStats().builder()
                .buildName(request.getBuildName())
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
        return new BazelStatsVector().builder()
                .name(bazelStatPostRequestEntry.getName())
                .percentage(bazelStatPostRequestEntry.getPercentage())
                .time(bazelStatPostRequestEntry.getTime())
                .build();
    }

}
