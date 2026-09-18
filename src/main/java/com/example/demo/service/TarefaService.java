package com.example.demo.service;

import com.example.demo.dto.TarefaRequestDTO;
import com.example.demo.dto.TarefaResponseDTO;
import com.example.demo.entity.Tarefa;
import com.example.demo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsavel pelas regras de negócios relacionado ao gereciamendo da tarefas
 */

@Service
public class TarefaService {


    public TarefaService(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    @Autowired
    static TarefaRepository tarefaRepository;

    /**
     * metodo de criar
     * @param tarefaRequestDTO
     * @return {@link TarefaResponseDTO } criada
     */
    public TarefaResponseDTO criar(TarefaRequestDTO tarefaRequestDTO){
        return tarefaRepository.salvar(toEntityCreate(tarefaRequestDTO));
    }

    /**
     * metodo de listar
     * @return todos {@link TarefaResponseDTO }
     */
    public List<TarefaResponseDTO> listar(){
        return tarefaRepository.listar();
    }

    /**
     * metodo de listar Pendentes
     * @return {@link TarefaResponseDTO } listarPendentes
     */

    public List<TarefaResponseDTO> listarPendentes() {
        return tarefaRepository.listarPendentes();
    }

    /**
     * metodo de Listar Concluidas
     * @return {@link TarefaResponseDTO } Concluidas
     */

    public List<TarefaResponseDTO> listarConcluidas() {
        return tarefaRepository.listarConcluidas();
    }

    /**
     * metodo de buscar por id
     * @param id
     * @return {@link TarefaResponseDTO } pelo id
     */

    public TarefaResponseDTO buscarPorId(Long id){
        return tarefaRepository.buscarPorId(id);
    }

    /**
     * metodo de atualizar
     * @param tarefaRequestDTO
     * @param id
     * @return {@link TarefaResponseDTO } na tarefa de atualizar
     */

    public TarefaResponseDTO atualizar(TarefaRequestDTO tarefaRequestDTO, Long id){
        return tarefaRepository.atualizar(tarefaRequestDTO, id);
    }

    /**
     * metodo de atualizar parcial
     * @param tarefaRequestDTO
     * @param id
     * @return {@link TarefaResponseDTO } na tarefa de atualizar parcial
     */

    public TarefaResponseDTO atualizarParcial(TarefaRequestDTO tarefaRequestDTO, Long id){
        return tarefaRepository.atualizarParcial(tarefaRequestDTO, id);
    }

    /**
     * metode de atualizar status concluido
     * @param id
     * @return {@link TarefaResponseDTO } na tarefa de atualizar status concluido
     */

    public TarefaResponseDTO atualizarStatusConcluido(Long id) {
        return tarefaRepository.atualizarStatusConcluido(id);
    }

    /**
     * metodo de deletar
     * @param id
     */

    public void deletar(Long id){
        tarefaRepository.deletar(id);
    }

    /**
     * mapeia a {@link TarefaRequestDTO } para {@link Tarefa}
     * @param tarefaRequestDTO
     * @return {@link Tarefa} com os dados da tarefa passada pelo parametro
     */
    public Tarefa toEntityCreate(TarefaRequestDTO tarefaRequestDTO){
        return new Tarefa(tarefaRequestDTO.nome(), tarefaRequestDTO.descricao(), tarefaRequestDTO.prioridade());
    }



}
