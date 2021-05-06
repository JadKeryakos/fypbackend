package com.fyp_poc.demo.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @JoinColumn(name = "build_id")
    @JsonProperty("payload")
    private List<BazelStatsVector> bazelStatsVectorList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "build_id", referencedColumnName = "id",unique=true)
    private Build build;
}

