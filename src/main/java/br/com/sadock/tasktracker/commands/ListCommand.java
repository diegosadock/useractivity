package br.com.sadock.tasktracker.commands;

import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.sadock.tasktracker.model.Task;
import br.com.sadock.tasktracker.repository.TaskRepository;
import br.com.sadock.tasktracker.status.TaskStatus;

public class ListCommand implements Command {

	private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	private final TaskRepository repository;

	public ListCommand(TaskRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute(String[] args) {

		List<Task> tasks = repository.findAll();

		if (tasks.isEmpty()) {
			System.out.println("No tasks found.");
			return;
		}

		// list
		if (args.length == 1) {
			print(tasks);
			return;
		}

		// list <status>
		try {
			TaskStatus status = TaskStatus.fromCli(args[1]);

			List<Task> filtered = tasks.stream().filter(t -> t.getStatus() == status).toList();

			if (filtered.isEmpty()) {
				System.out.println("No tasks with status " + status);
				return;
			}

			print(filtered);

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void print(List<Task> tasks) {
		for (Task t : tasks) {
			System.out.printf("[%d] (%s) %s {createdAt: %s}%n", t.getId(), t.getStatus(), t.getDescription(), t.getCreatedAt().format(FMT));
		}
	}
}
