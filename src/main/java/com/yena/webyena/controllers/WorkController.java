package com.yena.webyena.controllers;


import com.yena.webyena.customExceptions.ApplicationException;
import com.yena.webyena.entities.Works;
import com.yena.webyena.services.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;


    @GetMapping("/works")
    public ResponseEntity<?>getAllWorks(){
        var works = workService.getWorks();
        if(works == null){
            throw new ApplicationException(
                    "Works fetch process failed!",
                    String.format("Works fetch process failed!"),
                    HttpStatus.BAD_REQUEST
            );
        }
        return ResponseEntity.ok(works);
    }


    @PostMapping("/works")
    public Works createWork(@RequestBody Works works){
        var work = workService.createWork(works);
        if(work == null){
            throw new ApplicationException(
                    "Work creation failed",
                    String.format("Work creation failed"),
                    HttpStatus.BAD_REQUEST);
        }
        return work;
    }


    @GetMapping("/works/{workId}")
    public ResponseEntity<?> getWorkById(@PathVariable Integer workId){
        var work = workService.getWorksById(workId);
        if(work == null){
            throw new ApplicationException("Fetching work by id failed",
                    String.format("Fetching work by id failed"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(work);
    }

    @DeleteMapping("/works/{workId}")
    public void deleteWorkById(@PathVariable Integer workId){
        workService.deleteWorkById(workId);
    }
}
