package com.fyp_poc.demo.controllers.buildTests;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BuildTestApiResponse {

    private long id;
    private long testFailed;
    private long testPassed;
}
