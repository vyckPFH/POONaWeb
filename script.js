const inputTarefa = document.getElementById('input-tarefa');// input porque é um campo de entrada de texto, e o id é 'input-tarefa' para identificar o elemento no HTML.
const btnAdicionar = document.getElementById('adicionar-tarefa');// botão de adicionar tarefa, com id 'adicionar-tarefa'.
const listaTarefas = document.getElementById('lista-tarefas');// lista de tarefas, com id 'lista-tarefas'.

function adicionarTarefa() {
    const textoDaTarefa = inputTarefa.value;// pega o valor digitado no input
    
    if (textoDaTarefa === '' || textoDaTarefa.trim() === '') {// verifica se o input não está vazio
        alert('Digite uma tarefa!');// alerta se estiver vazio
        return; // para a função aqui
    }
    
    const novaLi = document.createElement('li');// cria um novo elemento <li>
    novaLi.textContent = textoDaTarefa;// define o texto da tarefa
    
    listaTarefas.appendChild(novaLi);// adiciona à lista
    
    inputTarefa.value = '';// limpa o input
    
    inputTarefa.focus();// coloca o foco de volta no input
}

btnAdicionar.addEventListener('click', adicionarTarefa);// quando clica no botão, chama a função adicionarTarefa