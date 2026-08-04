# Aula completa: Spring MVC + Thymeleaf + ToDo List

Este arquivo reúne o conteúdo que foi discutido até agora sobre a aplicação de lista de tarefas (`ToDo List`) usando Spring Boot, Spring MVC, JPA e Thymeleaf.

A ideia é transformar o projeto em uma referência didática: explicação da estrutura, tutorial de uso, exemplos de código e tabela de comandos úteis.

---

## 1. Visão geral do projeto

O projeto é uma aplicação web simples para gerenciar tarefas. A lógica está organizada em camadas:

- `controller` → recebe as requisições do navegador
- `model` → representa os dados da aplicação
- `repository` → acessa o banco de dados
- `templates` → páginas HTML renderizadas pelo Spring

A arquitetura usada é basicamente o padrão MVC:

- Model: classe `ToDo`
- View: arquivos HTML em `templates`
- Controller: classe `TodoController`

---

## 2. Estrutura da aplicação

### 2.1 Pasta principal

```text
todolistif/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── src/
    ├── main/
    │   ├── java/
    │   │   └── br/edu/ifpr/todolistif/
    │   │       ├── TodolistifApplication.java
    │   │       ├── controller/
    │   │       │   ├── TodoController.java
    │   │       │   └── helloworldController.java
    │   │       ├── model/
    │   │       │   └── ToDo.java
    │   │       └── repository/
    │   │           └── ToDoRepository.java
    │   └── resources/
    │       ├── application.properties
    │       └── templates/
    │           ├── index.html
    │           └── todolist/
    │               └── list.html
    └── test/
```

---

## 3. O que tem em cada pasta

### 3.1 `src/main/java`

É a pasta onde fica o código Java da aplicação.

### 3.2 `controller/`

Contém as classes responsáveis por receber requisições HTTP.

Exemplo:

- `TodoController.java`
  - recebe a rota `/create`
  - recebe a rota `/list`
  - chama o repositório
  - envia dados para a view

- `helloworldController.java`
  - controlador simples usado para testar/experimentar o Spring MVC

### 3.3 `model/`

Contém as entidades do sistema, ou seja, as classes que representam objetos do banco.

Exemplo:

- `ToDo.java`
  - representa uma tarefa
  - contém atributos como:
    - `id`
    - `title`
    - `createdAt`
    - `deadLine`
    - `finishedAt`

### 3.4 `repository/`

Contém as interfaces de acesso ao banco de dados.

Exemplo:

- `ToDoRepository.java`
  - herda de `JpaRepository<ToDo, Long>`
  - permite salvar, buscar, listar e excluir tarefas

### 3.5 `resources/templates/`

Pasta onde ficam os arquivos HTML usados pelo Thymeleaf.

Exemplo:

- `index.html` → formulário principal
- `todolist/list.html` → página que lista as tarefas

### 3.6 `application.properties`

Arquivo de configuração da aplicação.

Ele pode conter coisas como:

- porta do servidor
- conexão com o banco
- configuração de Hibernate/JPA

---

## 4. Explicação do `ToDo.java`

A classe `ToDo` é a entidade principal da aplicação.

### Código base

```java
@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate createdAt;

    private LocalDate deadLine;
    private LocalDate finishedAt;

    public ToDo() {
        this.createdAt = LocalDate.now();
    }

    public void markAsFinished() {
        this.finishedAt = LocalDate.now();
    }
}
```

### O que cada campo significa

| Campo | Função |
|------|--------|
| `id` | identificador único da tarefa |
| `title` | texto da tarefa |
| `createdAt` | data de criação |
| `deadLine` | prazo de conclusão |
| `finishedAt` | data em que foi concluída |

### Observação importante

O `createdAt` é preenchido automaticamente no construtor.

---

## 5. Explicação do `ToDoRepository.java`

O repositório é a parte que conversa com o banco.

```java
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
```

### O que isso significa

`JpaRepository` já oferece métodos prontos, como:

- `save()` → salvar
- `findAll()` → listar tudo
- `findById()` → buscar por id
- `delete()` → excluir

Ou seja, o repositorio é a camada “caixa preta” para persistência.

---

## 6. Explicação do `TodoController.java`

O `TodoController` é o centro da aplicação web.

Ele recebe a URL, processa a informação e decide o que mostrar na tela.

### Exemplo de rotas

#### Criar tarefa

```java
@PostMapping("/create")
public String create(ToDo toDo) {
    toDoRepository.save(toDo);
    return "redirect:/";
}
```

Esse método:

1. recebe uma tarefa enviada pelo formulário
2. salva no banco
3. redireciona para `/`

