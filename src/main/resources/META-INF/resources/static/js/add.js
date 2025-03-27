const api = "/api/tasks";

/**
 * limpar a tela
 */
function clear() {
    document.getElementById('titulo').value = '';
    document.getElementById('descricao').value = '';
}


/**
 * Adiciona uma tarefa na base
 * @type type
 */
function addTask() {
    let titulo = document.getElementById('titulo').value;
    let descricao = document.getElementById('descricao').value;

    let dataForm = {'status': 0, 'titulo': titulo, 'descricao': descricao};
    let task = JSON.stringify(dataForm);
    console.log(task);

    fetch(api, {
        method: 'POST',
        body: task, // string or object
        headers: {
            'Content-Type': 'application/json', 'Accept': 'application/json'
        }
    }).then(response => {
        console.log(response);
        window.location.replace("/");
    }).catch(error => {
        console.log(error);
    });
}

// evento ao carregar a tela
window.addEventListener("load", function(event) {
    console.log("Todos os recursos terminaram o carregamento!");

    // eventos botoes dessa tela
    document.getElementById("btn-add").addEventListener('click', () => { addTask(); });
    document.getElementById("btn-clear").addEventListener('click', () => { clear(); });
});
