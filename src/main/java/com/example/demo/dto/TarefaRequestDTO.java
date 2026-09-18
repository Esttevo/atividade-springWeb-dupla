package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Dodos necessario para criar tarefas
 * @param nome nome da tarefa
 * @param descricao decricao da tarefe
 *
 * * */

@Schema (description = "Dados utilizados para criar tarefas")

public record TarefaRequestDTO(

        @Schema(
                description = "Nome da tarefa",
                example = "Matematica"
        )
        @NotBlank(message = "nao pode estar em branco")
        @Size (min = 3 , max = 100, message = "O nome deve possuir entre 3 e 100 caracteres")
        String nome,

        @Schema(
                description = "Prioridade da tarefa",
                example = "muito"
        )
        @NotBlank(message = "nao pode estar em branco")
        @Size (min = 3 , max = 100, message = "O nome deve possuir entre 3 e 100 caracteres")
        String prioridade,

        @Schema (
                description = "Descricao da tarega",
                example = "pegar chave x para fazer y por cause de"
        )
        String descricao
) {
}