#### Listar tarefas

```java
@GetMapping("/list")
public ModelAndView getMethodName() {
    return new ModelAndView("todolist/list", Map.of("todos", toDoRepository.findAll()));
}
```

Esse método:

1. busca todas as tarefas com `findAll()`
2. coloca essa lista no objeto `todos`
3. envia para a view `todolist/list`

---

## 7. Como o MVC funciona aqui

### Fluxo completo

1. O usuário acessa a página no navegador
2. O navegador faz uma requisição para uma rota, por exemplo `/list`
3. O `TodoController` recebe essa requisição
4. O controller chama o `ToDoRepository`
5. O repositório busca os dados no banco
6. A view (`list.html`) recebe essa lista
7. O Thymeleaf monta o HTML na tela

### Em linguagem simples

- `Controller` = recebe e decide
- `Model` = estrutura dos dados
- `View` = HTML mostrado para o usuário

---

## 8. O erro `Error resolving template`

Esse erro apareceu quando o Spring tentou localizar uma view que não existia.

### Exemplo do erro

```text
Error resolving template [index/todolist/list], template might not exist or might not be accessible by any of the configured Template Resolvers
```

### O que isso significa

O Spring estava tentando encontrar um template chamado:

```text
index/todolist/list
```

Mas a pasta correta no projeto é:

```text
src/main/resources/templates/todolist/list.html
```

### Como corrigir

O nome da view deve apontar para o caminho certo.

Exemplo correto:

```java
new ModelAndView("todolist/list", Map.of("todos", toDoRepository.findAll()))
```

Isso faz o Spring procurar:

```text
templates/todolist/list.html
```

---

## 8.1 Diferença entre `@GetMapping` e `@PostMapping`

Um erro muito comum no Spring MVC é misturar o tipo de requisição HTTP corretamente.

### `@GetMapping`

Usado para abrir uma página ou acessar uma URL sem enviar dados do formulário.

Exemplo:

```java
@GetMapping("/adding")
public String showAddingPage() {
    return "todolist/adding";
}
```

Esse método é usado quando o navegador vai abrir a tela de cadastro, por exemplo ao clicar em um botão que redireciona para a URL `/adding`.

### `@PostMapping`

Usado para enviar dados ao servidor, normalmente vindo de um formulário.

Exemplo:

```java
@PostMapping("/create")
public String create(ToDo toDo) {
    toDoRepository.save(toDo);
    return "redirect:/";
}
```

Esse método é usado quando o formulário é enviado para a rota `/create`.

### Diferença prática

- `GET` = abrir página
- `POST` = enviar dados

### Por que o erro acontece

Se você tem um botão como este:

```html
<button type="button" onclick="window.location.href='/adding'">Registrar uma nova Tarefa</button>
```

isso faz o navegador usar `GET` para abrir `/adding`.

Se no controller você tiver apenas:

```java
@PostMapping("/adding")
public String showAddingPage() {
    return "todolist/adding";
}
```

então o Spring não vai responder a essa requisição, porque o navegador está fazendo `GET`, e não `POST`.

### Regra simples para não errar novamente

- Se for para abrir uma tela: use `@GetMapping`
- Se for para enviar um formulário: use `@PostMapping`

### Exemplo correto do fluxo

```java
@GetMapping("/adding")
public String showAddingPage() {
    return "todolist/adding";
}

@PostMapping("/create")
public String create(ToDo toDo) {
    toDoRepository.save(toDo);
    return "redirect:/";
}
```

### Resumo visual

| Situação | Método HTTP | Anotação |
|----------|-------------|----------|
| abrir página `/adding` | GET | `@GetMapping("/adding")` |
| enviar formulário para `/create` | POST | `@PostMapping("/create")` |
| listar tarefas | GET | `@GetMapping("/list")` |

### E o erro `No static resource`?

Esse erro aparece quando o navegador tenta acessar uma URL que não existe como rota MVC e o Spring também não encontra um arquivo estático correspondente em `static/`.

Ou seja, o problema não é o HTML em si, e sim a falta da rota correta no controller.

### O que você precisa verificar toda vez

1. O botão está apontando para a URL certa
2. O controller tem o `@GetMapping` ou `@PostMapping` correto
3. O tipo de requisição bate com o tipo esperado
4. O retorno do método (`"todolist/adding"`) bate com o nome do template

---

## 9. Como a tela `index.html` funciona

A página `index.html` é um formulário simples.

### Código

```html
<form method="post" action="/create">
    <label>Tarefa</label><input type="text" name="title"/><br>
    <label>Prazo</label><input type="date" name="deadLine"/><br>
    <button type="submit">Salvar</button>
</form>
```

