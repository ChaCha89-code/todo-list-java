package com.example.todolistjava;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
@Getter
@Setter
public class Todos {

    // 속성
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "todos_id")
    private Long todosId;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "edit_date", nullable = false)
    private LocalDateTime editDate;

    // 생성 시 자동 시간 설정
    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
        this.editDate = LocalDateTime.now();
    }

    // 수정 시 자동 시간 업데이트
    @PreUpdate
    protected void onUpdate() {
        this.editDate = LocalDateTime.now();
    }

    // @Column(name = "...") -> To match snake_case column names in MySQL
    // nullable = false, length -> Matches SQL column constraints (NOT NULL, VARCHAR(x))
    // columnDefinition = "TEXT" -> Ensures MySQL stores large content correctly
    // @PrePersist, @PreUpdate -> Lets Java handle timestamp logic like DEFAULT CURRENT_TIMESTAMP ON UPDATE
    // @Getters & @Setters : Lombok (Needed for JPA and JavaBeans standards)

}
