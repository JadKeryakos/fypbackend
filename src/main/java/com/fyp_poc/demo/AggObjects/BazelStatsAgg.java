package com.fyp_poc.demo.AggObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BazelStatsAgg {
    @JsonProperty("payload")
    private List<BazelStatsVectorAgg> bazelStatsVectorList;
}
