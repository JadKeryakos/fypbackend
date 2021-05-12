package com.fyp_poc.demo.controllers.builds;

import com.fyp_poc.demo.DTO.Build;
import com.fyp_poc.demo.services.builds.BuildService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maroun Ayli
 */
@RestController
@RequestMapping("/builds")
public class BuildController {

    /**
     * Service that is used to perform database related operations on builds
     */
    private final BuildService buildService;

    @Autowired
    public BuildController(BuildService buildService) {
        this.buildService = buildService;
    }


    /**
     * @return All the Build Objects
     */
    @GetMapping("")
    public ResponseEntity<?> findAllBazelBuilds() {
        try {
            List<Build> bazelBuilds = buildService.findAllBazelBuilds();
            return ResponseEntity.status(HttpStatus.OK).body(bazelBuilds);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    /**
     * @param n The number of builds
     * @return List containing the names of the last n builds
     */
    @GetMapping("/{n}")
    public ResponseEntity<?> findLastNBazelBuildNames(@PathVariable long n){
        try{
            List<String> lastNBazelBuildNames = buildService.findLastNBazelBuildNames(n);
            List<BazelStatsBuildNamesResponse> bazelStatsBuildNamesResponses = lastNBazelBuildNames.stream().map(name -> BazelStatsBuildNamesResponse.builder().buildName(name).build()).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(bazelStatsBuildNamesResponses);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    /**
     * @param request Request Containing the necessary information to create a build
     * @return The created build object if successful, error otherwise.
     */
    @PostMapping("")
    public ResponseEntity<?> createBuild(@RequestBody BuildApiRequest request) {
        try {
            Build build = buildService.createBuild(getBuildFromApiRequest(request));
            BuildApiResponse response = buildFromBuildDTO(build);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * @param id The id of the build to be updated.
     * @param request The request contains the updated status about the build and tests outcomes.
     * @return
     */
    @PutMapping("/build/{id}")
    public ResponseEntity<?> updateBuild(@PathVariable Long id, @RequestBody BuildUpdateRequest request){
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

    /**
     * @param id The id of the build to be deleted.
     * @return Empty response if successful, error otherwise.
     *
     * Deletes the build and every other stat related to it.
     */
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

    /**
     * @return Empty response if successful, error otherwise.
     * Deletes all the builds and everything related to them -> Empties the database.
     */
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
