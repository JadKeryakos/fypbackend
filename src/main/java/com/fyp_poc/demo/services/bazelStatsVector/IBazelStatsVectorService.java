package com.fyp_poc.demo.services.bazelStatsVector;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.BazelStatsVector;

import java.util.List;

public interface IBazelStatsVectorService {

    List<BazelStatsVector> findTheLatestNBazelStats(long numberOfRows);
}
