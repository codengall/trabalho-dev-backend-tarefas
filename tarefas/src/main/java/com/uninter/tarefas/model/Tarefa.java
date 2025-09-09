// src/main/java/com/uninter/tarefas/model/Tarefa.java
package com.uninter.tarefas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity // Marca esta classe como uma entidade JPA
public class Tarefa {

    @Id // Marca o campo 'id' como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a geração automática do ID
    private Long id;
    private String nome;
    private LocalDate dataEntrega;
    private String responsavel;

    // Construtor padrão 
    public Tarefa() {
    }

    // Construtor com parâmetros
    public Tarefa(String nome, LocalDate dataEntrega, String responsavel) {
        this.nome = nome;
        this.dataEntrega = dataEntrega;
        this.responsavel = responsavel;
    }

    // Getters e Setters
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

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", dataEntrega=" + dataEntrega +
               ", responsavel='" + responsavel + '\'' +
               '}';
    }
}