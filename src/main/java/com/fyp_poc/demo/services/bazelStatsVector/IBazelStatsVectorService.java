package com.fyp_poc.demo.services.bazelStatsVector;

import com.fyp_poc.demo.AggObjects.BazelStatsAgg;
import com.fyp_poc.demo.DTO.BazelStatsVector;
import com.fyp_poc.demo.DTO.CppCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IBazelStatsVectorService {

    List<BazelStatsVector> findTheLatestNBazelStats(long numberOfRows);
    Map<String, BazelStatsAgg> generateAggregations(List<String> aggregations, Long aggregationSize);


    List<BazelStatsVector> findBazelStatsByBuildNames(ArrayList<String> listOfBuildNames);
}
