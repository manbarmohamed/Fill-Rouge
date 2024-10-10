package com.fil.rouge.controller;

import com.fil.rouge.dto.WorkerDto;
import com.fil.rouge.model.Worker;
import com.fil.rouge.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/worker")
@CrossOrigin("*")
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/allWorkers")
    public List<WorkerDto> getAllWorkers() {
        return workerService.findAllWorker();
    }

    @GetMapping("/search")
    public List<WorkerDto> getWorkerByName(@RequestParam("name") String name) {
        return workerService.findWorkerByName(name);
    }

    @GetMapping("/loggedWorker")
    public WorkerDto getWorkerLogged() {
        return workerService.getWorkerLoggedInfo();
    }
}
