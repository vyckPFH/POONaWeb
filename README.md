#### Spring its a ecosistem
|aaaaaaaaaaaaaaaaa|
|aaaaaaaaaaaaaaaa|
|aaaaaaaaaaaaaaa|
|aaaaaaaaaaaaaa|
|aaaaaaaaaaaaa|
|aaaaaaaaaaaa|
|aaaaaaaaaaa|
|aaaaaaaaaa|
|aaaaaaaaa|
|aaaaaaaa|
|aaaaaaa|
|aaaaaa|
|aaaaa|
|aaaa|
|aaa|
|aa|
|a|

## MVC x APIs?

A aplicação pode ser entendida como uma estrutura baseada em MVC, enquanto o uso de APIs representa uma forma de comunicação entre o frontend e o backend.

| Aspecto | MVC | API |
|--------|-----|-----|
| Definição | Padrão de arquitetura para organizar a aplicação em Model, View e Controller. | Interface que permite que diferentes sistemas troquem dados de forma padronizada. |
| Objetivo | Separar responsabilidades dentro da aplicação. | Expor funcionalidades para serem consumidas por outros sistemas ou interfaces. |
| Componentes | Model: regras e dados; View: interface; Controller: processamento e interação. | Endpoint: rota que recebe e retorna dados, normalmente em JSON. |
| Comunicação | A View é renderizada no servidor e devolvida ao usuário. | O cliente consome os dados via requisições HTTP, como GET, POST, PUT e DELETE. |
| Exemplo | Spring Boot renderizando páginas HTML com Thymeleaf. | Spring Boot retornando JSON para frontend ou outro sistema. |
| Uso comum | Aplicações web com interface tradicional. | Aplicações que precisam de integração com frontend, mobile ou terceiros. |

Em resumo, MVC organiza a estrutura interna da aplicação, enquanto API define como os dados são acessados e trocados. Em muitos projetos modernos, eles podem coexistir: a aplicação usa MVC para renderizar páginas e também expõe uma API para comunicação com outros clientes.


## Estrutura do projeto

A aplicação está organizada em pastas seguindo a arquitetura padrão do Spring Boot:

- `todolistif/`
  - `pom.xml`
    - Arquivo de configuração do Maven.
    - Define as dependências da aplicação, como Spring Boot, Thymeleaf e outras bibliotecas necessárias para o funcionamento do projeto.

  - `mvnw` e `mvnw.cmd`
    - Scripts para executar a aplicação com Maven Wrapper, sem necessidade de instalar o Maven manualmente.

  - `src/`
    - Pasta principal contendo o código-fonte da aplicação.

    - `main/`
      - Diretório com os arquivos da aplicação em execução.

      - `java/`
        - Contém o código Java do projeto.

        - `br/edu/ifpr/todolistif/`
          - Pacote principal da aplicação.

          - `TodolistifApplication.java`
            - Classe principal responsável por iniciar a aplicação Spring Boot.

          - `controller/`
            - Pasta onde ficam os controladores da aplicação.
            - Eles recebem as requisições HTTP e direcionam o fluxo para a lógica correta.
            - Exemplo: `TodoController.java` e `helloworldController.java`.

          - `model/`
            - Pasta com as classes de modelo, que representam os dados da aplicação.
            - Aqui ficam as entidades do sistema, como `ToDo.java`, que descreve uma tarefa.

          - `repository/`
            - Pasta com as interfaces de acesso ao banco de dados.
            - Responsável por operações de persistência, como salvar, buscar e excluir tarefas.
            - Exemplo: `ToDoRepository.java`.

      - `resources/`
        - Contém arquivos de configuração e recursos usados pela aplicação.

        - `application.properties`
          - Arquivo com as configurações da aplicação, como porta do servidor e conexão com o banco de dados.

        - `templates/`
          - Pasta com os templates HTML renderizados pela aplicação.
          - Exemplo: `index.html`, que é a página principal da interface.

    - `test/`
      - Diretório com os testes da aplicação.
      - Exemplo: `TodolistifApplicationTests.java`.

  - `target/`
    - Pasta gerada automaticamente pelo Maven após compilação.
    - Contém arquivos compilados e resultados do build.


