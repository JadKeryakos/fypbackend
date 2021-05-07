package com.fyp_poc.demo.services.bazelStats;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.BazelStatsVector;
import com.fyp_poc.demo.DTO.Build;
import com.fyp_poc.demo.repositories.BazelStatsRepository;
import com.fyp_poc.demo.repositories.BuildsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BazelStatsService implements IBazelStatsService {

    private final BazelStatsRepository bazelStatsRepository;
    private final BuildsRepository buildsRepository;


    @Autowired
    public BazelStatsService(BazelStatsRepository bazelStatsRepository, BuildsRepository buildsRepository) {
        this.bazelStatsRepository = bazelStatsRepository;
        this.buildsRepository = buildsRepository;
    }

    @Override
    public BazelStats addStat(long buildId, BazelStats bazelStats) {
        Build specifiedBuild = buildsRepository.findById(buildId).get();
        bazelStats.setBuild(specifiedBuild);

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

    @Override
    public BazelStats findByBuildName(String buildName) {
        return null;
    }

    @Override
    public List<?> findLastNBazelBuildNames(long numberOfRows) {
        return null;
    }

    @Override
    public List<BazelStats> findListOfBazelStatsByBuildName(ArrayList<String> listOfBuildNames) {
        return null;
    }

    @Override
    public List<BazelStats> findBazelStatsByBuildNames(List<String> listOfBuildNames) {
        return bazelStatsRepository.findBazelStatsByBuildNames(listOfBuildNames);
    }

    /*
    @Override
    public BazelStats findByBuildName(String buildName) {
        return bazelStatsRepository.findByBuildName(buildName);
    }

    @Override
    public List<String> findLastNBazelBuildNames(long numberOfRows) {
        List<String> lastNBazelBuildNames = bazelStatsRepository.findLastNBazelBuildNames(numberOfRows);
        return lastNBazelBuildNames;
    }

    @Override
    public List<BazelStats> findListOfBazelStatsByBuildName(ArrayList<String> listOfBuildNames) {
        List<BazelStats> listOfStats = new ArrayList<>();
        for (String bazelBuild: listOfBuildNames){
            BazelStats byBuildName = bazelStatsRepository.findByBuildName(bazelBuild);
            listOfStats.add(byBuildName);
        }
        return listOfStats;
    }


*/

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
