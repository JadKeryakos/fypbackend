package com.fyp_poc.demo.AggObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BazelStatsVectorAgg {


    @JsonProperty("name")
    String name;
    @JsonProperty("time")
    Double time;

}
