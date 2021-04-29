package com.fyp_poc.demo.controllers.bazelStats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BazelStatsBuildNamesResponse {
    String buildName;
}
