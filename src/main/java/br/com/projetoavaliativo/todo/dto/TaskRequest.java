package br.com.projetoavaliativo.todo.dto;

import br.com.projetoavaliativo.todo.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequest(
        @NotBlank @Size(max = 120) String nome,
        @NotBlank @Size(max = 2000) String descricao,
        TaskStatus status,
        @Size(max = 2000) String observacoes
) { }
