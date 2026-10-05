package br.com.projetoavaliativo.todo.controller;

import br.com.projetoavaliativo.todo.model.Task;
import br.com.projetoavaliativo.todo.model.TaskStatus;
import br.com.projetoavaliativo.todo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired MockMvc mvc;
    @MockBean TaskService service;

    @Test
    void criarTarefaValidaEntradaERetorna201() throws Exception {
        Task tarefa = new Task();
        tarefa.setId(1L);
        tarefa.setNome("Estudar");
        tarefa.setDescricao("Spring");
        tarefa.setStatus(TaskStatus.PENDENTE);
        when(service.criar(any())).thenReturn(tarefa);

        mvc.perform(post("/api/tarefas").contentType(MediaType.APPLICATION_JSON)
                        .content("""{"nome":"Estudar","descricao":"Spring"}"""))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("PENDENTE"));
    }

    @Test
    void criarTarefaSemNomeRetorna400() throws Exception {
        mvc.perform(post("/api/tarefas").contentType(MediaType.APPLICATION_JSON)
                        .content("""{"nome":" ","descricao":"Spring"}"""))
                .andExpect(status().isBadRequest());
    }
}
