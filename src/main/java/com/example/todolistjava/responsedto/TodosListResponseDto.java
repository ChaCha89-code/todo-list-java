package com.example.todolistjava.responsedto;

import com.example.todolistjava.TodosData;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TodosListResponseDto {
    private int status;
    private List<TodosData> todosDataList;

}
