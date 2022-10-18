package model;

/**
 * @author andre
 */
public class Usuario extends EntidadeDominio{

    private String email;
    private String senha;
    private String confirmarSenha;
    private UsuarioType tipoUsuario;

    /**
     * Esse campo não deve ser persistido no campo, servindo apenas para verificação
     */
    private String senhaAntiga;

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

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public UsuarioType getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(UsuarioType tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isAdministrador(){
        return getTipoUsuario().equals(UsuarioType.ADMINISTRADOR);
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }
}
