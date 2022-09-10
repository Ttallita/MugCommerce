package selenium.pageModels;

public class EnderecoVO {

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

    public static EnderecoVO createEnderecoPadrao() {
        EnderecoVO enderecoVO = new EnderecoVO();

        enderecoVO.setTpResidencia("Sobrado");
        enderecoVO.setTpLogradouro("Avenida");
        enderecoVO.setLogradouro("Pingo d'água");
        enderecoVO.setBairro("Fernandes");
        enderecoVO.setNumeroEndereco("586");
        enderecoVO.setCep("95044-120");
        enderecoVO.setPais("Brasil");
        enderecoVO.setEstado("RS");
        enderecoVO.setCidade("Caxias do Sul");
        enderecoVO.setApelidoEndereco("Minha casa");
        enderecoVO.setObservacaoEndereco("");

        return enderecoVO;
    }

    public static EnderecoVO createEnderecoVazio() {
        EnderecoVO enderecoVO = new EnderecoVO();

        enderecoVO.setTpResidencia("");
        enderecoVO.setTpLogradouro("");
        enderecoVO.setLogradouro("");
        enderecoVO.setBairro("");
        enderecoVO.setNumeroEndereco("");
        enderecoVO.setCep("");
        enderecoVO.setPais("");
        enderecoVO.setEstado("");
        enderecoVO.setCidade("");
        enderecoVO.setApelidoEndereco("");
        enderecoVO.setObservacaoEndereco("");

        return enderecoVO;
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
}
