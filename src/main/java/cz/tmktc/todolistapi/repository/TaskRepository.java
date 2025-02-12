package cz.tmktc.todolistapi.repository;

import cz.tmktc.todolistapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA Repository interface - communicates with the database
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    /**
     * Searches the database for a task with given ID and returns it if it exists.
     *
     * @param id task ID
     * @return task
     */
    Optional<Task> findById(String id);
}
