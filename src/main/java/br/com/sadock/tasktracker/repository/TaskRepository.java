package br.com.sadock.tasktracker.repository;

import java.util.List;

import br.com.sadock.tasktracker.model.Task;
import br.com.sadock.tasktracker.status.TaskStatus;

public interface TaskRepository {

	void save(Task task);
	
	void update(Task task);

	List<Task> findAll();

	Task findById(int id);

	boolean deleteById(int id);
	
	void changeStatus(int id, TaskStatus status);
}