grupo: dominio da empresa escrito ao contrario (cocacola.com -> com.cocacola)
artifact: nome do projeto


*controller* é a classe que lida com as requisições http e envia respostas para o cliente. Ela é responsável por receber as solicitações do usuário, processá-las e retornar os resultados apropriados. Em um aplicativo web, os controladores geralmente lidam com rotas, parâmetros de solicitação e lógica de negócios relacionada à interação do usuário.


### **Tipos de APIs:** ###
- **REST:** Representational State Transfer, é um estilo de arquitetura de software para sistemas distribuídos (sistemas distriubuidos são sistemas que possuem componentes que estão em locais diferentes, mas que se comunicam entre si). 

    - **Cada método usa a URL + HTTP method para definir a operação, sem manter estado entre chamadas.**
    - Ele **define um conjunto de restrições e princípios para a criação de APIs**(Application Programming Interfaces) que permitem a comunicação entre diferentes sistemas. 

    - As APIs RESTful utilizam os métodos HTTP (GET, POST, PUT, DELETE) para realizar operações em recursos, que são representações de entidades do sistema.

    - Cada recurso é identificado por uma URL única e pode ser manipulado por meio das operações definidas pelos métodos HTTP.

    exemplo de uma API RESTful para gerenciar tarefas (to-do list):
    ```java
    // endpoint é a URL que representa um recurso específico na API. No caso de uma API RESTful para gerenciar tarefas, os endpoints podem ser definidos da seguinte forma:

    @GetMapping("/tarefas") // Endpoint para listar todas as tarefas
    public List<ToDo> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/tarefas/{id}") // Endpoint para obter uma tarefa específica pelo ID
    public ResponseEntity<ToDo> obterPorId(@PathVariable Long id) {
        return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    } 
    @PostMapping("/tarefas") // Endpoint para criar uma nova tarefa
    public ResponseEntity<ToDo> criarTarefa(@RequestBody ToDo tarefa) {
        ToDo salva = repository.save(tarefa);
        return ResponseEntity.status(201).body(salva);
    }
    @PutMapping("/tarefas/{id}") // Endpoint para atualizar uma tarefa existente pelo ID
    public ResponseEntity<ToDo> atualizarTarefa(
        @PathVariable Long id, 
        @RequestBody ToDo tarefaAtualizada) {
        return repository.findById(id) 
            .map(tarefa -> {
                tarefa.setTitulo(tarefaAtualizada.getTitulo());
                tarefa.setDescricao(tarefaAtualizada.getDescricao());
                repository.save(tarefa);
                return ResponseEntity.ok(tarefa);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/tarefas/{id}") // Endpoint para deletar uma tarefa pelo ID
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    ```

## Características das APIs: ##
stateless e stateful são características que descrevem como uma API lida com o estado das requisições e a persistência de informações entre elas.

+ STATELESS (usar com token) APIs: são ** APIs que não mantêm estado entre as requisições**. Cada requisição é independente e não depende de informações armazenadas no servidor. Isso significa que o servidor não armazena informações sobre o cliente entre as requisições, tornando a API mais escalável e fácil de gerenciar.

As APIs RESTful são geralmente stateless, o que significa que cada requisição deve conter todas as informações necessárias para processá-la, sem depender de dados armazenados no servidor.

+ STATEFUL APIs: são APIs que mantêm estado entre as requisições. O servidor armazena informações sobre o cliente entre as requisições, permitindo que a API mantenha o contexto da interação com o cliente. Isso pode ser útil em casos onde é necessário manter informações de sessão ou histórico de interações, mas também pode aumentar a complexidade e reduzir a escalabilidade da API.

stateful APIs podem ser mais difíceis de escalar e gerenciar, pois o servidor precisa manter informações sobre cada cliente entre as requisições. Isso pode levar a problemas de desempenho e complexidade adicional na implementação da API. Mas, no caso do tcc, se tiver login, a API pode ser stateful, pois o servidor precisará manter informações sobre o usuário logado entre as requisições.

