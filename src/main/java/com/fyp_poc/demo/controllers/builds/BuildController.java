package com.fyp_poc.demo.controllers.builds;

import com.fyp_poc.demo.DTO.Build;
import com.fyp_poc.demo.services.builds.BuildService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{nbOfBuilds}")
    public ResponseEntity findLastNBazelBuildNames(@PathVariable long nbOfBuilds){
        try{
            List<String> lastNBazelBuildNames = buildService.findLastNBazelBuildNames(nbOfBuilds);
            List<BazelStatsBuildNamesResponse> bazelStatsBuildNamesResponses = lastNBazelBuildNames.stream().map(name -> BazelStatsBuildNamesResponse.builder().buildName(name).build()).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(bazelStatsBuildNamesResponses);
        }catch (Exception e){
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

    @PutMapping("/build/{id}")
    public ResponseEntity updateBuild(@PathVariable Long id, @RequestBody BuildUpdateRequest request){
        try{
            if(!buildService.updateBuild(id,request.getBuildStatus(),request.getTestsStatus())){
                throw new NotFoundException("The requested build was not found");
            }
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/build/{id}")
    public ResponseEntity<?> deleteBuild(@PathVariable long id){
        try{
            buildService.deleteBuild(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/build/all")
    public ResponseEntity<?> deleteAllBuilds(){
        try{
            buildService.deleteAllBuilds();
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    private Build getBuildFromApiRequest(BuildApiRequest request) {
        return Build.builder()
                .buildName(request.getBuildName())
                .buildStatus(request.getBuildStatus())
                .testsStatus(request.getTestStatus())
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
                .buildStatus(build.getBuildStatus())
                .testsStatus(build.getTestsStatus())
                .build();
    }

}
