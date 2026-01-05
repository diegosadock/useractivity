package br.com.sadock.tasktracker.commands;

import br.com.sadock.tasktracker.repository.TaskRepository;

public class DeleteCommand implements Command {
	
	private final TaskRepository repository;
	
	public DeleteCommand(TaskRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute(String[] args) {
		if (args.length < 2) {
			System.out.println("Uso: task-cli delete <id>");
			return;
		}

		try {
			int id = Integer.parseInt(args[1]);
			boolean deleted = repository.deleteById(id);
			if (deleted) {
				System.out.println("Task with ID " + id + " deleted successfully.");
			} else {
				System.out.println("Task with ID " + id + " not found.");
			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido: " + args[1]);
		}
	}

}
