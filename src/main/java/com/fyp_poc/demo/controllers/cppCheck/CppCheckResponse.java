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
    private long error;
    private long performance;
    private long portability;
    private long style;
    private long warning;
}
