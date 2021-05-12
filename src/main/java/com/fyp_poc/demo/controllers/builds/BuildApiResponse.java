package com.fyp_poc.demo.controllers.builds;


import lombok.*;
import java.util.Date;


/**
 * @author Maroun Ayli
 * This Object is returned upon creating the build Object and inserting it in the database
 */

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BuildApiResponse {

    private long id;
    /**
     * Name of the build
     */
    private String buildName;
    /**
     * When was the object created, could be used to trace when was the pipeline executed
     */
    private Date createDate;

    /**
     * The status of the Unit Build outcome, possible outcomes are : {success, failure}
     */
    private String buildStatus;
    /**
     * The status of the Unit Tests outcome, possible outcomes are : {success, failure}
     */
    private String testsStatus;
}
