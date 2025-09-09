package com.uninter.tarefas.controller;

import com.uninter.tarefas.model.Tarefa;
import com.uninter.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController 
@RequestMapping("/api/tarefas") 
public class TarefaController {

    @Autowired // Injeta uma instância de TarefaRepository
    private TarefaRepository tarefaRepository;

    // Endpoint para criar uma nova tarefa (POST /api/tarefas)
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        try {
            Tarefa novaTarefa = tarefaRepository.save(tarefa);
            return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED); // Retorna 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500 Internal Server Error em caso de erro
        }
    }

    // Endpoint para listar todas as tarefas (GET /api/tarefas)
    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTodasTarefas() {
        try {
            List<Tarefa> tarefas = tarefaRepository.findAll();
            if (tarefas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content se não houver tarefas
            }
            return new ResponseEntity<>(tarefas, HttpStatus.OK); // Retorna 200 OK com a lista de tarefas
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para consultar uma tarefa específica pelo ID (GET /api/tarefas/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> consultarTarefaPorId(@PathVariable("id") Long id) {
        Optional<Tarefa> tarefaData = tarefaRepository.findById(id);

        if (tarefaData.isPresent()) {
            return new ResponseEntity<>(tarefaData.get(), HttpStatus.OK); // Retorna 200 OK com a tarefa encontrada
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 Not Found se a tarefa não for encontrada
        }
    }

    // Endpoint para atualizar uma tarefa existente (PUT /api/tarefas/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable("id") Long id, @RequestBody Tarefa tarefa) {
        Optional<Tarefa> tarefaData = tarefaRepository.findById(id);

        if (tarefaData.isPresent()) {
            Tarefa _tarefa = tarefaData.get();
            _tarefa.setNome(tarefa.getNome()); // Atualiza o nome
            _tarefa.setDataEntrega(tarefa.getDataEntrega()); // Atualiza a data de entrega
            _tarefa.setResponsavel(tarefa.getResponsavel()); // Atualiza o responsável
            return new ResponseEntity<>(tarefaRepository.save(_tarefa), HttpStatus.OK); // Retorna 200 OK com a tarefa atualizada
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 Not Found
        }
    }

    // Endpoint para remover uma tarefa (DELETE /api/tarefas/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removerTarefa(@PathVariable("id") Long id) {
        try {
            tarefaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content após a exclusão bem-sucedida
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500 Internal Server Error em caso de erro
        }
    }
}