### O que isso faz

- `name="title"` → envia o texto da tarefa
- `name="deadLine"` → envia a data do prazo
- o formulário vai para `/create`
- o controller salva a tarefa no banco

---

## 10. Como o `input type="date"` funciona

Se você quiser que o usuário escolha a data pelo calendário, use:

```html
<input type="date" name="deadLine">
```

### Por que isso funciona?

Porque o navegador exibe um calendário nativo do input do tipo `date`.

### Como o Spring lê isso?

Como o campo da entidade é `LocalDate deadLine`, o Spring consegue converter automaticamente a string do formulário para `LocalDate`.

---

## 11. Como fazer uma lista com `ul` e `li`

A forma mais simples é mostrar as tarefas como tópicos.

### Código

```html
<ul>
    <li th:each="todo : ${todos}" th:text="${todo.title}"></li>
</ul>
```

### Explicação

- `th:each` percorre a lista `todos`
- `todo` representa cada tarefa da lista
- `th:text` mostra o título da tarefa

---

## 12. Como fazer uma checklist com `checkbox`

Se você quer uma to-do list real, use `checkbox`.

### Código exemplo

```html
<ul>
    <li th:each="todo : ${todos}">
        <label>
            <input type="checkbox" th:checked="${todo.finishedAt != null}">
            <span th:text="${todo.title}"></span>
            <span th:text="${todo.deadLine}"></span>
        </label>
    </li>
</ul>
```

### Explicação

- `th:checked="${todo.finishedAt != null}"`
  - se `finishedAt` estiver preenchido, o checkbox fica marcado

Isso é um jeito de mostrar visualmente se a tarefa está concluída.

---

## 13. Como atualizar o banco quando o usuário marcar o checkbox

Essa é a parte importante para tornar a lista funcional.

### Ideia geral

Quando o usuário alterar o checkbox, o formulário precisa enviar a informação para o backend.

### Passo 1: montar o formulário na view

```html
<form th:action="@{/todo/toggle/{id}(id=${todo.id})}" method="post">
    <input type="checkbox"
           name="finished"
           value="true"
           th:checked="${todo.finishedAt != null}"
           onchange="this.form.submit()">
    <span th:text="${todo.title}"></span>
</form>
```

### Passo 2: criar o método no controller

```java
@PostMapping("/todo/toggle/{id}")
public String toggleTodo(@PathVariable Long id, @RequestParam boolean finished) {
    ToDo todo = toDoRepository.findById(id).orElseThrow();

    if (finished) {
        todo.markAsFinished();
    } else {
        todo.setFinishedAt(null);
    }

    toDoRepository.save(todo);
    return "redirect:/list";
}
```

### O que acontece

- o checkbox envia `finished=true` quando marcado
- o controller busca a tarefa pelo id
- se estiver marcado, chama `markAsFinished()`
- salva no banco com `save()`
- redireciona para a página de lista

---

## 14. O que é o Thymeleaf?

Thymeleaf é uma engine de templates usada com Spring Boot para renderizar HTML dinâmico.

Ele permite misturar HTML com dados vindos do backend.

### Principais símbolos usados

#### `${}`

Pega um valor do backend.

```html
<span th:text="${todo.title}"></span>
```

Significa:

- pega o valor do atributo `title` do objeto `todo`
- coloca na tela

#### `@{}`

Serve para gerar URL/rota.

```html
<form th:action="@{/create}" method="post"></form>
```

Isso monta a rota `/create` no HTML final.

#### `th:*`

São os atributos Thymeleaf que alteram o HTML.

Exemplos:

- `th:text` → coloca texto
- `th:each` → repete
- `th:checked` → marca checkbox
- `th:action` → define action do formulário
- `th:href` → define link

---

## 15. Mini dicionário dos comandos usados

| Comando | Significado | Exemplo |
|--------|-------------|---------|
| `@Controller` | marca a classe como controlador MVC | `@Controller public class TodoController` |
| `@GetMapping("/")` | rota para requisição GET | `@GetMapping("/")` |
| `@PostMapping("/create")` | rota para requisição POST | `@PostMapping("/create")` |
| `@RequestParam` | lê um parâmetro enviado pelo formulário | `@RequestParam String param` |
| `@PathVariable` | lê um valor da URL | `@PathVariable Long id` |
| `ModelAndView` | envia dados e nome da view | `new ModelAndView("todolist/list", ...)` |
| `Map.of(...)` | cria um mapa com dados para a view | `Map.of("todos", toDoRepository.findAll())` |
| `th:each` | repete elementos | `th:each="todo : ${todos}"` |
| `th:text` | imprime texto | `th:text="${todo.title}"` |
| `th:checked` | marca checkbox | `th:checked="${todo.finishedAt != null}"` |
| `th:action` | define ação do formulário | `th:action="@{/create}"` |
| `${...}` | acessa valor do backend | `${todo.title}` |
| `@{...}` | constrói URL | `@{/todo/toggle/{id}(id=${todo.id})}` |

