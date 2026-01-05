package br.com.sadock.tasktracker.commands;

import br.com.sadock.tasktracker.model.Task;
import br.com.sadock.tasktracker.repository.TaskRepository;
import br.com.sadock.tasktracker.status.TaskStatus;

public class MarkDoneCommand implements Command {
	
	private final TaskRepository repository;
	
	public MarkDoneCommand(TaskRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(String[] args) {
		if (args.length < 2) {
			System.out.println("Uso: task-cli mark-done <id>");
			return;
		}
		
		Task task = repository.findById(Integer.parseInt(args[1]));
		
		repository.changeStatus(task.getId(), TaskStatus.DONE);
		task.setUpdatedAt(java.time.LocalDateTime.now());
	}
	
	

}
