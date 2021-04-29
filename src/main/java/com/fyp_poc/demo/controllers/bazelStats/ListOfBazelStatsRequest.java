package com.fyp_poc.demo.controllers.bazelStats;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListOfBazelStatsRequest {
    private ArrayList<String> listOfBuildNames;
}
