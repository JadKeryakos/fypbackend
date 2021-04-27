package com.fyp_poc.demo.controllers.cppCheck;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CppCheckPostRequest {

    private String buildName;
    private double error;
    private double performance;
    private double portability;
    private double style;
    private double warning;
}
