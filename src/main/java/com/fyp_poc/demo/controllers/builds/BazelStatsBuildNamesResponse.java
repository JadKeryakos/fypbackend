package com.fyp_poc.demo.controllers.builds;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Maroun Ayli
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BazelStatsBuildNamesResponse {
    String buildName;
}
