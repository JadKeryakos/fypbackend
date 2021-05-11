package com.fyp_poc.demo.controllers.builds;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BuildApiRequest {
    private String buildName;
    private String buildStatus;
    private String testStatus;
}
