package com.example.todolistjava;

import com.example.todolistjava.requestdto.TodosRequestDto;
import com.example.todolistjava.requestdto.TodosUpdateRequestDto;
import com.example.todolistjava.responsedto.ErrorResponseDto;
import com.example.todolistjava.responsedto.TodosListResponseDto;
import com.example.todolistjava.responsedto.TodosResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodosService {

    private final TodosRepository todosRepository;

    public TodosService(TodosRepository todosRepository){
        this.todosRepository = todosRepository;
    }

    // TodosRequestDto -> username, title, content -> Todos(entity) -> TodosRepository(save) -> Todos(entity, saved) -> TodosData -> TodosResponseDto -> ResponseEntity
    public ResponseEntity<TodosResponseDto> saveTodosService(TodosRequestDto todosRequestDto) {
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

    public ResponseEntity<TodosListResponseDto> findAllTodosService() {
        List<Todos> todosList = todosRepository.findAll();
        List<TodosData> todosDataList = todosList.stream()
                .map(todos -> new TodosData(
                        todos.getTodosId(),
                        todos.getUserName(),
                        todos.getTitle(),
                        todos.getContent(),
                        todos.getCreateDate(),
                        todos.getEditDate()
                ))
                .collect(Collectors.toList());
        TodosListResponseDto todosListResponseDto = new TodosListResponseDto(200, todosDataList);
        return ResponseEntity.ok(todosListResponseDto);
    }

    public ResponseEntity<?> findTodosByIdService(Long todosId) {
        Optional<Todos> optionalTodos = todosRepository.findById(todosId);
        if(optionalTodos.isEmpty()) {
            ErrorResponseDto error = new ErrorResponseDto(404, "일정 정보를 조회할 수 없습니다.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        Todos foundTodos = optionalTodos.get();

        TodosData todosData = new TodosData(
                foundTodos.getTodosId(),
                foundTodos.getUserName(),
                foundTodos.getTitle(),
                foundTodos.getContent(),
                foundTodos.getCreateDate(),
                foundTodos.getEditDate()
        );

        TodosResponseDto todosResponseDto = new TodosResponseDto(200, todosData);
        return ResponseEntity.ok(todosResponseDto);
    }

    public ResponseEntity<?> updateTodosById(Long todosId, TodosUpdateRequestDto requestDto) {
        Optional<Todos> optionalTodos = todosRepository.findById(todosId);

        if(optionalTodos.isEmpty()) {
            ErrorResponseDto error = new ErrorResponseDto(404, "일정을 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        Todos todos = optionalTodos.get();

        if(requestDto.getTitle() != null) {
            todos.setTitle(requestDto.getTitle());
        }
        if(requestDto.getContent() != null) {
            todos.setTitle(requestDto.getContent());
        }

        todos.setEditDate(LocalDateTime.now());

        todosRepository.save(todos);

        TodosData updatedData = new TodosData(
                todos.getTodosId(),
                todos.getUserName(),
                todos.getTitle(),
                todos.getContent(),
                todos.getCreateDate(),
                todos.getEditDate()
        );

        return ResponseEntity.ok(new TodosResponseDto(200, updatedData));
    }
}

