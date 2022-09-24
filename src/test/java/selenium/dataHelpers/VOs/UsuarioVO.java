package selenium.dataHelpers.VOs;

public class UsuarioVO {

    private String email;
    private String senha;
    private String senhaConfirmacao;

    /**
     * Cliente padrão de configuração do banco de testes
     * @return
     */
    public static UsuarioVO createUsuarioClientePadrao(){
        UsuarioVO usuario = new UsuarioVO();

        usuario.setEmail("login@teste.com");
        usuario.setSenha("Teste@123");
        usuario.setSenhaConfirmacao("Teste@123");

        return usuario;
    }

    /**
     * Administrador padrão de configuração do banco de testes
     * @return
     */
    public static UsuarioVO createUsuarioAdmPadrao(){
        UsuarioVO usuario = new UsuarioVO();

        usuario.setEmail("loginADM@teste.com");
        usuario.setSenha("SenhaADM@123");
        usuario.setSenhaConfirmacao("SenhaADM@123");

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
