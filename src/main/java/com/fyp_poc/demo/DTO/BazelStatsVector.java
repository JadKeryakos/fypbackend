package com.fyp_poc.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="bazel_stats_vector")
@Table(name="bazel_stats_vector")

public class BazelStatsVector {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id")
    private UUID id;
    @Column(name="name")
    @JsonProperty("name")
    String name;
    @Column(name="time")
    @JsonProperty("time")
    String time;
    @Column(name="percentage")
    @JsonProperty("percentage")
    String percentage;
}