package com.fil.rouge.service;


import com.fil.rouge.dto.TaskDto;
import com.fil.rouge.emuns.Categories;
import com.fil.rouge.emuns.TaskStatus;
import com.fil.rouge.exception.TaskNotFoundException;
import com.fil.rouge.mapper.TaskMapper;
import com.fil.rouge.model.Client;
import com.fil.rouge.model.Task;
import com.fil.rouge.model.Worker;
import com.fil.rouge.repository.ClientRepository;
import com.fil.rouge.repository.TaskRepository;
import com.fil.rouge.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ClientRepository clientRepository;
    private final TaskMapper taskMapper;
    private final WorkerRepository workerRepository;

    public TaskDto createTask(TaskDto taskDto) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientRepository.findByUsername(loggedInUser.getName());
        Task task = taskMapper.toEntity(taskDto);
        task.setStatus(TaskStatus.PENDING);
        task.setClient(client);
        task = taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskMapper.partialUpdate(taskDto, task);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDto(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDto upadteTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskMapper.partialUpdate(taskDto, task);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDto(updatedTask);
    }

    public byte[] getTaskImageById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        return task.getTaskImage();
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

    public List<TaskDto> getTasksByCategory(Categories category) {
        List<Task> tasks = taskRepository.findByCategory(category);
        return tasks.stream().map(taskMapper::toDto).toList();
    }

    public TaskDto submitWork(Long taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        task.setStatus(TaskStatus.COMPLETED);

        Task updatedTask = taskRepository.save(task);

        return taskMapper.toDto(updatedTask);
    }

    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        return taskMapper.toDto(task);
    }

    //Dashboard Methods

    public List<TaskDto> getTasksByWorkerId() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Worker worker = workerRepository.findByUsername(loggedInUser.getName());
        List<Task> tasks = taskRepository.findTasksByWorkerId(worker.getId());
        return taskMapper.toDto(tasks);
    }

    public List<TaskDto> getTasksByClientId() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientRepository.findByUsername(loggedInUser.getName());
        List<Task> tasks = taskRepository.findTasksByClientId(client.getId());
        return taskMapper.toDto(tasks);
    }

    public Long countTasksAccepted() {
        return taskRepository.countTasksByStatus(TaskStatus.ACCEPTED);
    }

    public Long countTasksPending() {
        return taskRepository.countTasksByStatus(TaskStatus.PENDING);
    }

    public Long countTasksInProgress() {
        return taskRepository.countTasksByStatus(TaskStatus.IN_PROGRESS);
    }

    public Long countTasksRejected() {
        return taskRepository.countTasksByStatus(TaskStatus.REJECTED);
    }

    public Long countTasksCompleted() {
        return taskRepository.countTasksByStatus(TaskStatus.COMPLETED);
    }

    public Long countAllTasks() {
        return taskRepository.countAllTasks();
    }


    public Long countTasksAcceptedByClient() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientRepository.findByUsername(loggedInUser.getName());
        return taskRepository.countTasksByClientAndStatus(client.getId(), TaskStatus.ACCEPTED);
    }

    public Long countTasksPendingByClient() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientRepository.findByUsername(loggedInUser.getName());
        return taskRepository.countTasksByClientAndStatus(client.getId(), TaskStatus.PENDING);
    }

    public Long countTasksRejectedByClient() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientRepository.findByUsername(loggedInUser.getName());
        return taskRepository.countTasksByClientAndStatus(client.getId(), TaskStatus.REJECTED);
    }

    public Long countTasksInProgressByClient() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientRepository.findByUsername(loggedInUser.getName());
        return taskRepository.countTasksByClientAndStatus(client.getId(), TaskStatus.IN_PROGRESS);
    }

    public Long countTasksCompletedByClient() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientRepository.findByUsername(loggedInUser.getName());
        return taskRepository.countTasksByClientAndStatus(client.getId(), TaskStatus.COMPLETED);
    }


}

