package com.fyp_poc.demo.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;


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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name="build_name")
    @JsonProperty("build-name")
    private String buildName;
    @JsonProperty("error")
    long error;
    @JsonProperty("performance")
    long performance;
    @JsonProperty("portability")
    long portability;
    @JsonProperty("style")
    long style;
    @JsonProperty("warning")
    long warning;
    static List<String> attributes = Arrays.asList("error", "performance", "portability", "style", "warning");
}
