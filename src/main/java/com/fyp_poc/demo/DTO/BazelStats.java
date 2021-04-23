package com.fyp_poc.demo.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="bazel_stats")
@Table(name="bazel_stats")
public class BazelStats {
    @Id
    @Column(name="build_name")
    @JsonProperty("build_name")
    String buildName;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "build_id")
    @JsonProperty("payload")
    private List<BazelStatsVector> bazelStatsVectorList;

}

@Getter
@Setter
@NoArgsConstructor
@Entity(name="bazel_stats_vector")
@Table(name="bazel_stats_vector")
class BazelStatsVector {
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