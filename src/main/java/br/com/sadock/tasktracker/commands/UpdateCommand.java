package br.com.sadock.tasktracker.commands;

import java.time.LocalDateTime;
import java.util.Arrays;

import br.com.sadock.tasktracker.model.Task;
import br.com.sadock.tasktracker.repository.TaskRepository;

public class UpdateCommand implements Command {

	private final TaskRepository repository;

	public UpdateCommand(TaskRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute(String[] args) {

		if (args.length < 3) {
			System.out.println("Uso: task-cli update <id> <new description>");
			return;
		}

		try {
			int id = Integer.parseInt(args[1]);
			String newDescription = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

			Task task = repository.findById(id);

			if (task == null) {
				System.out.println("Task with ID " + id + " not found.");
				return;
			}

			task.setDescription(newDescription);
			task.setUpdatedAt(LocalDateTime.now());
			repository.update(task);

			System.out.println("Task with ID " + id + " updated successfully.");

		} catch (NumberFormatException e) {
			System.out.println("ID inválido: " + args[1]);
		}
	}

}