---

## 16. Tutorial passo a passo: como montar a lista de tarefas

### Passo 1: criar a entidade `ToDo`

A classe `ToDo` precisa ter os atributos da tarefa.

### Passo 2: criar o repositório

```java
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
```

### Passo 3: criar o `TodoController`

Crie rotas para:

- `POST /create`
- `GET /list`

### Passo 4: criar o formulário em `index.html`

```html
<form method="post" action="/create">
    <input type="text" name="title" placeholder="Digite a tarefa">
    <input type="date" name="deadLine">
    <button type="submit">Salvar</button>
</form>
```

### Passo 5: criar a página de listagem em `todolist/list.html`

```html
<ul>
    <li th:each="todo : ${todos}">
        <span th:text="${todo.title}"></span>
    </li>
</ul>
```

### Passo 6: testar

- rode a aplicação
- abra `/`
- adicione uma tarefa
- acesse `/list`

---

## 17. Tutorial de exemplo: checklist completo

### `index.html`

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Adicionar tarefa</title>
</head>
<body>
    <form method="post" action="/create">
        <label>Tarefa</label>
        <input type="text" name="title" required>
        <br>
        <label>Prazo</label>
        <input type="date" name="deadLine">
        <br>
        <button type="submit">Salvar</button>
    </form>
</body>
</html>
```

### `todolist/list.html`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Lista de Tarefas</title>
</head>
<body>
    <h1>Lista de Tarefas</h1>

    <ul>
        <li th:each="todo : ${todos}">
            <label>
                <input type="checkbox" th:checked="${todo.finishedAt != null}">
                <span th:text="${todo.title}"></span>
                <span th:text="${todo.deadLine}"></span>
            </label>
        </li>
    </ul>
</body>
</html>
```

---

## 18. Resumo prático do que foi usado no projeto

### MVC

- Model = `ToDo`
- View = `index.html` e `list.html`
- Controller = `TodoController`

### Persistência

- `ToDoRepository` estende `JpaRepository`

### HTML + Thymeleaf

- `th:each` para listar
- `th:text` para mostrar texto
- `th:checked` para checkbox
- `th:action` para formulário
- `${}` para acesso a variáveis do backend
- `@{}` para montar URL

### Inputs web

- `text` para título da tarefa
- `date` para prazo da tarefa

---

## 19. Tabela útil: tipos de comando e onde usar

| Contexto | Comando | Quando usar |
|----------|---------|-------------|
| Mostrar texto na tela | `th:text` | quando o valor vem do backend |
| Repetir elementos | `th:each` | para percorrer listas |
| Marcar checkbox | `th:checked` | quando a tarefa está concluída |
| Definir rota do form | `th:action` | quando o formulário envia dados |
| Criar link | `th:href` | quando o HTML precisa de uma URL dinâmica |
| Acessar variável do backend | `${...}` | para mostrar informações do controller |
| Construir URL | `@{...}` | quando precisa montar uma rota |

---

## 20. Conclusão

Neste projeto, a aplicação funciona assim:

- a página inicial tem um formulário para criar tarefas
- o `controller` recebe a requisição e salva no banco
- a página de listagem usa Thymeleaf para percorrer as tarefas
- o checkbox pode representar o estado da tarefa
- a data pode ser escolhida pelo calendário usando `input type="date"`

Com isso, você já tem uma base sólida para entender:

- o papel de cada pasta do projeto
- como o flow MVC funciona
- como o Thymeleaf trabalha com o HTML
- como o formulário envia dados para o banco

---

## 21. Dica de estudo

Se quiser avançar, o próximo passo natural é aprender:

- `@PutMapping` e `@DeleteMapping`
- edição de tarefas
- exclusão de tarefas
- estilo visual com CSS
- JavaScript para interações mais dinâmicas
- uso de API REST com JSON

---

Se quiser, no próximo passo eu posso transformar esse arquivo em um guia ainda mais “profissional”, com:

1. introdução do projeto
2. arquitetura MVC
3. passo a passo de execução
4. exemplo de `TodoController` completo
5. exemplo de `list.html` pronto para uso
6. checklist de desenvolvimento
