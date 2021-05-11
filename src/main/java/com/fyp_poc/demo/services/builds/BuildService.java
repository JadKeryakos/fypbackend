package com.fyp_poc.demo.services.builds;

import com.fyp_poc.demo.DTO.Build;

import com.fyp_poc.demo.repositories.BuildsRepository;
import com.fyp_poc.demo.services.bazelStats.BazelStatsService;
import com.fyp_poc.demo.services.buildTests.BuildTestService;
import com.fyp_poc.demo.services.cppCheck.CppCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildService implements IBuildService {

    private final BuildsRepository buildsRepository;
    private final CppCheckService cppCheckService;
    private final BazelStatsService bazelStatsService;
    private final BuildTestService buildTestService;

    @Autowired
    public BuildService(BuildsRepository buildsRepository, CppCheckService cppCheckService, BazelStatsService bazelStatsService, BuildTestService buildTestService) {
        this.buildsRepository = buildsRepository;
        this.cppCheckService = cppCheckService;
        this.bazelStatsService = bazelStatsService;
        this.buildTestService = buildTestService;
    }

    @Override
    public List<Build> findAllBazelBuilds() {
        return buildsRepository.findAll();
    }

    @Override
    public Build createBuild(Build build) {
        return buildsRepository.save(build);
    }

    @Override
    public List<String> findLastNBazelBuildNames(long nbOfBuilds) {
        return buildsRepository.findLastNBazelBuildNames(nbOfBuilds);
    }

    @Override
    public void deleteBuild(long id) {
        cppCheckService.removeCppCheckByBuildId(id);
        bazelStatsService.removeBazelStatsForBuild(id);
        buildTestService.removeBuildTestsForBuild(id);
        buildsRepository.deleteById(id);
    }

    @Override
    public void deleteAllBuilds() {
        cppCheckService.removeAllCppCheck();
        bazelStatsService.removeAllBazelStats();
        buildTestService.removeAllBuildTests();
        buildsRepository.removeAll();
    }

    @Override
    public Boolean updateBuild(Long id, String buildStatus, String testsStatus) {
        Build build = buildsRepository.findById(id).orElse(null);
        if(build==null)
            return false;
        build.setBuildStatus(buildStatus);
        build.setTestsStatus(testsStatus);
        buildsRepository.save(build);
        return true;
    }
}
