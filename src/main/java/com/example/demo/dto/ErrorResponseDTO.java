package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Exposição do erro de forma arrumada
 * @param codigo do erro
 * @param erro Nome do erro
 * @param mensagem mensagem jogada
 * @param URI onde foi a requisição do erro
 */

@Schema (description = "Expõe o erro de forma arrumada")
public record ErrorResponseDTO(
        @Schema(description = "codigo do erro", example = "201")
        int codigo,
        @Schema (description = "Nome do erro", example = "Entidade nao encontrada")
        String erro,
        @Schema(description = "Mensagem jogada", example = "Tarefa nao encontrada pelo id x")
        String mensagem,
        @Schema(description = "Onde foi o endpoint chamado que gerou erro",example = "api/v1/tarefas")
        String URI
) {
}
