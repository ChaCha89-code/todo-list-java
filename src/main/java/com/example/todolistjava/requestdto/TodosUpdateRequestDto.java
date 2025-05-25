package com.example.todolistjava.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodosUpdateRequestDto {
    private String title;
    private String content;
}
