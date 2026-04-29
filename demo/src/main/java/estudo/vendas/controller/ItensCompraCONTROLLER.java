package estudo.vendas.controller;

import java.util.List;

import estudo.vendas.dao.ItensCompraDAO;
import estudo.vendas.model.Compra;
import estudo.vendas.model.ItensCompra;

public class ItensCompraCONTROLLER {

    private ItensCompraDAO itensCompraDAO = new ItensCompraDAO();
    
    // Salvando itens de compra
    public boolean salvarItensCompra(Compra compra, List<ItensCompra> itensCompra) {
        if (compra == null || !validarId(compra.getId_compra(), "Compra invalida.")) {
            return false;
        }

        if (itensCompra == null || itensCompra.isEmpty()) {
            System.out.println("Compra sem itens.");
            return false;
        }

        for (ItensCompra item : itensCompra) {
            item.setCompra(compra);

            if (!validarItemCompra(item, false)) {
                return false;
            }

        }

        for (ItensCompra item : itensCompra) {
            boolean itemSalvo = itensCompraDAO.salvarItensCompra(item);

            if (!itemSalvo) {
                System.out.println("Erro ao salvar item da compra.");
                return false;
            }
        }
        return true;
    }

    // alterar itens de compra

    public boolean alterarItemCompra(ItensCompra itemCompra) {
        if (!validarItemCompra(itemCompra, true)) {
            return false;
        }

        ItensCompra itemAntigo = new ItensCompra();
        itemAntigo.setId_item(itemCompra.getId_item());

        return itensCompraDAO.alterarItensCompra(itemAntigo, itemCompra);
    }

    // excluir itens de compra

    public boolean excluirItemCompra(Integer idItem) {
        if (!validarId(idItem, "Item da compra invalido.")) {
            return false;
        }

        return itensCompraDAO.excluirItemCompra(idItem);
    }

    // pesquisar itens de compra

    public boolean pesquisarItemCompra(Integer idItem) {
        if (!validarId(idItem, "Item da compra invalido.")) {
            return false;
        }

        return itensCompraDAO.pesquisarItemCompra(idItem);
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
