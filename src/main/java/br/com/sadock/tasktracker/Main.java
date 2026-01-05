package br.com.sadock.tasktracker;

import br.com.sadock.tasktracker.commands.Command;
import br.com.sadock.tasktracker.commands.CommandRegistry;
import br.com.sadock.tasktracker.repository.JsonTaskRepository;
import br.com.sadock.tasktracker.repository.TaskRepository;

public class Main {

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Use: task-cli <command> [args]");
			return;
		}

		TaskRepository repository = new JsonTaskRepository();
		CommandRegistry registry = new CommandRegistry(repository);

		Command command = registry.get(args[0]);

		if (command == null) {
			System.out.println("Comando desconhecido: " + args[0]);
			return;
		}

		command.execute(args);
	}
}
