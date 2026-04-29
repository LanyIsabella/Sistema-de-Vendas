package estudo.vendas.controller;

import java.util.List;

import estudo.vendas.dao.ItensVendaDAO;
import estudo.vendas.dao.ProdutoDAO;
import estudo.vendas.dao.VendasDAO;
import estudo.vendas.model.ItensVenda;
import estudo.vendas.model.Vendas;

public class VendasCONTROLLER {
    private VendasDAO vendasDAO = new VendasDAO();
    private ItensVendaDAO itensVendaDAO = new ItensVendaDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean salvarVenda(Vendas venda, List<ItensVenda> itensVenda) {
        if (itensVenda == null || itensVenda.isEmpty()) {
            System.out.println("Venda sem itens.");
            return false;
        }

        for (ItensVenda item : itensVenda) {
            if (item.getProduto() == null || item.getProduto().getId_produto() == null) {
                System.out.println("Produto invalido no item da venda.");
                return false;
            }

            if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                System.out.println("Quantidade invalida no item da venda.");
                return false;
            }

            if (item.getPreco_unitario() == null || item.getPreco_unitario() <= 0) {
                System.out.println("Preco unitario invalido no item da venda.");
                return false;
            }

            // Verifica o estoque de produto na base

            int idProduto = item.getProduto().getId_produto();
            int estoque = produtoDAO.verificarEstoque(idProduto);

            if (item.getQuantidade() > estoque || estoque < 1) {
                System.out.println("Estoque insuficiente para o produto: " + idProduto);
                return false;
            }
        }

        // Verifica a qtde de vendas por cpf no mes

        int qtdeVendas = vendasDAO.listarVendasClienteMes(venda.getCliente().getCpf_cliente());

        if (qtdeVendas > 3) {
            System.out.println("Cliente ja atingiu o limite de 3 vendas no mes.");
            return false;
        }

        // Calcula o valor total da venda

        venda.setValor_total(calcularValorTotal(itensVenda));

        // Salva a venda

        boolean vendaSalva = vendasDAO.salvarVenda(venda);

        if (!vendaSalva || venda.getId_venda() == null) {
            System.out.println("Erro ao salvar venda.");
            return false;
        }

        // Salva itens da venda

        for (ItensVenda item : itensVenda) {
            item.setVendas(venda);

            boolean itemSalvo = itensVendaDAO.salvarItensVenda(item);

            if (!itemSalvo) {
                System.out.println("Erro ao salvar item da venda.");
                return false;
            }
        }

        // Atualiza o estoque e o campo valor_ultima_venda

        for (ItensVenda item : itensVenda) {
            int idProduto = item.getProduto().getId_produto();
            int novoEstoque = produtoDAO.verificarEstoque(idProduto) - item.getQuantidade();

            boolean estoqueAtualizado = produtoDAO.atualizarEstoqueProduto(idProduto, novoEstoque);
            boolean ultimoValorAtualizado = produtoDAO.atualizarUltimoValorVenda(idProduto, item.getPreco_unitario());

            if (!estoqueAtualizado || !ultimoValorAtualizado) {
                System.out.println("Erro ao atualizar produto: " + idProduto);
                return false;
            }
        }

        return true;
    }

    // Metodo pra calcular valor total

    private float calcularValorTotal(List<ItensVenda> itensVenda) {
        float total = 0;

        for (ItensVenda item : itensVenda) {
            total += item.getPreco_unitario() * item.getQuantidade();
        }

        return total;
    }
}
