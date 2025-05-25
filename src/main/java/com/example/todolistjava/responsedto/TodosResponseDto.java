package com.example.todolistjava.responsedto;

import com.example.todolistjava.TodosData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Generates no-args constructor
@AllArgsConstructor // Generates constructor with all fields
// 위 세 어노테이션은 유지 보수가 어렵다(캠슐화, 객체지향을 해친다)
public class TodosResponseDto {

    // 속성
    private int status;
    private TodosData data;

    // 생성자

    // 기능

}
