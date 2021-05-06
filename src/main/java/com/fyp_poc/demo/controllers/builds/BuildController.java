package com.fyp_poc.demo.controllers.builds;

import com.fyp_poc.demo.DTO.Build;
import com.fyp_poc.demo.services.builds.BuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            List<BuildGetApiResponse> responseList = buildListFromBuildList(bazelBuilds);
            return ResponseEntity.status(HttpStatus.OK).body(bazelBuilds);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    private List<BuildGetApiResponse> buildListFromBuildList(List<Build> listOfBuilds) {
        List<BuildGetApiResponse> responseList = new ArrayList<>();
        for (Build build : listOfBuilds) {
            responseList.add(buildFromCppCheck(build));
        }
        return responseList;
    }

    private BuildGetApiResponse buildFromCppCheck(Build build) {
        return BuildGetApiResponse.builder()
                .buildName(build.getBuildName())
                .id(build.getId())
                .createDate(build.getCreateDate())
                .build();
    }

}
