const baseUrl = 'http://localhost:8080/emug';

function montaUrl(baseUrl, path, params) {
    let url = new URL(baseUrl + "/" +path)
    url.search = new URLSearchParams(params).toString()

    return url;
}

function aplicaMascaraTelefone() {
    let behavior = function (val) {
            return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
        },
        options = {
            onKeyPress: function (val, e, field, options) {
                field.mask(behavior.apply({}, arguments), options);
            }
        };

    $('#telefone').mask(behavior, options);
}

function abrirModal(id) {
    document.getElementById("idRemover").value = id;

    let form = $('#formStatus')
    /*
        Limpa campos caso exista o form de inativação
     */
    if(form != null) {
        $('#categoriaStatus').val("Selecione...");
        $('#justificativa').val("");
    }

    let modalRemover = new bootstrap.Modal(document.getElementById('removeModal'), {
        keyboard: false
    });

    modalRemover.show();
}


function preencheSelectEstado() {
    fetch('https://servicodados.ibge.gov.br/api/v1/localidades/estados/')
        .then(result => result.json())
        .then(result => {
            let estados = document.getElementById("estado")

            result.sort((a, b) => a.nome.localeCompare(b.nome))

            result.forEach(estado => {
                let option = document.createElement("option");
                option.value = estado.nome;
                option.innerHTML = estado.nome;
                option.setAttribute("id-estado", estado.id)

                let estadoAtual = $('#estadoAtual').val()

                if(estadoAtual === estado.nome) {
                    $(option).prop('selected', true)
                    $("#cidade").prop("disabled", false);
                    carregaCidades(estado.id);
                }

                estados.appendChild(option)
            })
        })
}

$('#estado').change(function() {
    let cidades = $("#cidade");

    if(this.value === "") {
        cidades.html("")

        let option = document.createElement("option");
        option.value = "";
        option.innerHTML = "Selecione";

        cidades.append(option)
        cidades.prop("disabled", "disabled")
        return
    }

    let idEstado = $(this).find(":selected").attr("id-estado");

    carregaCidades(idEstado)
})

function carregaCidades(idEstado) {
    let cidades = $("#cidade");
    cidades.html("")
    cidades.prop("disabled", false)

    fetch("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + idEstado +"/municipios")
        .then(result => result.json())
        .then(result => {
            result.sort((a, b) => a.nome.localeCompare(b.nome))

            result.forEach(cidade => {
                let option = document.createElement("option");
                option.value = cidade.nome;
                option.innerHTML = cidade.nome;

                let cidadeAtual = $('#cidadeAtual').val()

                if(cidadeAtual === cidade.nome)
                    $(option).prop('selected', true)

                cidades.append(option)
            })
        })
}

const toBase64 = arquivo => new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.onloadend = () => resolve(reader.result)
    reader.onerror = reject
    reader.readAsDataURL(arquivo)
})

$( "#formStatus" ).submit(function( event ) {
    event.preventDefault();

    let categorias = $('#categoriaStatus').val();
    let justificativa = $('#justificativa').val();

    let erros = []

    if(categorias === '' || categorias === 'Selecione...')
        erros.push('Selecione uma categoria')

    if(justificativa === '')
        erros.push('Digite a justificativa')

    if(erros.length > 0) {
        erros.forEach(erro => {
            let li = document.createElement('li')

            li.innerHTML = erro

            document.getElementById('lista-erros').appendChild(li)
        })

        document.getElementById('erroInativar').style.display = 'block'
        return;
    }

    $('#formStatus')[0].submit()
});

function createNotify(tipo, title, message) {
    return new Notify({
        status: tipo,
        title: title,
        text: message,
        effect: 'fade',
        speed: 300,
        customClass: null,
        customIcon: null,
        showIcon: true,
        showCloseButton: false,
        autoclose: true,
        autotimeout: 3000,
        gap: 20,
        distance: 20,
        type: 1,
        position: 'right top'
    })
}