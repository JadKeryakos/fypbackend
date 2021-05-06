package com.fyp_poc.demo.services.buildTests;

import com.fyp_poc.demo.DTO.Build;
import com.fyp_poc.demo.DTO.BuildTests;
import com.fyp_poc.demo.repositories.BuildTestsRepository;
import com.fyp_poc.demo.repositories.BuildsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildTestService implements IBuildTestsService {

    private final BuildTestsRepository buildTestsRepository;
    private final BuildsRepository buildsRepository;

    @Autowired
    public BuildTestService(BuildTestsRepository buildTestsRepository, BuildsRepository buildsRepository) {
        this.buildTestsRepository = buildTestsRepository;
        this.buildsRepository = buildsRepository;
    }

    @Override
    public BuildTests findBuildTestByBuildId(long buildId) {
        return buildTestsRepository.findAll().get(0);
    }

    @Override
    public BuildTests addTestForBuildUsingBuildId(long buildId, BuildTests buildTest) {
        Build specifiedBuild = buildsRepository.findById(buildId).get();
        System.out.println("=====================================================================================");

        System.out.println(specifiedBuild);
        System.out.println("=====================================================================================");

        buildTest.setBuild(specifiedBuild);
        return buildTestsRepository.save(buildTest);
    }
}
