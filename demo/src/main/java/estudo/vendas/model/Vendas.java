package estudo.vendas.model;

import java.util.Date;

public class Vendas {

    private Integer id_venda;
    private Date data_venda;
    private Float valor_total;
    private Cliente cliente;

    public Vendas(){

    }

    public Vendas(Integer id_venda, Date data_venda, Float valor_total, Cliente cliente) {
        this.id_venda = id_venda;
        this.data_venda = data_venda;
        this.valor_total = valor_total;
        this.cliente = cliente;
    }

    public Integer getId_venda() {
        return id_venda;
    }

    public void setId_venda(Integer id_venda) {
        this.id_venda = id_venda;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(Float valor_total) {
        this.valor_total = valor_total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
