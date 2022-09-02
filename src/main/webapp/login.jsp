<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="assets/css/style.css"/>
    </head>
    <body>
        <div class="container-fluid d-flex h-100 align-items-center justify-content-center">
            <div class="row">

                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="text-center">Login</h3>
                            <form>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input id="email" type="email" class="form-control" name="email" aria-describedby="emailHelp">
                                </div>
                                <div class="mb-3">
                                    <label for="senha" class="form-label">Senha</label>
                                    <input id="senha" type="password" class="form-control" name="senha">
                                </div>
                                <div class="mb-3">
                                    <label for="senhaConfirmacao" class="form-label">Confirmar Senha</label>
                                    <input id="senhaConfirmacao" type="password" class="form-control" name="senhaConfirmacao">
                                </div>
                                <div class="d-grid gap-2 col-8 mx-auto align-middle">
                                    <a href="index.jsp">
                                        <button class="btn btn-primary" type="button" name="botaoLogin">Fazer Login</button>
                                    </a>
                                </div>
                            </form>
                            Não possui conta? <a href="cadastroCliente.jsp" name="botaoCadastro">Cadastre-se</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="bg-dark text-center text-white">
            &copy;Todos direitos reservados André e Tallita
        </footer>
    </body>
    <script src="webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
</html>