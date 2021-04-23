package com.fyp_poc.demo.controllers;

import com.fyp_poc.demo.DTO.CppCheckTest;
import com.fyp_poc.demo.repositories.CppCheckRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cppcheck")
public class CppCheckController {

    private final CppCheckRepository cppCheckRepository;

    public CppCheckController(CppCheckRepository cppCheckRepository) {
        this.cppCheckRepository = cppCheckRepository;
    }

    @PostMapping("")
    ResponseEntity<CppCheckTest> addCheck(@RequestBody CppCheckTest cppCheck){
        cppCheckRepository.save(cppCheck);
        return ResponseEntity.ok(cppCheck);
    }

    @GetMapping("")
    ResponseEntity<Iterable<CppCheckTest>> getCheck(){
        return ResponseEntity.ok(cppCheckRepository.findAll());
    }


}
