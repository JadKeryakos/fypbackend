package com.fyp_poc.demo.services.buildTests;

import com.fyp_poc.demo.DTO.BuildTests;
import com.fyp_poc.demo.repositories.BuildTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildTestService implements IBuildTestsService {

    private final BuildTestsRepository buildTestsRepository;

    @Autowired
    public BuildTestService(BuildTestsRepository buildTestsRepository) {
        this.buildTestsRepository = buildTestsRepository;
    }

    @Override
    public BuildTests findBuildTestByBuildId(long buildId) {
        return buildTestsRepository.findAll().get(0);
    }
}
