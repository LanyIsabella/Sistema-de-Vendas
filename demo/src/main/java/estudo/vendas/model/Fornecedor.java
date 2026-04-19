package estudo.vendas.model;

public class Fornecedor {

    private Integer id_fornecedor;
    private String nome_fantasia;
    private String razao_social;
    private String cnpj;

    public Fornecedor(){

    }

    public Fornecedor(Integer id_fornecedor, String nome_fantasia, String razao_social, String cnpj) {
        this.id_fornecedor = id_fornecedor;
        this.nome_fantasia = nome_fantasia;
        this.razao_social = razao_social;
        this.cnpj = cnpj;
    }

    public Integer getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(Integer id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
