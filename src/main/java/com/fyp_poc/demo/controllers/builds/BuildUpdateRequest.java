package com.fyp_poc.demo.controllers.builds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Maroun Ayli
 */
@Getter
@Setter
@AllArgsConstructor
public class BuildUpdateRequest {
    /**
     * The updated build status, possible values : {success, failure}
     */
    private String buildStatus;

    /**
     * The updated tests status, possible values : {success, failure}
     */
    private String testsStatus;
}