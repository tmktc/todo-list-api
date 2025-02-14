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

    /**
     * Returns all tasks.
     *
     * @return all tasks
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    /**
     * Returns a task.
     *
     * @param id id of a task
     * @return task whose id matches the parameter
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(taskService.getTask(id));
    }

    /**
     * Creates a task.
     * If ID is included in the request, it updates the given task instead.
     *
     * @param task to be created or updated
     * @return created or updated task
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.created(URI.create("/tasks/userID")).body(taskService.createTask(task));
    }

    /**
     * Deletes a task.
     *
     * @param id id of the task that is to be deleted
     * @return 204 noContent if the deletion is successful, 404 notFound if the deletion failed
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(value = "id") String id) {
        boolean successfulDeletion = taskService.deleteTask(id);
        if (!successfulDeletion) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
