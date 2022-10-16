
function setTituloModal(textoTitulo){
    let tituloModal = document.getElementById("modalLabel");
    tituloModal.innerText = textoTitulo;
}

function adicionaItemBodyModal(item){
    let corpoModal = document.getElementById("modalBody");
    item.appendTo(corpoModal);
}

function adicionaFormModal(item){
    let formModal = document.getElementById("formModal");
    item.appendTo(formModal);
}

function adicionaBotaoFooter(botao){
    let modalFooter = document.getElementById("modalFooter");
    botao.appendTo(modalFooter);
}

function limpaModal(){
    let modalFooter = document.getElementById("modalFooter");
    modalFooter.innerHTML = '';

    let modalBody = document.getElementById("modalBody");
    modalBody.innerHTML = '';

    $(`<form id="formModal"></form>`).appendTo(modalBody);
}
