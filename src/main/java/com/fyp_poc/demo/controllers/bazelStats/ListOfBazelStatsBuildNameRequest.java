package com.fyp_poc.demo.controllers.bazelStats;

import lombok.*;

import java.util.ArrayList;

/**
 * @author Maroun Ayli
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListOfBazelStatsBuildNameRequest {
    /**
     * List of build names
     */
    private ArrayList<String> listOfBuildNames;
}
