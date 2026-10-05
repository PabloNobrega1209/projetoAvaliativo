package br.com.projetoavaliativo.todo.service;

import br.com.projetoavaliativo.todo.dto.TaskRequest;
import br.com.projetoavaliativo.todo.model.Task;
import br.com.projetoavaliativo.todo.model.TaskStatus;
import br.com.projetoavaliativo.todo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock TaskRepository repository;
    @InjectMocks TaskService service;

    @Test
    void criarDefineStatusPendenteQuandoNaoInformado() {
        when(repository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task criada = service.criar(new TaskRequest(" Ler ", " Livro ", null, null));

        assertEquals("Ler", criada.getNome());
        assertEquals("Livro", criada.getDescricao());
        assertEquals(TaskStatus.PENDENTE, criada.getStatus());
        verify(repository).save(any(Task.class));
    }

    @Test
    void atualizarTarefaInexistenteRetorna404() {
        when(repository.findById(42L)).thenReturn(Optional.empty());
        var erro = assertThrows(org.springframework.web.server.ResponseStatusException.class,
                () -> service.atualizar(42L, new TaskRequest("Nome", "Descrição", null, null)));
        assertEquals(404, erro.getStatusCode().value());
        verify(repository, never()).save(any());
    }
}
