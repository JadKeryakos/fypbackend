package com.fyp_poc.demo.services.bazelStats;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.BazelStatsVector;
import com.fyp_poc.demo.repositories.BazelStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BazelStatsService implements IBazelStatsService {

    BazelStatsRepository bazelStatsRepository;

    @Autowired
    public BazelStatsService(BazelStatsRepository bazelStatsRepository) {
        this.bazelStatsRepository = bazelStatsRepository;
    }

    @Override
    public BazelStats addStat(BazelStats bazelStats) {
        /***
         *
         * Create a unique ID for each bazelStatVector, to avoid having the
         * database to auto-generate IDs.
         *
         ***/
        List<BazelStatsVector> bazelStatsVectorList = bazelStats.getBazelStatsVectorList();
        for(BazelStatsVector bazelStatsVector: bazelStatsVectorList){
            UUID uuid = UUID.randomUUID();
            bazelStatsVector.setId(uuid);
        }
        return bazelStatsRepository.save(bazelStats);
    }

    @Override
    public List<BazelStats> findAllBazelStats() {
        return bazelStatsRepository.findAll();
    }
}
