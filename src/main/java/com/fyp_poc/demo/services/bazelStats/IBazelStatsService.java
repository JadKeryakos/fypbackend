package com.fyp_poc.demo.services.bazelStats;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.BazelStatsVector;

import java.util.ArrayList;
import java.util.List;

public interface IBazelStatsService {
    BazelStats addStat(long buildId,BazelStats bazelStats);

    List<BazelStats> findAllBazelStats();


    BazelStats findByBuildName(String buildName);

    List<?> findLastNBazelBuildNames(long numberOfRows);

    List<BazelStats> findListOfBazelStatsByBuildName(ArrayList<String> listOfBuildNames);
    void removeBazelStats(long id);
    List<BazelStats> findBazelStatsByBuildNames(List<String> listOfBuildNames);
}
