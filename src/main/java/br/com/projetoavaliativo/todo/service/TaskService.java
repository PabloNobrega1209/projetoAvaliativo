package br.com.projetoavaliativo.todo.service;

import br.com.projetoavaliativo.todo.dto.TaskRequest;
import br.com.projetoavaliativo.todo.model.Task;
import br.com.projetoavaliativo.todo.model.TaskStatus;
import br.com.projetoavaliativo.todo.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) { this.repository = repository; }

    public List<Task> listar() { return repository.findAll(); }

    public Task buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    public Task criar(TaskRequest request) {
        Task task = new Task();
        aplicar(task, request);
        return repository.save(task);
    }

    public Task atualizar(Long id, TaskRequest request) {
        Task task = buscar(id);
        aplicar(task, request);
        return repository.save(task);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        repository.deleteById(id);
    }

    private void aplicar(Task task, TaskRequest request) {
        task.setNome(request.nome().trim());
        task.setDescricao(request.descricao().trim());
        task.setStatus(request.status() == null ? TaskStatus.PENDENTE : request.status());
        task.setObservacoes(request.observacoes() == null ? null : request.observacoes().trim());
    }
}
