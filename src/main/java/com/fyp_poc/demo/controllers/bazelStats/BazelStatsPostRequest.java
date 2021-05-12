package com.fyp_poc.demo.controllers.bazelStats;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * @author Maroun Ayli
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BazelStatsPostRequest {

    /**
     * List of BazelStats Vectors
     */
    @JsonProperty("payload")
    private List<BazelStatsVectorPostRequest> bazelStatsVectorList;
}

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
class BazelStatsVectorPostRequest {
    private String name;
    private Double time;
    private String percentage;
}