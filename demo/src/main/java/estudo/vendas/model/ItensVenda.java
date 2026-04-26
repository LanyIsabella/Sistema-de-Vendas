package estudo.vendas.model;

public class ItensVenda {

    private Integer id_item;
    private Integer quantidade;
    private Vendas vendas;
    private Produto produto;

    public ItensVenda(){

    }

    public ItensVenda(Integer id_item, Integer quantidade, Vendas vendas, Produto produto) {
        this.id_item = id_item;
        this.quantidade = quantidade;
        this.vendas = vendas;
        this.produto = produto;
    }

    public Integer getId_item() {
        return id_item;
    }

    public void setId_item(Integer id_item) {
        this.id_item = id_item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
