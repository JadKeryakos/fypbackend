package com.fyp_poc.demo.controllers.buildTests;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NamesListRequest {
    private List<String> names;
}
