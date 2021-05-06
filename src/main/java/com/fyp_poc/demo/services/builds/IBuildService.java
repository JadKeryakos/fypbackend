package com.fyp_poc.demo.services.builds;

import com.fyp_poc.demo.DTO.Build;

import java.util.List;

public interface IBuildService {
    List<Build> findAllBazelBuilds();
}
