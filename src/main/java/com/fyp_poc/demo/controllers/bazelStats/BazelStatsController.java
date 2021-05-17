package com.fyp_poc.demo.controllers.bazelStats;

import com.fyp_poc.demo.DTO.BazelStats;


import com.fyp_poc.demo.AggObjects.BazelStatsAgg;
import com.fyp_poc.demo.DTO.BazelStatsVector;
import com.fyp_poc.demo.DTO.CppCheck;
import com.fyp_poc.demo.controllers.cppCheck.CppCheckBuildNamesRequest;
import com.fyp_poc.demo.controllers.cppCheck.CppCheckResponse;
import com.fyp_poc.demo.services.bazelStats.BazelStatsService;
import com.fyp_poc.demo.services.bazelStatsVector.BazelStatsVectorService;
import javassist.NotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : Maroun Ayli
 */
@Getter
@Setter
@RestController
@RequestMapping("")
public class BazelStatsController {


    /**
     * Service that manages bazelStats-related queries
     */
    private BazelStatsService bazelStatsService;
    /**
     * Service that manages bazelStatsVectors-related queries
     */
    private BazelStatsVectorService bazelStatsVectorService;


    @Autowired
    public BazelStatsController(BazelStatsService bazelStatsService, BazelStatsVectorService bazelStatsVectorService) {
        this.bazelStatsService = bazelStatsService;
        this.bazelStatsVectorService = bazelStatsVectorService;
    }


    /**
     * @param buildId The id of the build
     * @param request Request of Type BazelStatsPostRequest, contains the Payload found in the BazelStats Object.
     * @return Returns the same object if it has been committed committed successfully to the database, else returns an error
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

    /**
     * @return Fetches all the BazelStats Objects from the database and returns them in the form of a list.
     * Return an error if it fails.
     */
    @GetMapping("/bazel-stats")
    public ResponseEntity<?> findAllBazelStats(){
        try{
            List<BazelStats> bazelStatsList = bazelStatsService.findAllBazelStats();
            return ResponseEntity.status(HttpStatus.OK).body(bazelStatsList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/bazel-stats/{bazelStatsId}")
    public ResponseEntity<?> findBazelStatsById(@PathVariable("bazelStatsId") long id){
        try{
            BazelStats bazelStats = bazelStatsService.findBazelStatsById(id);
            if(bazelStats == null){
                throw new NotFoundException(String.format("Bazel Stats with Id %d was not found" , id));
            }
            return ResponseEntity.status(HttpStatus.OK).body(bazelStats);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * @param n Number of BazelStats Objects to fetch from the database
     * @return A list of the last N BazelStats Objects
     */
    @GetMapping("/bazel-stats/last/{n}")
    public ResponseEntity<?> findTheLatestNBazelStats(@PathVariable long n){
        try{
            List<BazelStats> bazelStatsList = bazelStatsVectorService.lastNBazelStats(n);
            return ResponseEntity.status(HttpStatus.OK).body(bazelStatsList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * @param request list of build names
     * @return list of BazelStats Objects where the name is in the requested list. Returns error upon failure.
     */
    @PostMapping("/bazel-stats/build-names")
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
