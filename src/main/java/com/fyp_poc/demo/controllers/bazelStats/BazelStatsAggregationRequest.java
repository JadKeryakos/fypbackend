
package com.fyp_poc.demo.controllers.bazelStats;

import lombok.*;
import java.util.List;

/**
 * @author : Maroun Ayli
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BazelStatsAggregationRequest {
    /**
     * List that contains the name of the aggregations to be used
     * Example aggregations : ["min","max","avg"]
     */
    List<String> aggregations;

    /**
     * Number of BazelStats Objects to be taken into consideration when aggregating
     */
    Long aggregationSize;
}
