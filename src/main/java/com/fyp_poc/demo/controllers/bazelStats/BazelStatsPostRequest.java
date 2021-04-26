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


    String buildName;
    private List<BazelStatsVectorPostRequest> bazelStatsVectorList;
}

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
class BazelStatsVectorPostRequest {
    String name;
    String time;
    String percentage;
}