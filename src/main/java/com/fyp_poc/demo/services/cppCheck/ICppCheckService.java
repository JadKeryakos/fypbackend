package com.fyp_poc.demo.services.cppCheck;

import com.fyp_poc.demo.DTO.CppCheck;

import java.util.UUID;

public interface ICppCheckService {


    CppCheck addCheck(CppCheck cppCheck);

    Iterable<CppCheck> findAllChecks();

    CppCheck findCheckById(UUID cppCheckId) throws Exception;
}
