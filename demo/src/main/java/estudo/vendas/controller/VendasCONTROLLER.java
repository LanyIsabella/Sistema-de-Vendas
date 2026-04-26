package estudo.vendas.controller;

import java.util.List;

import estudo.vendas.dao.ItensVendaDAO;
import estudo.vendas.dao.ProdutoDAO;
import estudo.vendas.dao.VendasDAO;
import estudo.vendas.model.ItensVenda;
import estudo.vendas.model.Produto;
import estudo.vendas.model.Vendas;

public class VendasCONTROLLER {
    private VendasDAO vendasDAO = new VendasDAO();
    private ItensVendaDAO itensVendaDAO = new ItensVendaDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean verificarEstoque(List<ItensVenda> itens) {

        // Ao fazer uma venda, o sistema deve validar se tal produto tem
        // estoque maior ou igual a 1. Se não tiver, a venda não pode ser
        // executada e a operação de venda abortada.

        for (ItensVenda item : itens) {
            Produto produtoBanco = produtoDAO.buscarProdutoID(item.getProduto().getId_produto());

            if (produtoBanco == null) {
                System.out.println("Produto não encontrado.");
                return false;
            }

            if (item.getQuantidade() <= 0) {
                System.out.println("Quantidade inválida.");
                return false;
            }

            if (item.getQuantidade() > produtoBanco.getQtde_estoque()) {
                System.out.println("Estoque insuficiente para o produto: " + produtoBanco.getNome_produto());
                return false;
            }
        }

        return true;
    }

    public boolean verificarVendasCliente(int id_cliente){

        // Ao fazer uma venda, o sistema deve verificar quantas vendas já
        // foram realizadas para o cliente Flávio Vilela. Se já foram
        // realizadas mais de 3 vendas, exibir uma mensagem de alerta
        // dizendo que atingiu a quantidade de vendas por cliente e
        // abortar a operação.

        int quantidade_vendas = vendasDAO.listarVendasCliente(id_cliente);

        if (quantidade_vendas >= 3) {
            System.err.println("Limite de vendas por usuário excedido.");
            return false;
        }

        return true;

    }


    public boolean atualizarEstoque(int id_produto, int quantidadeVendida) {

        // Ao fazer uma venda, o sistema deve atualizar o estoque, ou seja,
        // subtrair a quantidade vendida do estoque atual. 

        Produto produtoBanco = produtoDAO.buscarProdutoID(id_produto);

        if (produtoBanco == null) {
            System.out.println("Produto não encontrado.");
            return false;
        }

        int estoqueAtual = produtoBanco.getQtde_estoque();
        int novoEstoque = estoqueAtual - quantidadeVendida;

        if (novoEstoque < 0) {
            System.out.println("Estoque não pode ficar negativo.");
            return false;
        }

        return produtoDAO.atualizarEstoqueProduto(id_produto, novoEstoque);
    }

    public float calcularValorTotal(List<ItensVenda> itens) {

        // Calcular valor_total de vendas.

        float total = 0;

        for (ItensVenda item : itens) {
            Produto produtoBanco = produtoDAO.buscarProdutoID(item.getProduto().getId_produto());

            if (produtoBanco != null) {
                total += produtoBanco.getPreco_produto() * item.getQuantidade();
            }
        }

        return total;
    }

    public boolean realizarVenda(Vendas venda, List<ItensVenda> itens) {

        if (!verificarVendasCliente(venda.getCliente().getId_cliente())) {
            return false;
        }

        if (!verificarEstoque(itens)) {
            return false;
        }

        float total = calcularValorTotal(itens);
        venda.setValor_total(total);

        boolean vendaSalva = vendasDAO.salvarVenda(venda);

        if (!vendaSalva) {
            System.out.println("Erro ao salvar venda.");
            return false;
        }

        if (venda.getId_venda() == null) {
            System.out.println("Erro ao obter ID da venda.");
            return false;
        }

        for (ItensVenda item : itens) {
            item.setVendas(venda);

            boolean itemSalvo = itensVendaDAO.salvarItensVenda(item);

            if (!itemSalvo) {
                System.out.println("Erro ao salvar item da venda.");
                return false;
            }

            boolean estoqueAtualizado = atualizarEstoque(
                item.getProduto().getId_produto(),
                item.getQuantidade()
            );

            if (!estoqueAtualizado) {
                System.out.println("Erro ao atualizar estoque.");
                return false;
            }
        }

        System.out.println("Venda realizada com sucesso.");
        return true;
    }
}




    


