package com.example.todolistjava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Generates no-args constructor
@AllArgsConstructor // Generates constructor with all fields
public class TodosData {
    private Long todosId;
    private String userName;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime editDate;

}



