package com.fyp_poc.demo.services.bazelStatsVector;

import com.fyp_poc.demo.DTO.BazelStatsVector;
import com.fyp_poc.demo.repositories.BazelStatsVectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BazelStatsVectorService implements IBazelStatsVectorService{

    BazelStatsVectorRepository bazelStatsVectorRepository;

    @Autowired
    public BazelStatsVectorService(BazelStatsVectorRepository bazelStatsVectorRepository) {
        this.bazelStatsVectorRepository = bazelStatsVectorRepository;
    }

    @Override
    public List<BazelStatsVector> findTheLatestNBazelStats(long numberOfRows) {
        return bazelStatsVectorRepository.findTheLatestNBazelStats(numberOfRows);
    }
}
