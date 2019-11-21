package de.phib.tasket.itemlist.item.task;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for CRUD operations on the task database.
 */
public interface TaskRepository extends CrudRepository<Task, String> {

}
