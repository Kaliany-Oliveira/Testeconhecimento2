package com.example.Firsttest.service;

import com.example.Firsttest.model.Todo;
import com.example.Firsttest.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    public Todo createTodo(Todo todo) {
        if (todo.getTitle() == null || todo.getTitle().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'title' é obrigatório");
        }

        Todo novoTodo = todoRepository.save(todo);
        System.out.println("Novo Todo Criado: " + novoTodo);
        return novoTodo;
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = getTodoById(id);
        todo.setTitle(todoDetails.getTitle());
        todo.setCompleted(todoDetails.isCompleted());
        todo.setTempoGasto(todoDetails.getTempoGasto());

        Todo atualizado = todoRepository.save(todo);
        System.out.println("Todo Atualizado: " + atualizado);
        return atualizado;
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }

        try {
            todoRepository.deleteById(id);
            System.out.println("Todo deletado com sucesso: " + id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar tarefa: " + e.getMessage());
        }
    }
}