package estudo.vendas.model;

public class Cliente {

    private Integer id_cliente;
    private String nome_cliente;
    private String cpf_cliente;
    private String rg_cliente;
    private String endereco;
    private String telefone;

    public Cliente(){

    }

    public Cliente(Integer id_cliente, String nome_cliente, String cpf_cliente, String rg_cliente, String endereco,
            String telefone) {
        this.id_cliente = id_cliente;
        this.nome_cliente = nome_cliente;
        this.cpf_cliente = cpf_cliente;
        this.rg_cliente = rg_cliente;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public String getRg_cliente() {
        return rg_cliente;
    }

    public void setRg_cliente(String rg_cliente) {
        this.rg_cliente = rg_cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
