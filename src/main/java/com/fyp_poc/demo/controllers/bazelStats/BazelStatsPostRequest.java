package com.fyp_poc.demo.controllers.bazelStats;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BazelStatsPostRequest {

    private String buildName;
    private List<BazelStatsVectorPostRequest> bazelStatsVectorList;
}

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
class BazelStatsVectorPostRequest {
    private String name;
    private String time;
    private String percentage;
}