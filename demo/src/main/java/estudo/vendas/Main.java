package estudo.vendas;

import java.util.ArrayList;
import java.util.List;

import estudo.vendas.controller.VendasCONTROLLER;
import estudo.vendas.model.Cliente;
import estudo.vendas.model.ItensVenda;
import estudo.vendas.model.Produto;
import estudo.vendas.model.Vendas;

public class Main {
    public static void main(String[] args) {
        VendasCONTROLLER controller = new VendasCONTROLLER();

        // Cliente
        Cliente cliente = new Cliente();
        cliente.setId_cliente(1);

        // Venda
        Vendas venda = new Vendas();
        venda.setCliente(cliente);
        venda.setData_venda(new java.util.Date());

        // Produto 1
        Produto produto1 = new Produto();
        produto1.setId_produto(1);

        ItensVenda item1 = new ItensVenda();
        item1.setProduto(produto1);
        item1.setQuantidade(2);

        // Produto 2
        Produto produto2 = new Produto();
        produto2.setId_produto(2);

        ItensVenda item2 = new ItensVenda();
        item2.setProduto(produto2);
        item2.setQuantidade(1);

        // Lista de itens
        List<ItensVenda> itens = new ArrayList<>();
        itens.add(item1);
        itens.add(item2);

        // Executar venda
        boolean sucesso = controller.realizarVenda(venda, itens);

        if (sucesso) {
            System.out.println("Venda concluída.");
        } else {
            System.out.println("Erro na venda.");
        }
    }
}