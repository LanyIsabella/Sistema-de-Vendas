package estudo.vendas.model;

public class ItensVenda {

    private Integer id_item;
    private Float preco_unitario;
    private Integer quantidade;
    private Vendas vendas;
    private Produto produto;

    public ItensVenda(){

    }

    public ItensVenda(Integer id_item, Float preco_unitario, Integer quantidade, Vendas vendas, Produto produto) {
        this.id_item = id_item;
        this.preco_unitario = preco_unitario;
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

    public Float getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(Float preco_unitario) {
        this.preco_unitario = preco_unitario;
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
