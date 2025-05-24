package com.example.todolistjava;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/todos")
public class TodosController {

    private final TodosService todosService;

    public TodosController(TodosService todosService) {
        this.todosService = todosService;
    }

    @PostMapping
    public ResponseEntity<TodosResponseDto> saveTodos (@RequestBody TodosRequestDto todosRequestDto) {
        return todosService.saveTodos(todosRequestDto);
    }

}
