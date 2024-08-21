package com.fil.rouge.service;


import com.fil.rouge.dto.TaskDto;
import com.fil.rouge.emuns.TaskStatus;
import com.fil.rouge.exception.TaskNotFoundException;
import com.fil.rouge.mapper.TaskMapper;
import com.fil.rouge.model.Task;
import com.fil.rouge.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskDto createTask(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        task.setStatus(TaskStatus.PENDING);
        task = taskRepository.save(task);
        return taskMapper.toDto(task);
    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public TaskDto upadteTask(Long id ,TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException("Task not found"));
        taskMapper.partialUpdate(taskDto,task);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDto(updatedTask);
    }

    public List<TaskDto> searchTasks(String keyword) {
        List<Task> tasks = taskRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
        return tasks.stream().map(taskMapper::toDto).toList();
    }

    public List<TaskDto> getTasks() {
        return taskRepository.findAll().stream().map(taskMapper::toDto).toList();
    }

    public List<TaskDto> getPendingTasks() {
        return taskMapper.toDto(taskRepository.findByStatus(TaskStatus.PENDING));
    }

    public List<TaskDto> getAcceptedTasks() {
        return taskMapper.toDto(taskRepository.findByStatus(TaskStatus.ACCEPTED));
    }

    public TaskDto acceptTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        task.setStatus(TaskStatus.ACCEPTED);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDto(updatedTask);
    }

}
