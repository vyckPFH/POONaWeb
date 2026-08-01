// package br.edu.ifpr.todolistif.controller;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController // Define que essa classe é um controlador REST do Spring MVC, responsável por lidar com as requisições HTTP e retornar respostas apropriadas em formato JSON ou XML.
// // A anotação @RestController é uma combinação de @Controller e @ResponseBody, o que significa que os métodos desta classe retornarão diretamente os dados (como JSON ou XML) em vez 
// // de retornar uma view (como uma página HTML). Isso é útil para criar APIs RESTful, onde os clientes esperam receber dados estruturados em vez de páginas web.
// @RequestMapping("/helloworld") // Define o mapeamento de URL para os métodos desta classe.
// // A anotação @RequestMapping pode ser usada para mapear URLs específicas para métodos específicos, 
// // permitindo que a aplicação responda a diferentes requisições HTTP (GET, POST, PUT, DELETE) de acordo com a URL solicitada.

// public class helloworldController {
//     // GET /helloworld/get
//     @GetMapping("/get")

//     public String helloWorld() {
//         return "Hello, World!";
//     }

// }
