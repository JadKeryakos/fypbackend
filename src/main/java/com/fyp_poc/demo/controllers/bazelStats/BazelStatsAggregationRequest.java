package com.fyp_poc.demo.controllers.bazelStats;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BazelStatsAggregationRequest {
    List<String> aggregations;
    Long aggregationSize;
}
