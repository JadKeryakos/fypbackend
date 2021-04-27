package com.fyp_poc.demo.controllers.functional;


import com.fyp_poc.demo.DTO.CppCheckAggregation;

import java.time.LocalDateTime;
import java.util.List;

@FunctionalInterface
public interface CppCheckAggregator {
    List<CppCheckAggregation> process(double x);
}