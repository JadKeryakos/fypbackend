package com.fyp_poc.demo.controllers.cppCheck;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CppCheckResponse {
    private long id;
    private String buildName;
    private double error;
    private double performance;
    private double portability;
    private double style;
    private double warning;
}
