package com.fyp_poc.demo.controllers.cppCheck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CppCheckBuildNamesRequest {
    List<String> buildNames;
}
