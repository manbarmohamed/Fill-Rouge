package com.fil.rouge.controller;

import com.fil.rouge.dto.TaskDto;
import com.fil.rouge.emuns.Categories;
import com.fil.rouge.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.upadteTask(id, taskDto);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

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
    public ResponseEntity<List<TaskDto>> getAcceptedTasks() {
        List<TaskDto> tasks = taskService.getAcceptedTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<TaskDto> acceptTask(@PathVariable Long id) {
        TaskDto acceptedTask = taskService.acceptTask(id);
        return new ResponseEntity<>(acceptedTask, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<TaskDto>> getTasksByCategory(@PathVariable Categories category) {
        List<TaskDto> tasks = taskService.getTasksByCategory(category);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/submit-work")
    public ResponseEntity<TaskDto> submitWork(@PathVariable Long taskId) {
        TaskDto updatedTask = taskService.submitWork(taskId);
        return ResponseEntity.ok(updatedTask);
    }
}
