package com.example.todolistjava;

import com.example.todolistjava.requestdto.TodosRequestDto;
import com.example.todolistjava.requestdto.TodosUpdateRequestDto;
import com.example.todolistjava.responsedto.ErrorResponseDto;
import com.example.todolistjava.responsedto.TodosListResponseDto;
import com.example.todolistjava.responsedto.TodosResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/todos")
public class TodosController {

    private final TodosService todosService;

    public TodosController(TodosService todosService) {
        this.todosService = todosService;
    }

    @PostMapping
    public ResponseEntity<?> saveTodosController (
            @Valid @RequestBody TodosRequestDto todosRequestDto, BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(new ErrorResponseDto(400, errorMessage));
        }
        return todosService.saveTodosService(todosRequestDto);
    }

    @GetMapping
    public ResponseEntity<TodosListResponseDto> findAllTodosController() {
        return todosService.findAllTodosService();
    }

    @GetMapping("/{todosId}")
    public ResponseEntity<?> findTodosByIdController(@PathVariable Long todosId) {
        return todosService.findTodosByIdService(todosId);
    }

    @PatchMapping("/{todosId}")
    public ResponseEntity<?> patchTodos (@PathVariable Long todosId, @RequestBody TodosUpdateRequestDto requestDto) {
        return todosService.updateTodosById(todosId, requestDto);
    }


}
