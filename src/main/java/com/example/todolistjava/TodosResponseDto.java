package com.example.todolistjava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Generates no-args constructor
@AllArgsConstructor // Generates constructor with all fields
public class TodosResponseDto {

    // 속성
    private int status;
    private TodosData data;

    // 생성자


    // 기능

}
