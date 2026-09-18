package com.example.demo.entity;

/**
 * Representa entidade tarefa
 */

public class Tarefa {

    private Long id;
    private String nome;
    private String descricao;
    private String prioridade;
    private StatusTarefa statusTarefa;

    public Tarefa() {
        this.id = null;
        this.nome = "";
        this.descricao = "";
        this.statusTarefa = null;
    }

    public Tarefa(String nome, String descricao, String prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public Tarefa(Long id, String nome, String descricao, StatusTarefa statusTarefa, String prioridade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.statusTarefa = statusTarefa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusTarefa getStatusTarefa() {
        return statusTarefa;
    }

    public void setStatusTarefa(StatusTarefa statusTarefa) {
        this.statusTarefa = statusTarefa;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", statusTarefa=" + statusTarefa +
                '}';
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
