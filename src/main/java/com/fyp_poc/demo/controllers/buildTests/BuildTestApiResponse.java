package com.fyp_poc.demo.controllers.buildTests;

import com.fyp_poc.demo.DTO.Build;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author Maroun Ayli
 * This object is returned whenever a BuildTests object is created
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BuildTestApiResponse {

    private long id;
    /**
     * Number of failed tests per build
     */
    private long testFailed;

    /**
     * Number of successful tests per build
     */
    private long testPassed;

    /**
     * Referenced build
     */
    private Build build;
}
