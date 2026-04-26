package estudo.vendas.controller;

import java.util.List;

import estudo.vendas.dao.CompraDAO;
import estudo.vendas.dao.ItensCompraDAO;
import estudo.vendas.dao.ProdutoDAO;
import estudo.vendas.model.Compra;
import estudo.vendas.model.ItensCompra;
import estudo.vendas.model.ItensVenda;

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
                System.out.println("Produto invalido no item da venda.");
                return false;
            }

            if (item.getQuantidade() || item.getQuantidade() <= 0) {
                System.out.println("Quantidade invalida no item da venda.");
                return false;
            }

            if (item.getPreco_unitario() == null || item.getPreco_unitario() <= 0) {
                System.out.println("Preco unitario invalido no item da venda.");
                return false;
            }

        }

        // Salva a compra

        boolean compraSalva = compraDAO.salvarCompra(compra);


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
            int novoEstoque = produtoDAO.verificarEstoque(idProduto) + item.getQuantidade();

            boolean estoqueAtualizado = produtoDAO.atualizarEstoqueProduto(idProduto, novoEstoque);
            boolean ultimoValorAtualizado = produtoDAO.atualizarUltimoValorCompra(idProduto, item.getPreco_unitario());

            if (!estoqueAtualizado || !ultimoValorAtualizado) {
                System.out.println("Erro ao atualizar produto: " + idProduto);
                return false;
            }
        }

        return true;

    }

    private float calcularPrecoMedio() {

        // CALCULAR PRECO_MEDIO
        
    } 

}
