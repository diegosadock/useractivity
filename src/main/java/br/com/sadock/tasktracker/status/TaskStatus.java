package br.com.sadock.tasktracker.status;

public enum TaskStatus {
	TODO,
	IN_PROGRESS,
	DONE;
	
	public static TaskStatus fromCli(String value) {
        return switch (value.toLowerCase()) {
            case "todo" -> TODO;
            case "in-progress" -> IN_PROGRESS;
            case "done" -> DONE;
            default -> throw new IllegalArgumentException("Status inválido: " + value);
        };
    }

}
