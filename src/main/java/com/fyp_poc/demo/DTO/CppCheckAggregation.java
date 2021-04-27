package com.fyp_poc.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CppCheckAggregation {
    long error;
    @JsonProperty("performance")
    long performance;
    @JsonProperty("portability")
    long portability;
    @JsonProperty("style")
    long style;
    @JsonProperty("warning")
    long warning;
    @JsonProperty("aggregationSize")
    long aggregationSize;
}
