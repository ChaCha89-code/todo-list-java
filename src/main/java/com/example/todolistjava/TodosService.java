package com.example.todolistjava;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class TodosService {

    private final TodosRepository todosRepository;

    public TodosService(TodosRepository todosRepository){
        this.todosRepository = todosRepository;
    }

    public ResponseEntity<TodosResponseDto> saveTodos(TodosRequestDto todosRequestDto) {
        Todos todos = new Todos();
        todos.setUserName(todosRequestDto.getUserName());
        todos.setTitle(todosRequestDto.getTitle());
        todos.setContent(todosRequestDto.getContent());
        Todos savedTodos = todosRepository.save(todos);

        TodosData todosData = new TodosData(
                savedTodos.getTodosId(),
                savedTodos.getUserName(),
                savedTodos.getTitle(),
                savedTodos.getContent(),
                savedTodos.getCreateDate(),
                savedTodos.getEditDate()
        );

        TodosResponseDto todosResponseDto = new TodosResponseDto(200, todosData);
        return ResponseEntity.ok(todosResponseDto);
        // .ok -> HttpStatus.OK를 자동으로 넣어주는 것
    }
}
