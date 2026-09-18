package com.example.demo.dto;

import com.example.demo.entity.StatusTarefa;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Representação para o usuario da tarefa
 * @param id identificador da tarefa
 * @param nome nome da tarefa
 * @param descricao descricao da tarefa
 * @param prioridade priorização da tarefa
 * @param statusTarefa status da tarefa
 */

@Schema (description = "Dados da tarefa que serão retornados")
public record TarefaResponseDTO (
        @Schema (description = "Identificador do produto", example = "1")
        Long id,
        @Schema (description = "Nome da tarefa", example = "Apertar parafusos")
        String nome,
        @Schema (description = "Descrição da tarefa", example = "maquina vibrando 3 parafusos soltos")
        String descricao,
        @Schema (description = "Gravidade da tarefa", example = "Muito prioritaria")
        String prioridade,
        @Schema (description = "Status da tarefa", example = "CONCLUIDO")
        StatusTarefa statusTarefa
) {
}
