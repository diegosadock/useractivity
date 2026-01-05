package br.com.sadock.tasktracker.commands;

import br.com.sadock.tasktracker.model.Task;
import br.com.sadock.tasktracker.repository.TaskRepository;
import br.com.sadock.tasktracker.status.TaskStatus;

public class MarkInProgressCommand implements Command {
	
	private final TaskRepository repository;
	
	public MarkInProgressCommand(TaskRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute(String[] args) {
		if (args.length < 2) {
			System.out.println("Uso: task-cli mark-in-progress <id>");
			return;
		}
		
		Task task = repository.findById(Integer.parseInt(args[1]));
		
		repository.changeStatus(task.getId(), TaskStatus.IN_PROGRESS);
		task.setUpdatedAt(java.time.LocalDateTime.now());
	}

}
