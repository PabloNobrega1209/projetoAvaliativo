package br.com.projetoavaliativo.todo.controller;

import br.com.projetoavaliativo.todo.dto.TaskRequest;
import br.com.projetoavaliativo.todo.model.Task;
import br.com.projetoavaliativo.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) { this.service = service; }

    @GetMapping
    public List<Task> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Task buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task criar(@Valid @RequestBody TaskRequest request) { return service.criar(request); }

    @PutMapping("/{id}")
    public Task atualizar(@PathVariable Long id, @Valid @RequestBody TaskRequest request) { return service.atualizar(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { service.excluir(id); }
}
