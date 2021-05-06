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
    @Column(name="build_name")
    @JsonProperty("build-name")
    private String buildName;
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

    public CppCheck(long id,String buildName,double error,double performance,double portability,double style, double warning){
        this.id=0L;
        this.buildName=buildName;
        this.portability=portability;
        this.error=error;
        this.performance=performance;
        this.style=style;
        this.warning=warning;

    }

    public CppCheck cppCheckReduceSingle(CppCheck baseReduce, BiFunction<Double,Double,Double> lambda){
        return new CppCheck(0L,"BuildName Aggregation",
                lambda.apply(error,baseReduce.getError()),
                lambda.apply(performance,baseReduce.getPerformance()),
                lambda.apply(portability, baseReduce.getPortability()),
                lambda.apply(style,baseReduce.getStyle()),
                lambda.apply(warning,baseReduce.getWarning()));
    }

    public static CppCheck cppCheckReduceList(List<CppCheck> cppChecks,Function<List<Double>,Double> lambda){
        return new CppCheck(0L,"BuildName Aggregation",
                lambda.apply(cppChecks.stream().map(CppCheck::getError).collect(Collectors.toList())),
                lambda.apply(cppChecks.stream().map(CppCheck::getPerformance).collect(Collectors.toList())),
                lambda.apply(cppChecks.stream().map(CppCheck::getPortability).collect(Collectors.toList())),
                lambda.apply(cppChecks.stream().map(CppCheck::getStyle).collect(Collectors.toList())),
                lambda.apply(cppChecks.stream().map(CppCheck::getWarning).collect(Collectors.toList())));

    }

    public static CppCheck cppCheckNil(){
        return new CppCheck(0L,"",0L,0L,0L,0L,0L);
    }
}
