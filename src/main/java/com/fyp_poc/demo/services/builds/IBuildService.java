package com.fyp_poc.demo.services.builds;

import com.fyp_poc.demo.DTO.BazelStats;
import com.fyp_poc.demo.DTO.Builds;

import java.util.List;

public interface IBuildService {
    List<Builds> findAllBazelBuilds();
}
