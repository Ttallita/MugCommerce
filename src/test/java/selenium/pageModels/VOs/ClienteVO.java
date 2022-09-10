package selenium.pageModels.VOs;

public class ClienteVO {

    private String nome;
    private String sobrenome;
    private String cpf;
    private String genero;
    private String dtNascimento;
    private String telefone;

    private UsuarioVO usuarioVO;
    private EnderecoVO enderecoVO;

    public static ClienteVO createClienteVOPadrao(){
        ClienteVO cliente = new ClienteVO();

        cliente.setNome("Maria");
        cliente.setSobrenome("dos Anjos");
        cliente.setCpf("303.799.550-59");
        cliente.setGenero("F");
        cliente.setDtNascimento("12/12/2001");
        cliente.setTelefone("11944506708");
        cliente.setUsuarioVO(UsuarioVO.createUsuarioPadrao());
        cliente.setEnderecoVO(EnderecoVO.createEnderecoPadrao());

        return cliente;
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

    public UsuarioVO getUsuarioVO() {
        return usuarioVO;
    }

    public void setUsuarioVO(UsuarioVO usuarioVO) {
        this.usuarioVO = usuarioVO;
    }

}
