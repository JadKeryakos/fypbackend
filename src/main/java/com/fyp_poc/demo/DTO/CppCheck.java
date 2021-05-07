package com.fyp_poc.demo.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cpp_check")
public class CppCheck {
    @Id
    @Column(name="id")
    @GeneratedValue
    @JsonIgnore
    private long id;
    @JsonProperty("error")
    double error;
    @JsonProperty("performance")
    double performance;
    @JsonProperty("portability")
    double portability;
    @JsonProperty("style")
    double style;
    @JsonProperty("warning")
    double warning;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "build_id", referencedColumnName = "id",unique=true)
    private Build build;


}
