package com.fil.rouge.controller;

import com.fil.rouge.dto.TaskDto;
import com.fil.rouge.emuns.Categories;
import com.fil.rouge.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TaskController {

    private final TaskService taskService;

//    @PostMapping
//    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
//        TaskDto createdTask = taskService.createTask(taskDto);
//        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
//    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") long id) {
        TaskDto taskDto = taskService.getTaskById(id);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
//        TaskDto updatedTask = taskService.upadteTask(id, taskDto);
//        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
//    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskDto>> searchTasks(@RequestParam String keyword) {
        List<TaskDto> tasks = taskService.searchTasks(keyword);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/all")
    //@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<TaskDto>> getTasks() {
        List<TaskDto> tasks = taskService.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<TaskDto>> getPendingTasks() {
        List<TaskDto> tasks = taskService.getPendingTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/accepted")
    // @PreAuthorize("hasRole('CLIENT')")

    public ResponseEntity<List<TaskDto>> getAcceptedTasks() {
        List<TaskDto> tasks = taskService.getAcceptedTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<TaskDto> acceptTask(@PathVariable("id") Long id) {
        TaskDto acceptedTask = taskService.acceptTask(id);
        return new ResponseEntity<>(acceptedTask, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    //@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<TaskDto>> getTasksByCategory(@PathVariable("category") Categories category) {
        List<TaskDto> tasks = taskService.getTasksByCategory(category);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/submit-work")
    public ResponseEntity<TaskDto> submitWork(@PathVariable Long taskId) {
        TaskDto updatedTask = taskService.submitWork(taskId);
        return ResponseEntity.ok(updatedTask);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestPart("task") TaskDto taskDto,
                                              @RequestPart(value = "taskImageFile", required = false) MultipartFile taskImageFile) {
        if (taskImageFile != null) {
            taskDto.setTaskImageFile(taskImageFile);
        }
        TaskDto createdTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Endpoint to update a task
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id,
                                              @RequestPart("task") TaskDto taskDto,
                                              @RequestPart(value = "taskImageFile", required = false) MultipartFile taskImageFile) {
        if (taskImageFile != null) {
            taskDto.setTaskImageFile(taskImageFile);
        }
        TaskDto updatedTask = taskService.updateTask(id, taskDto);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    //@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<byte[]> getTaskImage(@PathVariable Long id) {
        byte[] image = taskService.getTaskImageById(id);
        return ResponseEntity.ok(image);
    }


    //Dashboard Methods

    @GetMapping("/worker")
    public ResponseEntity<List<TaskDto>> getTasksByWorker() {
        List<TaskDto> tasks = taskService.getTasksByWorkerId();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/client")
    public ResponseEntity<List<TaskDto>> getTasksByClient() {
        List<TaskDto> tasks = taskService.getTasksByClientId();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/count/status/accepted")
    public ResponseEntity<Long> countTasksAccepted() {
        Long count = taskService.countTasksAccepted();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/status/pending")
    public ResponseEntity<Long> countTasksPending() {
        Long count = taskService.countTasksPending();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/status/inProgress")
    public ResponseEntity<Long> countTasksInProgress() {
        Long count = taskService.countTasksInProgress();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/status/rejected")
    public ResponseEntity<Long> countTasksRejected() {
        Long count = taskService.countTasksRejected();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/status/completed")
    public ResponseEntity<Long> countTasksCompleted() {
        Long count = taskService.countTasksCompleted();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/client/count/status/accepted")
    public ResponseEntity<Long> countTasksAcceptedByClient() {
        Long count = taskService.countTasksAcceptedByClient();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/client/count/status/pending")
    public ResponseEntity<Long> countTasksPendingByClient() {
        Long count = taskService.countTasksPendingByClient();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/client/count/status/rejected")
    public ResponseEntity<Long> countTasksRejectedByClient() {
        Long count = taskService.countTasksRejectedByClient();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/client/count/status/inProgress")
    public ResponseEntity<Long> countTasksInProgressByClient() {
        Long count = taskService.countTasksInProgressByClient();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/client/count/status/completed")
    public ResponseEntity<Long> countTasksCompletedByClient() {
        Long count = taskService.countTasksCompletedByClient();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllTasks() {
        Long count = taskService.countAllTasks();
        return ResponseEntity.ok(count);
    }
}
