package com.fyp_poc.demo.controllers.bazelStats;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.repositories.BazelStatsRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RestController
@RequestMapping("/bazel-stats")
public class BazelStatsController {
    private final BazelStatsRepository bazelStatsRepository;






    public BazelStatsController(BazelStatsRepository bazelStatsListRepository) {
        this.bazelStatsRepository = bazelStatsListRepository;
    }


    @PostMapping("/")
    BazelStats addStatNew(@RequestBody BazelStats bazelStatsList){
        return bazelStatsRepository.save(bazelStatsList);
    }



    @GetMapping("/all")
    ResponseEntity<Iterable<BazelStats>> getAllStats(){
        return ResponseEntity.ok(bazelStatsRepository.findAll());
    }

    @PostMapping("/add")
    BazelStats addStat(@RequestBody BazelStats bazelStatsList){
        return bazelStatsRepository.save(bazelStatsList);
    }
}
