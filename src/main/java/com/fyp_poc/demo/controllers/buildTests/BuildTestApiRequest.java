package com.fyp_poc.demo.controllers.buildTests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BuildTestApiRequest {
    private long testFailed;
    private long testPassed;
}
