package com.example.todolistjava;

import lombok.Getter;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

@Getter
public class TodosRequestDto {

    // 속성
    private String userName;
    private String title;
    private String content;

    // 생성자
    public TodosRequestDto() {}

    // 기능
}
