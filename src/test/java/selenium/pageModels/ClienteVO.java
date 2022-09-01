package selenium.pageModels;

public class ClienteVO {

    private String email;
    private String senha;
    private String senhaConfirmacao;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String genero;
    private String dtNascimento;
    private String telefone;
    private String tpResidencia;
    private String tpLogradouro;
    private String logradouro;
    private String bairro;
    private String numeroEndereco;
    private String cep;
    private String pais;
    private String estado;
    private String cidade;
    private String apelidoEndereco;
    private String observacaoEndereco;

    public ClienteVO(String email, String senha) {
        this.email = email;
        this.senha = senha;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTpResidencia() {
        return tpResidencia;
    }

    public void setTpResidencia(String tpResidencia) {
        this.tpResidencia = tpResidencia;
    }

    public String getTpLogradouro() {
        return tpLogradouro;
    }

    public void setTpLogradouro(String tpLogradouro) {
        this.tpLogradouro = tpLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getApelidoEndereco() {
        return apelidoEndereco;
    }

    public void setApelidoEndereco(String apelidoEndereco) {
        this.apelidoEndereco = apelidoEndereco;
    }

    public String getObservacaoEndereco() {
        return observacaoEndereco;
    }

    public void setObservacaoEndereco(String observacaoEndereco) {
        this.observacaoEndereco = observacaoEndereco;
    }

//    String email, String senha, String senhaConfirmacao, String nome,
//    String sobrenome,  String cpf, String genero, String dtNascimento, String telefone,
//    String tpResidencia, String tpLogradouro, String logradouro, String bairro, String numeroEndereco,
//    String cep, String pais, String estado, String cidade, String apelidoEndereco, String observacaoEndereco
}
