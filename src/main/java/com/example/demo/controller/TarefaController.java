package com.example.demo.controller;

import com.example.demo.dto.TarefaRequestDTO;
import com.example.demo.dto.TarefaResponseDTO;
import com.example.demo.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/tarefas")
@RestController

/**
 * Controller REST responsavel pelos endpoints relacionados ao recurso tarefa
 *
 */

@Tag(
        name ="Tarefa",
        description = "Operação relacionado Gerenciamento de Tarefas"
)

public class TarefaController {

    public TarefaController(TarefaService tarefaService) {
        this.service = tarefaService;
    }

    @Autowired
    private TarefaService service;

    /**
     * Metodo de Criar
     * @param {@link TarefaRequestDTO}
     * @return Criar Tarefa
     */


    @Operation(
            summary = "Criar Tarefas",
            description =  "Retorna Tarefa criada"
    )

    @ApiResponses({

            @ApiResponse(
                    responseCode = "201",
                    description = "Tarefa criado com sucesso!"

            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao cadastrar"
            )
    })



    @PostMapping("")
    public ResponseEntity<TarefaResponseDTO> criar(@RequestBody @Valid TarefaRequestDTO tarefaRequestDTO) {
        TarefaResponseDTO tarefaResponseDTO = service.criar(tarefaRequestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(tarefaResponseDTO.id())
                .toUri();

       return ResponseEntity.created(uri).body(tarefaResponseDTO);
    }

    /**
     * Metodo de listar
     * @return lista de {@link TarefaResponseDTO}
     */

    @Operation(
            summary = "Listar tarefas",
            description = "Retorna Listar tarefa"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tarefas listadas sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao listar"
            )
    })


    @GetMapping
    public  ResponseEntity<List<TarefaResponseDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    /**
     * Metodo de listar tarefas pendentes
     * @return lista de {@link TarefaResponseDTO}
     */

    @Operation(
            summary = "Listar tarefas pendentes",
            description = "Retorna Lista de tarefas pendentes"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tarefas listadas sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao listar"
            )
    })

    @GetMapping("/pendentes")
    public ResponseEntity<List<TarefaResponseDTO>> listarPendentes() {
        return ResponseEntity.ok(service.listarPendentes());
    }

    /**
     * Metodo de listar tarefas concluidas
     * @return lista de {@link TarefaResponseDTO}
     */

    @Operation(
            summary = "Listar tarefas pendentes",
            description = "Retorna Lista de tarefas concluidas"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tarefas listadas sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao listar"
            )
    })

    @GetMapping("/concluidas")
    public ResponseEntity<List<TarefaResponseDTO>> listarConcluidas() {
        return ResponseEntity.ok(service.listarConcluidas());
    }

    /**
     * Metodo BuscarPorId
     * @param id
     * @return {@link TarefaResponseDTO}
     */


    @Operation(
            summary = "Buscar por id na tarefa",
            description = "Retorna tarefaResponse pelo id passado"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Id buscado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao buscar por id"
            )
    })

   @GetMapping("/{id}")
   public ResponseEntity<TarefaResponseDTO> buscarPorId (
           @Parameter(description = "identficar unico da tarefa", example = "1")
           @PathVariable Long id){

        return ResponseEntity.ok(service.buscarPorId(id));

    }

    /**
     * Metodo de atualizar
     * @param tarefaRequestDTO
     * @param id
     * @return {@link TarefaResponseDTO} atualizada
     */

    @Operation(
            summary = "atualizar tarefa",
            description = "retorna atualizar"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "tarefa atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao atualizar"
            )
    })

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizar (@RequestBody @Valid TarefaRequestDTO tarefaRequestDTO,
                                                        @Parameter(description = "atualizar uma tarefa", example = "id")
                                                        @PathVariable Long id){
        return ResponseEntity.ok(service.atualizar(tarefaRequestDTO, id));
    }

    /**
     * Atualizar tarefa parcialmente
     * @param tarefaRequestDTO
     * @param id
     * @return {@link TarefaResponseDTO} atualizada
     */
    @PatchMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizarParcial(@RequestBody TarefaRequestDTO tarefaRequestDTO,
                                                              @Parameter(description = "atualizar parcial uma tarefa", example = "id")
                                                              @PathVariable Long id ){

        return ResponseEntity.ok(service.atualizar(tarefaRequestDTO, id));
    }

    /**
     * Atualizar tarefa parcialmente, somente status para concluido
     * @param id
     * @return {@link TarefaResponseDTO} atualizada
     */

    @Operation(
            summary = "atualizar tarefa parcialmente",
            description = "retorna tarefa atualiada"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "tarefa atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao atualizar"
            )
    })

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<TarefaResponseDTO> AtualizarStatusConcluido(
            @Parameter(description = "Id da tarefa que vai ser atualizada")
            @PathVariable Long id)
    {
        return ResponseEntity.ok(service.atualizarStatusConcluido(id));
    }

    /**
     * Metodo de deletar
     * @param id
     * @return {@link TarefaResponseDTO}
     */

    @Operation(
            summary = "deletar tarefa",
            description = "deleta tarefa pelo id"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "deletado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "erro ao deletar"

            )
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (
            @Parameter(description = "deletar tarefa pelo id", example = "1")
            @PathVariable long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();


    }





}
