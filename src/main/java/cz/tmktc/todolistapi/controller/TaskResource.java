package cz.tmktc.todolistapi.controller;


import cz.tmktc.todolistapi.model.Task;
import cz.tmktc.todolistapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controller class - handles requests that come from the client
 */
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskResource {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(taskService.getTask(id));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.created(URI.create("/tasks/userID")).body(taskService.createTask(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(value = "id") String id) {
        boolean successfulDeletion = taskService.deleteTask(id);
        if (!successfulDeletion) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
