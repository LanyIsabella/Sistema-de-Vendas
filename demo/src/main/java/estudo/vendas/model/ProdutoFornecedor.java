package estudo.vendas.model;

public class ProdutoFornecedor {

    private Produto id_produto;
    private Fornecedor id_fornecedor;

    public ProdutoFornecedor(){

    }

    public ProdutoFornecedor(Produto id_produto, Fornecedor id_fornecedor) {
        this.id_produto = id_produto;
        this.id_fornecedor = id_fornecedor;
    }

    public Produto getId_produto() {
        return id_produto;
    }

    public void setId_produto(Produto id_produto) {
        this.id_produto = id_produto;
    }

    public Fornecedor getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(Fornecedor id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

}
