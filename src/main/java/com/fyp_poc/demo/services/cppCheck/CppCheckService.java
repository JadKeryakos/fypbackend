package com.fyp_poc.demo.services.cppCheck;

import com.fyp_poc.demo.DTO.CppCheck;
import com.fyp_poc.demo.repositories.CppCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CppCheckService implements ICppCheckService {


    private CppCheckRepository cppCheckRepository;

    @Autowired
    public CppCheckService(CppCheckRepository cppCheckRepository) {
        this.cppCheckRepository = cppCheckRepository;
    }


    @Override
    public CppCheck addCheck(CppCheck cppCheck) {
        UUID uuid = UUID.randomUUID();
        cppCheck.setId(uuid);
        return cppCheckRepository.save(cppCheck);
    }

    @Override
    public List<CppCheck> findAllChecks() {
        return cppCheckRepository.findAll();
    }

    @Override
    public CppCheck findCheckById(UUID cppCheckId) throws Exception {
        Optional<CppCheck> cppCheck = cppCheckRepository.findById(cppCheckId);
        if(cppCheck.isPresent()){
            return cppCheck.get();
        }else {
            // TODO: Create Your own Exception
            throw new Exception("Cpp Check not Found");
        }

    }
}
