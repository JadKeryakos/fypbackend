package com.fyp_poc.demo.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name="bazel_stats")
@Table(name="bazel_stats")
public class BazelStats {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "bazel_stats_id")
    @JsonProperty("payload")
    private List<BazelStatsVector> bazelStatsVectorList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "build_id", referencedColumnName = "id",unique=true)
    private Build build;
}

