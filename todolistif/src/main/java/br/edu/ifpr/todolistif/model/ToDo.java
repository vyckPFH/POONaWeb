package br.edu.ifpr.todolistif.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ToDo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id; //pk do banco
    @Column(nullable = false)
    private String title; //tarefa
    @Column(nullable = false)
    private LocalDateTime createdAt; //data criação da tarefa
    @Column(nullable = false)
    private LocalDateTime deadLine; //prazo para concluir a tarefa
    private LocalDateTime finishedAt; //data de conclusão da tarefa

    public ToDo() {
        this.createdAt = LocalDateTime.now();
    }

    public void markAsFinished() {
        this.finishedAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }
    
}
