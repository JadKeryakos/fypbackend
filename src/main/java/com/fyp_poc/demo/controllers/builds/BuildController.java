package com.fyp_poc.demo.controllers.builds;

import com.fyp_poc.demo.DTO.Build;
import com.fyp_poc.demo.services.builds.BuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            List<Build> bazelBuilds = buildService.findAllBazelBuilds();
            List<BuildApiResponse> responseList = buildListFromBuildList(bazelBuilds);
            return ResponseEntity.status(HttpStatus.OK).body(bazelBuilds);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("")
    public ResponseEntity createBuild(@RequestBody BuildApiRequest request) {
        try {
            Build build = buildService.createBuild(getBuildFromApiRequest(request));
            BuildApiResponse response = buildFromBuildDTO(build);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private Build getBuildFromApiRequest(BuildApiRequest request) {
        return Build.builder()
                .buildName(request.getBuildName())
                .build();
    }


    private List<BuildApiResponse> buildListFromBuildList(List<Build> listOfBuilds) {
        List<BuildApiResponse> responseList = new ArrayList<>();
        for (Build build : listOfBuilds) {
            responseList.add(buildFromBuildDTO(build));
        }
        return responseList;
    }

    private BuildApiResponse buildFromBuildDTO(Build build) {
        return BuildApiResponse.builder()
                .buildName(build.getBuildName())
                .id(build.getId())
                .createDate(build.getCreateDate())
                .build();
    }

}
