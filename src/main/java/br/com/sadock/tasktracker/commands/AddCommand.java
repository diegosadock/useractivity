package br.com.sadock.tasktracker.commands;

import java.time.LocalDateTime;

import br.com.sadock.tasktracker.model.Task;
import br.com.sadock.tasktracker.repository.TaskRepository;
import br.com.sadock.tasktracker.status.TaskStatus;

public class AddCommand implements Command {

	private final TaskRepository repository;

	public AddCommand(TaskRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute(String[] args) {
		if (args.length < 2) {
			System.out.println("Uso: task-cli add \"descrição\"");
			return;
		}

		String descricao = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));

		Task tarefa = new Task();
		tarefa.setDescription(descricao);
		tarefa.setStatus(TaskStatus.TODO);
		tarefa.setCreatedAt(LocalDateTime.now());

		repository.save(tarefa);

		System.out.println("Task added successfully (ID: " + tarefa.getId() + ")");
	}
	
	
}
