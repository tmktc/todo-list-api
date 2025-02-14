package cz.tmktc.todolistapi.service;

import cz.tmktc.todolistapi.model.Task;
import cz.tmktc.todolistapi.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class - business logic
 */
@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    /**
     * Returns all tasks.
     *
     * @return all tasks
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Returns a task.
     *
     * @param id id of a task
     * @return task whose id matches the parameter
     */
    public Task getTask(String id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    /**
     * Creates or updates a task.
     *
     * @param task to be creates or updated
     * @return created or updated task
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Deletes a task.
     *
     * @param id id of the task
     * @return true if the deletion was successful, false if not
     */
    public boolean deleteTask(String id) {
        try {
            taskRepository.delete(getTask(id));
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
