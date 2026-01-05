package br.com.sadock.tasktracker.commands;

import java.util.HashMap;
import java.util.Map;

import br.com.sadock.tasktracker.repository.TaskRepository;

public class CommandRegistry {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry(TaskRepository repository) {
        commands.put("add", new AddCommand(repository));
        commands.put("list", new ListCommand(repository));
        commands.put("delete", new DeleteCommand(repository));
        commands.put("update", new UpdateCommand(repository));
        commands.put("mark-in-progress", new MarkInProgressCommand(repository));
        commands.put("mark-done", new MarkDoneCommand(repository));
    }

    public Command get(String name) {
        return commands.get(name);
    }
}
