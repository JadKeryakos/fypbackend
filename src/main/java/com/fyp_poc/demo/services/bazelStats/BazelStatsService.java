package com.fyp_poc.demo.services.bazelStats;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.BazelStatsVector;
import com.fyp_poc.demo.repositories.BazelStatsRepository;
import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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



  /*  @Override
    public List<BazelStats> findTheAggregateOfNBazelStats(String number) {
        List<BazelStats> all = bazelStatsRepository.findAll();
        BazelStats bazelStatsAggregate;
        all.stream().forEach(bazelStats -> {

        });
        return hashMap;
    }
*/

}
