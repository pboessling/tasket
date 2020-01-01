package de.phib.tasket.data.item.task;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for CRUD operations on the task database table.
 */
public interface TaskRepository extends CrudRepository<Task, String> {

}
