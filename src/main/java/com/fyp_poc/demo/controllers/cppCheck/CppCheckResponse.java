package com.fyp_poc.demo.controllers.cppCheck;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyp_poc.demo.DTO.Build;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CppCheckResponse {
    @JsonIgnore
    private long id;
    private double error;
    private double performance;
    private double portability;
    private double style;
    private double warning;
    private Build build;
}
