package com.example.demo.repository;

import com.example.demo.dto.TarefaRequestDTO;
import com.example.demo.dto.TarefaResponseDTO;
import com.example.demo.entity.StatusTarefa;
import com.example.demo.entity.Tarefa;
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

    public TarefaResponseDTO salvar(Tarefa tarefa){
        tarefa.setStatusTarefa(StatusTarefa.PENDENTE);
        tarefa.setId(++id);

        tarefas.add(tarefa);

        Tarefa tarefaCriada = tarefas.getLast();
        return toDTO(tarefaCriada);
    }

    public List<TarefaResponseDTO> listar(){
        List<TarefaResponseDTO> tarefa = tarefas.stream().map(tarefa1 -> toDTO(tarefa1)).toList();
        return tarefa;
    }

    public List<TarefaResponseDTO> listarPendentes() {
        List<TarefaResponseDTO> tarefaResponseDTOS = tarefas.stream().filter(tarefa -> tarefa.getStatusTarefa()
                        .equals(StatusTarefa.PENDENTE))
                        .map(tarefa -> toDTO(tarefa)).toList();

        return tarefaResponseDTOS;
    }

    public List<TarefaResponseDTO> listarConcluidas() {
        List<TarefaResponseDTO> tarefaResponseDTOS = tarefas.stream().filter(tarefa -> tarefa.getStatusTarefa()
                .equals(StatusTarefa.CONCLUIDO))
                .map(tarefa -> toDTO(tarefa)).toList();

        return tarefaResponseDTOS;
    }

    public TarefaResponseDTO buscarPorId (Long id){

        Tarefa tarefa = tarefas.stream().filter(tarefa1 -> tarefa1.getId().equals(id)).findAny().orElseThrow(()-> new IllegalArgumentException("Tarefa nao encontrada"));
        return toDTO(tarefa);
    }

    public TarefaResponseDTO atualizar (TarefaRequestDTO tarefaRequestDTO, Long id){

        Tarefa tarefa = tarefas.stream().filter(tarefa1 -> tarefa1.getId().equals(id)).findAny().orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        tarefa.setNome(tarefaRequestDTO.nome());
        tarefa.setDescricao(tarefaRequestDTO.descricao());
        tarefa.setPrioridade(tarefaRequestDTO.prioridade());

        tarefas.add(tarefa);
        return toDTO(tarefa);
    }

    public TarefaResponseDTO atualizarParcial(TarefaRequestDTO tarefaRequestDTO, Long id){
        Tarefa tarefa = tarefas.stream().filter(tarefa1 -> tarefa1.getId().equals(id)).findAny().orElseThrow(() -> new IllegalArgumentException("tarefa nao encontrada"));




        tarefas.add(tarefa);
        return toDTO(tarefa);
    }


    public TarefaResponseDTO atualizarStatusConcluido(Long id) {
        Tarefa tarefa = tarefas.stream().filter(tarefa1 -> tarefa1.getId().equals(id)).findAny().orElseThrow(()-> new IllegalArgumentException("tarefa nao encontrada pelo id"));

        if (tarefa.getStatusTarefa().equals(StatusTarefa.CONCLUIDO)) {
            throw new IllegalArgumentException("nao pode, ta dando bug de atualizar duas vezes a mesma");
        }

        tarefa.setStatusTarefa(StatusTarefa.CONCLUIDO);


        tarefas.add(tarefa);

        return toDTO(tarefa);

    }


    public void deletar(long id){

        Tarefa tarefa = tarefas.stream().filter(tarefa1 -> tarefa1.getId().equals(id)).findAny().orElseThrow(()-> new IllegalArgumentException("tarefa não encontrada"));
        tarefas.remove(tarefa);

    }

    public TarefaResponseDTO toDTO(Tarefa tarefa){
        return new TarefaResponseDTO(tarefa.getId(), tarefa.getNome(), tarefa.getDescricao(),tarefa.getPrioridade(),  tarefa.getStatusTarefa());
    }



}
