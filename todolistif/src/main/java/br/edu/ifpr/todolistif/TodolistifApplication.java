package br.edu.ifpr.todolistif;

import java.time.LocalDateTime; 

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.edu.ifpr.todolistif.model.ToDo;
import br.edu.ifpr.todolistif.repository.ToDoRepository;
// CLASSE MAIN DO PROJETO DO JAVA SPRING BOOT

@SpringBootApplication // Define que essa é a classe main do projeto e é a junção das anotações @Configuration, @EnableAutoConfiguration e @ComponentScan
// @Configuration -> Define que está classe pode ter metodos que definem BEANS, beans são objetos que são gerenciados pelo Spring, ou seja, o Spring é responsável por criar, configurar e gerenciar o ciclo de vida desses objetos
// @EnableAutoConfiguration -> Permite que o Spring Boot configure automaticamente a aplicação com base nas dependências presentes no classpath. Por exemplo, se você tiver o Spring MVC no classpath, ele configurará automaticamente um servidor web e outros componentes necessários para uma aplicação web.
// @ComponentScan -> Permite que o Spring Boot escaneie os pacotes e subpacotes da aplicação em busca de classes anotadas com @Component, @Service, @Repository, @Controller, etc., para que elas sejam registradas como beans no contexto do Spring. Isso facilita a injeção de dependências e a configuração automática da aplicação. 
public class TodolistifApplication {

	private final ToDoRepository todoRepository; 

    public TodolistifApplication(ToDoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(TodolistifApplication.class, args);
	}

	CommandLineRunner exe (ToDoRepository todoRepository) {
		return args -> {
			// Aqui você pode adicionar código para inicializar o banco de dados ou executar outras tarefas ao iniciar a aplicação
			ToDo todo = new ToDo();
			todo.setTitle("Exemplo de tarefa");
			todo.setDeadLine(LocalDateTime.now()); 
			todoRepository.save(todo);
		};
	}

}
