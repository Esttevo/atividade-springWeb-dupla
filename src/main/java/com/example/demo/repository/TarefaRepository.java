package com.example.demo.repository;

import com.example.demo.dto.TarefaRequestDTO;
import com.example.demo.dto.TarefaResponseDTO;
import com.example.demo.entity.StatusTarefa;
import com.example.demo.entity.Tarefa;
import com.example.demo.exception.TarefaNaoEncontradaException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repisitório responsavel pelo acesso dos dados de tarefas
 */

@Repository
public class TarefaRepository {

    static final List<Tarefa> tarefas = new ArrayList<>();
    static Long id = 0L;

    /**
     * Cadastrar tarefa
     * @param tarefa
     * @return {@link TarefaResponseDTO} criada
     */

    public TarefaResponseDTO salvar(Tarefa tarefa){
        tarefa.setStatusTarefa(StatusTarefa.PENDENTE);
        tarefa.setId(++id);

        tarefas.add(tarefa);

        Tarefa tarefaCriada = tarefas.getLast();
        return toDTO(tarefaCriada);
    }

    /**
     * Listar todas as tarefas
     * @return {@link List<TarefaResponseDTO>} existentes
     */

    public List<TarefaResponseDTO> listar(){
        List<TarefaResponseDTO> tarefa = tarefas.stream().map(tarefa1 -> toDTO(tarefa1)).toList();
        if(tarefa.isEmpty()) throw new TarefaNaoEncontradaException("Nenhuma tarefa existente");
        return tarefa;
    }

    /**
     * Listar tarefas Pendentes
     * @return {@link List<TarefaResponseDTO>} existentes
     */

    public List<TarefaResponseDTO> listarPendentes() {
        List<TarefaResponseDTO> tarefaResponseDTOS = tarefas.stream().filter(tarefa -> tarefa.getStatusTarefa()
                        .equals(StatusTarefa.PENDENTE))
                        .map(tarefa -> toDTO(tarefa)).toList();

        if(tarefaResponseDTOS.isEmpty()) throw new TarefaNaoEncontradaException("Nenhuma tarefa Pendente!");
        return tarefaResponseDTOS;
    }

    /**
     * Listar tarefas Concluidas
     * @return {@link List<TarefaResponseDTO>} existentes
     */

    public List<TarefaResponseDTO> listarConcluidas() {
        List<TarefaResponseDTO> tarefaResponseDTOS = tarefas.stream().filter(tarefa -> tarefa.getStatusTarefa()
                .equals(StatusTarefa.CONCLUIDO))
                .map(tarefa -> toDTO(tarefa)).toList();

        if(tarefaResponseDTOS.isEmpty()) throw new TarefaNaoEncontradaException("Nenhuma tarefa concluida!");
        return tarefaResponseDTOS;
    }

    /**
     * Buscar tarefa pelo ID
     * @param id Da tarefa
     * @return {@link TarefaResponseDTO} com id
     * @throws TarefaNaoEncontradaException caso nao exista tarefa com id passado
     */

    public TarefaResponseDTO buscarPorId (Long id){

        Tarefa tarefa = tarefas.stream().filter(tarefa1 -> tarefa1.getId().equals(id)).findAny().orElseThrow(()-> new TarefaNaoEncontradaException("Tarefa nao encontrada"));
        return toDTO(tarefa);
    }

    /**
     * Atualizar totalmente uma tarefa
     * @param id Da tarefa a ser atualizada
     * @param tarefaRequestDTO com novos atributos
     * @return {@link TarefaResponseDTO} atualizada
     * @throws TarefaNaoEncontradaException caso nao exista tarefa com id passado
     */

    public TarefaResponseDTO atualizar (TarefaRequestDTO tarefaRequestDTO, Long id){

        Tarefa tarefa = tarefas.stream().filter(tarefa1 -> tarefa1.getId().equals(id)).findAny().orElseThrow(() -> new TarefaNaoEncontradaException("Tarefa não encontrada"));

        tarefa.setNome(tarefaRequestDTO.nome());
        tarefa.setDescricao(tarefaRequestDTO.descricao());
        tarefa.setPrioridade(tarefaRequestDTO.prioridade());

        return toDTO(tarefa);
    }

    /**
     * Atualizar uma tarefa Parcialmente /metodo incompleto
     * @param tarefaRequestDTO com novo atributo
     * @param id da tarefa a ser atualizada
     * @return {@link TarefaResponseDTO} ja atualizada
     * @throws TarefaNaoEncontradaException caso nao exista tarefa com id passado
     */

    public TarefaResponseDTO atualizarParcial(TarefaRequestDTO tarefaRequestDTO, Long id){
        Tarefa tarefa = tarefas.stream().filter(tarefa1 -> tarefa1.getId().equals(id)).findAny().orElseThrow(() -> new TarefaNaoEncontradaException("tarefa nao encontrada"));

        //falta logica aqui

        return toDTO(tarefa);
    }


    /**
     * Atualizar Status para Concluido
     * @param id da tarefa a ser atualizada
     * @return {@link TarefaResponseDTO} Atualizada
     * @throws TarefaNaoEncontradaException caso nao exista tarefa com id passado
     */

    public TarefaResponseDTO atualizarStatusConcluido(Long id) {
        Tarefa tarefa = tarefas.stream().filter(tarefa1 -> tarefa1.getId().equals(id)).findAny().orElseThrow(()-> new TarefaNaoEncontradaException("tarefa nao encontrada pelo id"));

        if (tarefa.getStatusTarefa().equals(StatusTarefa.CONCLUIDO)) {
            throw new IllegalArgumentException("nao pode, ta dando bug de atualizar duas vezes a mesma");
        }

        tarefa.setStatusTarefa(StatusTarefa.CONCLUIDO);



        return toDTO(tarefa);

    }

    /**
     * Deletar tarefa
     * @param id da tarefa a ser deletado
     * @throws TarefaNaoEncontradaException caso nao exista tarefa com id passado
     */

    public void deletar(long id){

        Tarefa tarefa = tarefas.stream().filter(tarefa1 -> tarefa1.getId().equals(id)).findAny().orElseThrow(()-> new TarefaNaoEncontradaException("tarefa não encontrada"));
        tarefas.remove(tarefa);

    }

    /**
     * Transforma Objeto {@link Tarefa} em {@link TarefaResponseDTO}
     * @param tarefa
     * @return {@link TarefaResponseDTO}
     */

    public TarefaResponseDTO toDTO(Tarefa tarefa){
        return new TarefaResponseDTO(tarefa.getId(), tarefa.getNome(), tarefa.getDescricao(),tarefa.getPrioridade(),  tarefa.getStatusTarefa());
    }



}
