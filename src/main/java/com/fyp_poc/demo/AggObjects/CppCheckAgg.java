package com.fyp_poc.demo.AggObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CppCheckAgg {

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

    public CppCheckAgg cppCheckReduceSingle(CppCheckAgg baseReduce, BiFunction<Double,Double,Double> lambda){
        return new CppCheckAgg(
                lambda.apply(error,baseReduce.getError()),
                lambda.apply(performance,baseReduce.getPerformance()),
                lambda.apply(portability, baseReduce.getPortability()),
                lambda.apply(style,baseReduce.getStyle()),
                lambda.apply(warning,baseReduce.getWarning()));
    }

    public static CppCheckAgg cppCheckReduceList(List<CppCheckAgg> cppCheckAggList, Function<List<Double>,Double> lambda){
        return new CppCheckAgg(
                lambda.apply(cppCheckAggList.stream().map(CppCheckAgg::getError).collect(Collectors.toList())),
                lambda.apply(cppCheckAggList.stream().map(CppCheckAgg::getPerformance).collect(Collectors.toList())),
                lambda.apply(cppCheckAggList.stream().map(CppCheckAgg::getPortability).collect(Collectors.toList())),
                lambda.apply(cppCheckAggList.stream().map(CppCheckAgg::getStyle).collect(Collectors.toList())),
                lambda.apply(cppCheckAggList.stream().map(CppCheckAgg::getWarning).collect(Collectors.toList())));

    }

    public static CppCheckAgg cppCheckNil(){
        return new CppCheckAgg(0L,0L,0L,0L,0L);
    }

}
