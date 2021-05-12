package com.fyp_poc.demo.controllers.builds;

import lombok.*;

/**
 * @author Maroun Ayli
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BuildApiRequest {
    /**
     * The name of the referenced/created Build
     */
    private String buildName;

    /**
     * The status of the build outcome, possible outcomes are :  {success, failure}
     */
    private String buildStatus;
    /**
     * The status of the Unit Tests outcome, possible outcomes are : {success, failure}
     */
    private String testStatus;
}
