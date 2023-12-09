package com.yena.webyena.services;

import com.yena.webyena.entities.Works;
import com.yena.webyena.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;

    //GET ALL WORKS
    public List<Works> getWorks(){return workRepository.findAll();}

    //GET WORKS BY ID
    public Optional<Works> getWorksById(Integer workId){
        return workRepository.findById(workId);
    }

    //POST WORK
    public Works createWork(Works works){
        return workRepository.saveAndFlush(works);
    }

    //DELETE WORK BY ID
    public void deleteWorkById(Integer workId){
        workRepository.deleteById(workId);
    }
}
