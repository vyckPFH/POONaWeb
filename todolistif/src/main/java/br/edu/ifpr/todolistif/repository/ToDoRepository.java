package br.edu.ifpr.todolistif.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifpr.todolistif.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    
}
