package estudo.vendas.model;

public class Produto {

    private Integer id_produto;
    private String nome_produto;
    private Float preco_produto;
    private Integer qtde_estoque;
    private Categoria categoria; 
    private float valor_ultima_compra;
    private float valor_ultima_venda;

    public Produto(){

    }

    public Produto(Integer id_produto, String nome_produto, Float preco_produto, Integer qtde_estoque,
            Categoria categoria, float valor_ultima_compra, float valor_ultima_venda) {
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.preco_produto = preco_produto;
        this.qtde_estoque = qtde_estoque;
        this.categoria = categoria;
        this.valor_ultima_compra = valor_ultima_compra;
        this.valor_ultima_venda = valor_ultima_venda;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public Float getPreco_produto() {
        return preco_produto;
    }

    public void setPreco_produto(Float preco_produto) {
        this.preco_produto = preco_produto;
    }

    public Integer getQtde_estoque() {
        return qtde_estoque;
    }

    public void setQtde_estoque(Integer qtde_estoque) {
        this.qtde_estoque = qtde_estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public float getValor_ultima_compra() {
        return valor_ultima_compra;
    }

    public void setValor_ultima_compra(float valor_ultima_compra) {
        this.valor_ultima_compra = valor_ultima_compra;
    }

    public float getValor_ultima_venda() {
        return valor_ultima_venda;
    }

    public void setValor_ultima_venda(float valor_ultima_venda) {
        this.valor_ultima_venda = valor_ultima_venda;
    }
    
}
