package com.fyp_poc.demo.services.builds;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.Builds;

import com.fyp_poc.demo.repositories.BuildsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildService implements IBuildService {

    private final BuildsRepository buildsRepository;

    @Autowired
    public BuildService(BuildsRepository buildsRepository) {
        this.buildsRepository = buildsRepository;
    }

    @Override
    public List<Builds> findAllBazelBuilds() {
        return buildsRepository.findAll();
    }
}
