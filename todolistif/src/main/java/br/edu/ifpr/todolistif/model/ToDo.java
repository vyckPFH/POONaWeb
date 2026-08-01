package br.edu.ifpr.todolistif.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id; //pk do banco
    @Column(nullable = false)
    private String title; //tarefa
    @Column(nullable = false)
    private LocalDate createdAt; //data criação da tarefa
    // @Column(nullable = false)
    private LocalDate deadLine; //prazo para concluir a tarefa
    private LocalDate finishedAt; //data de conclusão da tarefa

    public ToDo() {
        this.createdAt = LocalDate.now();
    }

    public void markAsFinished() {
        this.finishedAt = LocalDate.now();
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public LocalDate getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDate finishedAt) {
        this.finishedAt = finishedAt;
    }
    
}
