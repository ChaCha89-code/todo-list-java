package com.example.todolistjava.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodosRequestDto {

    // 속성
    @NotBlank(message = "유저 이름은 공백일 수 없습니다.")
    private String userName;
    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private String title;
    @NotBlank(message = "내용은 공백일 수 없습니다.")
    private String content;

    // 생성자

    // 기능
}
