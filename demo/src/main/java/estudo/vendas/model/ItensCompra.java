package estudo.vendas.model;

public class ItensCompra {
    private Integer id_item;
    private Float preco_unitario;
    private Integer quantidade;
    private Produto produto;
    private Compra compra;

    public ItensCompra() {
    }

    public ItensCompra(Integer id_item, Float preco_unitario, Integer quantidade, Produto produto, Compra compra) {
        this.id_item = id_item;
        this.preco_unitario = preco_unitario;
        this.quantidade = quantidade;
        this.produto = produto;
        this.compra = compra;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

}
