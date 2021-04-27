package com.fyp_poc.demo.services.bazelStatsVector;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.BazelStatsVector;

import java.util.List;
import java.util.Map;

public interface IBazelStatsVectorService {

    List<BazelStatsVector> findTheLatestNBazelStats(long numberOfRows);
    Map<String,BazelStats> generateAggregations(List<String> aggregations,Long aggregationSize);
}
