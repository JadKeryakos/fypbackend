package com.fyp_poc.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name="build_tests")
@Table(name="build_tests")
public class BuildTests {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="test_failed")
    private long testFailed;
    @Column(name="test_passed")
    private long testPassed;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "build_id", referencedColumnName = "id",unique=true)
    private Build build;

}
