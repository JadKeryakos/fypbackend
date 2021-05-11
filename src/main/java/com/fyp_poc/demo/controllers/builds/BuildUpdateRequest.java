package com.fyp_poc.demo.controllers.builds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BuildUpdateRequest {
    private String buildStatus;
    private String testsStatus;
}