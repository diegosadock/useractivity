package br.com.sadock.tasktracker.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.sadock.tasktracker.model.Task;
import br.com.sadock.tasktracker.status.TaskStatus;

public class JsonTaskRepository implements TaskRepository {

    private static final File FILE = new File("tasks.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Task> tarefas = new ArrayList<>();

    public JsonTaskRepository() {
    	mapper.registerModule(new JavaTimeModule());
        load();
    }

    private void load() {
        try {
            if (FILE.exists()) {
                tarefas = mapper.readValue(FILE, new TypeReference<>() {});
            }
        } catch (Exception e) {
            tarefas = new ArrayList<>();
        }
    }

    private void persist() {
        try {
            mapper.writeValue(FILE, tarefas);
        } catch (Exception ignored) {}
    }

    @Override
    public void save(Task tarefa) {
        tarefa.setId(tarefas.size() + 1);
        tarefas.add(tarefa);
        persist();
    }

    @Override
    public List<Task> findAll() {
        return tarefas;
    }

    @Override
    public boolean deleteById(int id) {
    	if (findById(id) != null) {
        tarefas.removeIf(t -> t.getId() == id);
        persist();
        return true;
    	}
    	return false;
    }

    @Override
    public Task findById(int id) {
        return tarefas.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

	@Override
	public void update(Task updatedTask) {
		
		Task existingTask = findById(updatedTask.getId());
		
		if (existingTask != null) {
	        existingTask.setDescription(updatedTask.getDescription());
	        existingTask.setStatus(updatedTask.getStatus());
	        persist();
	    }
		
	}

	@Override
	public void changeStatus(int id, TaskStatus status) {
		
		Task existingTask = findById(id);
		
		if (existingTask != null) {
	        existingTask.setStatus(status);
	        persist();
	    }
		
	}
}
