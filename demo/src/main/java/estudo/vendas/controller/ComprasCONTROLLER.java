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


    // metodo de alterar compra

    public boolean alterarCompra(Compra compra, List<ItensCompra> itensCompra) {
        if (!validarCompra(compra, true)) {
            return false;
        }

        if (itensCompra == null || itensCompra.isEmpty()) {
            System.out.println("Compra sem itens.");
            return false;
        }

        for (ItensCompra item : itensCompra) {
            item.setCompra(compra);

            if (!validarItemCompra(item, true)) {
                return false;
            }
        }

        Compra compraAntiga = new Compra();
        compraAntiga.setId_compra(compra.getId_compra());

        boolean compraAlterada = compraDAO.alterarCompra(compraAntiga, compra);

        if (!compraAlterada) {
            System.out.println("Erro ao alterar compra.");
            return false;
        }

        for (ItensCompra item : itensCompra) {
            ItensCompra itemAntigo = new ItensCompra();
            itemAntigo.setId_item(item.getId_item());

            boolean itemAlterado = itensCompraDAO.alterarItensCompra(itemAntigo, item);

            if (!itemAlterado) {
                System.out.println("Erro ao alterar item da compra.");
                return false;
            }
        }

        return true;
    }

    // metodo de excluir compra

    public boolean excluirCompra(Integer idCompra) {
        if (!validarId(idCompra, "Compra invalida.")) {
            return false;
        }

        Compra compra = new Compra();
        compra.setId_compra(idCompra);

        List<ItensCompra> itensCompra = itensCompraDAO.listarItensCompraPorCompra(compra);

        for (ItensCompra item : itensCompra) {
            boolean itemExcluido = itensCompraDAO.excluirItemCompra(item.getId_item());

            if (!itemExcluido) {
                System.out.println("Erro ao excluir item da compra.");
                return false;
            }
        }

        return compraDAO.excluirCompra(idCompra);
    }

    // metodo de pesquisar

    public boolean pesquisarCompra(Integer idCompra) {
        if (!validarId(idCompra, "Compra invalida.")) {
            return false;
        }

        return compraDAO.pesquisarCompra(idCompra);
    }

    private boolean validarCompra(Compra compra, boolean validarId) {
        if (compra == null) {
            System.out.println("Compra invalida.");
            return false;
        }

        if (validarId && !validarId(compra.getId_compra(), "Compra invalida.")) {
            return false;
        }

        if (compra.getData_compra() == null) {
            System.out.println("Data da compra invalida.");
            return false;
        }

        if (compra.getValor_total() == null || compra.getValor_total() <= 0) {
            System.out.println("Valor total da compra invalido.");
            return false;
        }

        if (compra.getFornecedor() == null || compra.getFornecedor().getId_fornecedor() == null) {
            System.out.println("Fornecedor invalido na compra.");
            return false;
        }

        return true;
    }

    private boolean validarItemCompra(ItensCompra item, boolean validarId) {
        if (item == null) {
            System.out.println("Item da compra invalido.");
            return false;
        }

        if (validarId && !validarId(item.getId_item(), "Item da compra invalido.")) {
            return false;
        }

        if (item.getCompra() == null || item.getCompra().getId_compra() == null) {
            System.out.println("Compra invalida no item da compra.");
            return false;
        }

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

        return true;
    }

    private boolean validarId(Integer id, String mensagem) {
        if (id == null || id <= 0) {
            System.out.println(mensagem);
            return false;
        }

        return true;

    }

}
