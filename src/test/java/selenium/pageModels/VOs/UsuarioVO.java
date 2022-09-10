package selenium.pageModels.VOs;

public class UsuarioVO {

    private String email;
    private String senha;
    private String senhaConfirmacao;

    public static UsuarioVO createUsuarioPadrao(){
        UsuarioVO usuario = new UsuarioVO();

        usuario.setEmail("clienteTeste@teste.com");
        usuario.setSenha("Senha@123");
        usuario.setSenhaConfirmacao("Senha@123");

        return usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaConfirmacao() {
        return senhaConfirmacao;
    }

    public void setSenhaConfirmacao(String senhaConfirmacao) {
        this.senhaConfirmacao = senhaConfirmacao;
    }
}
