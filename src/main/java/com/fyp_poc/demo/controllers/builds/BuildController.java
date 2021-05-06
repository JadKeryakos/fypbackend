package com.fyp_poc.demo.controllers.builds;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.Builds;
import com.fyp_poc.demo.services.builds.BuildService;
import com.fyp_poc.demo.services.cppCheck.CppCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/builds")
public class BuildController {

    private final BuildService buildService;

    @Autowired
    public BuildController(BuildService buildService) {
        this.buildService = buildService;
    }


    @GetMapping("")
    public ResponseEntity findAllBazelBuilds() {
        try {
            List<Builds> bazelBuilds = buildService.findAllBazelBuilds();
            return ResponseEntity.status(HttpStatus.OK).body(bazelBuilds);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
