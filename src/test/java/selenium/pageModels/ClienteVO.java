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

    private EnderecoVO enderecoVO;

    public ClienteVO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public static ClienteVO createClienteVOPadrao(){
        ClienteVO cliente = new ClienteVO("email@email.com", "senha123456");

        cliente.setEmail("clienteTeste@teste.com");
        cliente.setSenha("Senha@123");
        cliente.setSenhaConfirmacao("Senha@123");
        cliente.setNome("Maria");
        cliente.setSobrenome("dos Anjos");
        cliente.setCpf("303.799.550-59");
        cliente.setGenero("M");
        cliente.setDtNascimento("12/12/2001");
        cliente.setTelefone("11944506708");
        cliente.setEnderecoVO(EnderecoVO.createEnderecoPadrao());

        return cliente;
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

    public EnderecoVO getEnderecoVO() {
        return enderecoVO;
    }

    public void setEnderecoVO(EnderecoVO enderecoVO) {
        this.enderecoVO = enderecoVO;
    }

}
