const api = "/api/tasks";

/**
 * limpar a tela
 */
function clear() {
    document.getElementById('titulo').value = '';
    document.getElementById('descricao').value = '';
}

/**
 * edita uma tarefa na base
 * @type type
 */
function editTask() {
    let titulo = document.getElementById('titulo').value;
    let descricao = document.getElementById('descricao').value;
    let id = document.getElementById('id').textContent;
    let elementStatus = document.getElementById('status');
    let status = 0;

    let url = `${api}/${id}`;

    if (elementStatus.checked) {
        status = 1;
    }

    let dataForm = {'id': id, 'status': status, 'titulo': titulo, 'descricao': descricao};
    let task = JSON.stringify(dataForm);
    console.log(task);

    fetch(url, {
        method: 'PUT',
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

/**
 * Adiciona a tarefa da base na tela
 * @type type
 */
function loadTask() {

    let params = new URLSearchParams(window.location.search);
    let id = params.get("id");
    let url = `${api}/${id}`;

//    console.log(url);
    // adiciona id a tela
    document.getElementById('id').innerHTML = id;

    fetch(url).then(response => {
        console.log(response);
        response.json().then(task => {
//            console.log(task);
            // adiciona os dados a tela
            document.getElementById('titulo').value = task.titulo;
            document.getElementById('descricao').value = task.descricao;

            if (new String(task.status) === '1') {
                document.getElementById('status').checked = true;
            }

        }).catch(error => {
            console.log(error);
        });
    }).catch(error => {
        console.log(error);
    });
}

// evento ao carregar a tela
window.addEventListener("load", function(event) {
    console.log("Todos os recursos terminaram o carregamento!");

    loadTask();

    // eventos botoes dessa tela
    document.getElementById("btn-edit").addEventListener('click', () => { editTask(); });
    document.getElementById("btn-clear").addEventListener('click', () => { clear(); });
});
