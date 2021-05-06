package com.fyp_poc.demo.services.builds;

import com.fyp_poc.demo.DTO.Build;
import com.fyp_poc.demo.controllers.builds.BuildApiRequest;

import java.util.List;

public interface IBuildService {
    List<Build> findAllBazelBuilds();

    Build createBuild(Build build);

    List<String> findLastNBazelBuildNames(long nbOfBuilds);
}
