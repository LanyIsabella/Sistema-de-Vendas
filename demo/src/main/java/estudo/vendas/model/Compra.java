package estudo.vendas.model;

import java.util.Date;

public class Compra {
    private Integer id_compra;
    private Date data_compra;
    private Float valor_total;
    private Fornecedor fornecedor;

    public Compra() {
    }

    public Compra(Integer id_compra, Date data_compra, Float valor_total, Fornecedor fornecedor) {
        this.id_compra = id_compra;
        this.data_compra = data_compra;
        this.valor_total = valor_total;
        this.fornecedor = fornecedor;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(Float valor_total) {
        this.valor_total = valor_total;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
}
