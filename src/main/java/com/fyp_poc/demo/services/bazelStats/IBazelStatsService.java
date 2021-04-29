package com.fyp_poc.demo.services.bazelStats;

import com.fyp_poc.demo.DTO.BazelStats;

import java.util.ArrayList;
import java.util.List;

public interface IBazelStatsService {
    BazelStats addStat(BazelStats bazelStats);

    List<BazelStats> findAllBazelStats();


    BazelStats findByBuildName(String buildName);

    List<?> findLastNBazelBuildNames(long numberOfRows);

    List<BazelStats> findListOfBazelStatsByBuildName(ArrayList<String> listOfBuildNames);
}
