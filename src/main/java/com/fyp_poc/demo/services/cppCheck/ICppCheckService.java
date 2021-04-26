package com.fyp_poc.demo.services.cppCheck;

import com.fyp_poc.demo.DTO.CppCheck;

import java.util.List;
import java.util.UUID;

public interface ICppCheckService {


    CppCheck addCheck(CppCheck cppCheck);

    List<CppCheck> findAllChecks();

    CppCheck findCppCheck(UUID cppCheckId) throws Exception;
}
