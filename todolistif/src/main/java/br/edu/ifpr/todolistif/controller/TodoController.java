package br.edu.ifpr.todolistif.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifpr.todolistif.model.ToDo;
import br.edu.ifpr.todolistif.repository.ToDoRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


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
    
    //criar uma nova tarefa (ToDo) e salvar no banco de dados
    @PostMapping("/create") // Define que o método create() será chamado quando uma requisição POST for feita para a URL "/create" do aplicativo.
    public String create(ToDo toDo) {
        toDoRepository.save(toDo);
        return "redirect:/insert"; // redireciona para a página de inserção após salvar a tarefa
    }

    


    @PostMapping("/deletePorTitulo") // Define que o método create() será chamado quando uma requisição POST for feita para a URL "/create" do aplicativo.
    public String delete(ToDo toDo) {
        ToDo tarefa = toDoRepository.findByTitle(toDo.getTitle());
        toDoRepository.delete(tarefa);
        return "redirect:/deletePorTitulo"; // redireciona para a página de inserção após salvar a tarefa
    }




    @GetMapping("/deletePorTitulo")
    public ModelAndView apagarTarefas() {
              return new ModelAndView(
           
            "todolist/deletePorTitulo", Map.of("todos", toDoRepository.findAll()) 
        );
    }
    

    @GetMapping("/insert")
    public String renderizarTelaInsert() {
        return "todolist/insert";
    }
    

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/list") // Define que o método index() será chamado quando uma requisição GET for feita para a URL raiz ("/") do aplicativo.
    public ModelAndView renderizarTelaList() { // qnd chamado, executa uma listagem de todas as tarefas (ToDo) persistidas no BD e retorna uma resposta adequada.
        // ModelAndView cria uma view do model, como quero mostrar na tela.
             
        return new ModelAndView(
            // "todolistif/list" é o nome da view (página HTML) que será renderizada para exibir a lista de tarefas.
            // Map.of("todos", toDoRepository.findAll()) cria um mapa de dados que será passado para a view. A chave "todos" é usada para acessar a lista de tarefas no template da view, e o valor é obtido chamando o método findAll() do repositório, que retorna todas as tarefas armazenadas no banco de dados.
            // um mapa de dados é uma estrutura que associa chaves a valores, permitindo que você armazene e recupere informações de forma organizada. No contexto do Spring MVC, o mapa de dados é usado para passar informações do controlador para a view, permitindo que os dados sejam exibidos na interface do usuário.
            //.findAll: retorna uma lista de todas as entidades ToDo armazenadas no banco de dados.
            "todolist/list", Map.of("todos", toDoRepository.findAll()) 
        );
    }

    // exemplo de teste cm thunderclient: GET http://localhost:8080/list
    // @GetMapping("/teste/toDos")
    // public String getMethodName(@RequestParam String param) {
    //     return new String();
    // }
    
    


}
// Controller controla todo tipo de chamada HTTP, como GET, POST, PUT e DELETE. Ele é responsável por receber as requisições do usuário, 
// processá-las e retornar uma resposta adequada. No caso do TodoController, ele lida com as operações relacionadas às tarefas (ToDo), 
// como criar novas tarefas, listar tarefas existentes, atualizar tarefas e excluir tarefas.