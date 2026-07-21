package br.edu.ifpr.todolistif;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpr.todolistif.model.ToDo;
import br.edu.ifpr.todolistif.repository.ToDoRepository;

@SpringBootApplication
public class TodolistifApplication {

	private final ToDoRepository toDoRepository;

    public TodolistifApplication(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(TodolistifApplication.class, args);
	}

	CommandLineRunner exe (ToDoRepository toDoRepository) {
		return args -> {
			// Aqui você pode adicionar código para inicializar o banco de dados ou executar outras tarefas ao iniciar a aplicação
			ToDo todo = new ToDo();
			todo.setTitle("Exemplo de tarefa");
			todo.setDeadLine(LocalDateTime.now().plusDays(7)); // Define um prazo de 7 dias para a tarefa
			toDoRepository.save(todo);
		};
	}

}
