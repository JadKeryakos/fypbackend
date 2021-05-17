package com.fyp_poc.demo.controllers.buildTests;

import lombok.*;

/**
 * @author Maroun Ayli
 * Payload to create a BuildTests object
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BuildTestApiRequest {
    /**
     * Number of failed tests per build
     */
    private long testFailed;

    /**
     * Number of successful tests per build
     */
    private long testPassed;
}
