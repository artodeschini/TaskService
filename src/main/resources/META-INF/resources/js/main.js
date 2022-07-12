const api = "/api/tasks";

function createTextCell(newRow, index, value) {
    let cell = newRow.insertCell(index);

    let p = document.createElement('p');
    let b = document.createElement('b');
    b.innerHTML = value;
    p.appendChild(b);

    cell.appendChild(p);
}

function createCellStatus(newRow, index, isStatusConcluido) {
    let cell = newRow.insertCell(index);
    let s = document.createElement('span');


    if ( isStatusConcluido ) {
        s.innerHTML = 'Concluida';

    } else {
        s.innerHTML = 'Em progresso';
    }

    cell.appendChild(s);
}

function loadTask(id) {
    console.log(`/edit.html?id=${id}`);
    window.location.replace(`/edit.html?id=${id}`);
}

/**
 * edita uma tarefa na base o status
 * @type type
 */
function changeStatus(id) {
    let url = `${api}/${id}`;

    fetch(url).then(response => {
        console.log(response);
        response.json().then(task => {

            if (task.status == 1) {
                task.status = 0;
            } else {
                task.status = 1;
            }

            fetch(url, {
                method: 'PUT',
                body: JSON.stringify(task), // string or object
                headers: {
                    'Content-Type': 'application/json', 'Accept': 'application/json'
                }
            }).then(response => {
                console.log(response);
                window.location.replace("/");
            }).catch(error => {
                console.log(error);
            });

        }).catch(error => {
            console.log(error);
        });
    }).catch(error => {
        console.log(error);
    });
}

function deleteTask(id) {
    let url = `${api}/${id}`;

    fetch(url, {
        method: 'DELETE',
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

function createButtonGrid(id, label) {
    let button = document.createElement('button');
    button.innerHTML = label;

    //actions
    if (label === 'Editar') {
        button.addEventListener('click', () => { loadTask(id) });
    } else if (label === 'Deletar') {
        button.addEventListener('click', () => { deleteTask(id) });
    } else if (label === 'Desmarcar Concluida' || label ===  'Marcar como Concluida') {
        button.addEventListener('click', () => { changeStatus(id) });
    }

    return button;
}

function checkStatusConcluido(status) {
    if ( new String(status) == '1' ) {
        return true;
    } else {
        return false;
    }
}

function findTasksAll() {
    //onload page

    let rows = document.getElementById('rows');
    let index = 1;

    fetch(api).then(response => {
        response.json().then(tasks => {
            tasks.forEach(t => {
                // Insert a row at tde end of table
                let newRow = rows.insertRow(index++);

                // status
                let isStatusConcluido = checkStatusConcluido(t.status);

                // elemetros da grid

                //id
                createTextCell(newRow, 0, t.id);

                //status
                createCellStatus(newRow, 1, isStatusConcluido);

                // titulo
                createTextCell(newRow, 2, t.titulo);

                // descricao
                createTextCell(newRow, 3, t.descricao);

                //botoes
                let cellBotoes = newRow.insertCell(4);

                // btn editar
                let btnEdit = createButtonGrid(t.id, 'Editar');
                cellBotoes.appendChild(btnEdit);

                // btn deletar
                let btnDel = createButtonGrid(t.id, 'Deletar');
                cellBotoes.appendChild(btnDel);

                let label = '';
                if ( isStatusConcluido ) {
                    label = 'Desmarcar Concluida';
                } else {
                    label = 'Marcar como Concluida';
                }

                // btn patch
                let btnStatus = createButtonGrid(t.id, label);
                cellBotoes.appendChild(btnStatus);
            });
        });
    }).catch(error => {
        console.log(error);
    });
}
