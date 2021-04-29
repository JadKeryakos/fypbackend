package com.fyp_poc.demo.services.bazelStatsVector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.BazelStatsVector;
import com.fyp_poc.demo.repositories.BazelStatsVectorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class BazelStatsVectorService implements IBazelStatsVectorService{

    private final BazelStatsVectorRepository bazelStatsVectorRepository;
    private final Map<String, Function<List<BazelStatsVector>,Double>> aggregatorMap;

    public BazelStatsVectorService(BazelStatsVectorRepository bazelStatsVectorRepository,
                                   Map<String, Function<List<BazelStatsVector>, Double>> aggregatorMap) {
        this.bazelStatsVectorRepository = bazelStatsVectorRepository;
        this.aggregatorMap = aggregatorMap;
        this.aggregatorMap.put("sum",this::sum);
        this.aggregatorMap.put("max",this::max);
        this.aggregatorMap.put("min",this::min);
        this.aggregatorMap.put("avg",this::avg);
    }

    @Override
    public List<BazelStatsVector> findTheLatestNBazelStats(long numberOfRows) {
        return bazelStatsVectorRepository.findTheLatestNBazelStats(numberOfRows);
    }

    @Override
    public Map<String, BazelStats> generateAggregations(List<String> aggregations, Long aggregationSize) {
       Map<String,BazelStats> res = new HashMap<>();
       for(String agg : aggregations){
           res.put(agg,generateAggregation(generateRawStats(aggregationSize), aggregatorMap.get(agg)));
       }
       return res;
    }



    private Map<String, List<BazelStatsVector>> generateRawStats(Long aggregationSize) {
        List<BazelStatsVector> bazelStatsVectors = findTheLatestNBazelStats(aggregationSize);
        HashMap<String,List<BazelStatsVector>> map = new HashMap<>();
        for(BazelStatsVector vector : bazelStatsVectors){
            String name = vector.getName();
            if (!map.containsKey(name)) {
                map.put(name, new ArrayList<>());
            }
            map.get(name).add(vector);
        }
        return map;
    }

    private BazelStats generateAggregation(Map<String,List<BazelStatsVector>> map , Function<List<BazelStatsVector> , Double> f){
        HashMap<String,Double> res = new HashMap<>();
        for(String name : map.keySet()){
            res.put(name,f.apply(map.get(name)));
        }
        BazelStats bazelStats = new BazelStats();
        bazelStats.setBuildName("Aggregation");
        List<BazelStatsVector> bazelStatsVectors = new ArrayList<>();
        for(String prop : res.keySet()){
            bazelStatsVectors.add(new BazelStatsVector(null,prop,res.get(prop),"%"));
        }
        bazelStats.setBazelStatsVectorList(bazelStatsVectors);
        return bazelStats;
    }

    private Double sum(List<BazelStatsVector> list){
        return list.stream().map(BazelStatsVector::getTime).reduce(0.0,Double::sum);
    }
    private Double max(List<BazelStatsVector> list){
        return list.stream().map(BazelStatsVector::getTime).mapToDouble(x->x).max().orElse(0.0);
    }
    private Double min(List<BazelStatsVector> list){
        return list.stream().map(BazelStatsVector::getTime).mapToDouble(x->x).min().orElse(0.0);
    }
    private Double avg(List<BazelStatsVector> list){
        return list.stream().map(BazelStatsVector::getTime).mapToDouble(x->x).average().orElse(0.0);
    }



}
