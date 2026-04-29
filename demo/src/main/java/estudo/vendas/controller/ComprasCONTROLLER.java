package estudo.vendas.controller;

import java.util.List;

import estudo.vendas.dao.CompraDAO;
import estudo.vendas.dao.ItensCompraDAO;
import estudo.vendas.dao.ProdutoDAO;
import estudo.vendas.model.Compra;
import estudo.vendas.model.ItensCompra;
import estudo.vendas.model.Produto;

public class ComprasCONTROLLER {
    private CompraDAO compraDAO = new CompraDAO();
    private ItensCompraDAO itensCompraDAO = new ItensCompraDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean salvarCompra(Compra compra, List<ItensCompra> itensCompra){
        if (itensCompra == null || itensCompra.isEmpty()) {
            System.out.println("Compra sem itens.");
            return false;
        }

        for (ItensCompra item : itensCompra) {
            if (item.getProduto() == null || item.getProduto().getId_produto() == null) {
                System.out.println("Produto invalido no item da compra.");
                return false;
            }

            if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                System.out.println("Quantidade invalida no item da compra.");
                return false;
            }

            if (item.getPreco_unitario() == null || item.getPreco_unitario() <= 0) {
                System.out.println("Preco unitario invalido no item da compra.");
                return false;
            }

        }

        // Salva a compra

        boolean compraSalva = compraDAO.salvarCompra(compra);

        if (!compraSalva) {
            System.out.println("Erro ao salvar compra.");
            return false;
        }


        // Salvando itens de compra

        for (ItensCompra item : itensCompra) {
            item.setCompra(compra);

            boolean itemSalvo = itensCompraDAO.salvarItensCompra(item);

            if (!itemSalvo) {
                System.out.println("Erro ao salvar item da compra.");
                return false;
            }
        }


        // Atualiza o estoque, o campo valor_ultima_compra e o preco_medio

        for (ItensCompra item : itensCompra) {
            int idProduto = item.getProduto().getId_produto();
            float novoPrecoMedio = calcularPrecoMedio(idProduto, item.getQuantidade(), item.getPreco_unitario());
            int novoEstoque = produtoDAO.verificarEstoque(idProduto) + item.getQuantidade();

            boolean estoqueAtualizado = produtoDAO.atualizarEstoqueProduto(idProduto, novoEstoque);
            boolean ultimoValorAtualizado = produtoDAO.atualizarUltimoValorCompra(idProduto, item.getPreco_unitario());
            boolean precoMedioAtualizado = produtoDAO.atualizarPrecoMedioProduto(idProduto, novoPrecoMedio);

            if (!estoqueAtualizado || !ultimoValorAtualizado || !precoMedioAtualizado) {
                System.out.println("Erro ao atualizar produto: " + idProduto);
                return false;
            }
        }

        return true;

    }

    // metodo pra calcular o preco_medio

    private float calcularPrecoMedio(int idProduto, int quantidadeCompra, float precoUnitarioCompra) {
        Produto produto = produtoDAO.buscarProdutoID(idProduto);

        if (produto == null) {
            return precoUnitarioCompra;
        }

        int estoqueAtual = produto.getQtde_estoque() == null ? 0 : produto.getQtde_estoque();
        float precoMedioAtual = produto.getPreco_medio() == null ? 0 : produto.getPreco_medio();
        int estoqueFinal = estoqueAtual + quantidadeCompra;

        if (estoqueFinal <= 0) {
            return precoUnitarioCompra;
        }

        return ((precoMedioAtual * estoqueAtual) + (precoUnitarioCompra * quantidadeCompra)) / estoqueFinal;
    } 

}
