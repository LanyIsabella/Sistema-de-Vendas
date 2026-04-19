package estudo.vendas.model;

public class ItensCompra {
    private int id_item;
    private int quantidade;
    private Produto produto;
    private Compra compra;

    public ItensCompra() {
    }

    public ItensCompra(int id_item, int quantidade, Produto produto, Compra compra) {
        this.id_item = id_item;
        this.quantidade = quantidade;
        this.produto = produto;
        this.compra = compra;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
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
