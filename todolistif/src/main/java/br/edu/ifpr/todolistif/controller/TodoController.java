package br.edu.ifpr.todolistif.controller;

import org.springframework.stereotype.Controller;

import br.edu.ifpr.todolistif.model.ToDo;
import br.edu.ifpr.todolistif.repository.ToDoRepository;

@Controller // Define que essa classe é um controlador do Spring MVC, responsável por lidar com as requisições HTTP e retornar respostas apropriadas.
// A anotação @Controller indica que essa classe contém métodos que podem ser mapeados para URLs específicas e processar as solicitações recebidas.
// pode retornar respostas em diferentes formatos, como HTML, JSON, XML, etc., (ou paginas web) dependendo da configuração do projeto e das bibliotecas utilizadas.

public class TodoController {
    private final ToDoRepository toDoRepository;

    //Construtor | para injetar a dependência do repositório
    public TodoController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    //Métodos do controller para lidar com as requisições HTTP (GET, POST, PUT, DELETE) podem ser adicionados aqui
    
    //Criar nova tarefa
    public String create(ToDo to_do) {
        toDoRepository.save(to_do);
        return "redirect:/"; //Redireciona para a lista de tarefas após criar uma nova
    }


}
// Controller controla todo tipo de chamada HTTP, como GET, POST, PUT e DELETE. Ele é responsável por receber as requisições do usuário, 
// processá-las e retornar uma resposta adequada. No caso do TodoController, ele lida com as operações relacionadas às tarefas (ToDo), 
// como criar novas tarefas, listar tarefas existentes, atualizar tarefas e excluir tarefas.