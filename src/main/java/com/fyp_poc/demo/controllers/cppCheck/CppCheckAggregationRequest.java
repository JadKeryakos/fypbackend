package com.fyp_poc.demo.controllers.cppCheck;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CppCheckAggregationRequest {
    @JsonProperty("aggregations")
    private List<String> aggregations;
    @JsonProperty("aggregationSize")
    private Long aggregationSize;
}
