package com.example.demo.dto;

import com.example.demo.entity.StatusTarefa;

public record TarefaResponseDTO (
        Long id,
        String nome,
        String descricao,
        String prioridade,
        StatusTarefa statusTarefa
) {
}
