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

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(String id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public boolean deleteTask(String id) {
        try {
            taskRepository.delete(getTask(id));
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
