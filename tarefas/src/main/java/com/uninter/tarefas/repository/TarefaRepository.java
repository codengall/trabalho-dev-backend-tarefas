package com.uninter.tarefas.repository;

import com.uninter.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository // Marca esta interface como um componente de repositório Spring
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}