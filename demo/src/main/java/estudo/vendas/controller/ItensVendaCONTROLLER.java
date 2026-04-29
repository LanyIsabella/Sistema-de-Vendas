package estudo.vendas.controller;

import java.util.List;

import estudo.vendas.dao.ItensVendaDAO;
import estudo.vendas.model.ItensVenda;
import estudo.vendas.model.Vendas;

public class ItensVendaCONTROLLER {
    private ItensVendaDAO itensVendaDAO = new ItensVendaDAO();

    // Salvando itens de venda

    public boolean salvarItensVenda(Vendas venda, List<ItensVenda> itensVenda) {
        if (venda == null || !validarId(venda.getId_venda(), "Venda invalida.")) {
            return false;
        }

        if (itensVenda == null || itensVenda.isEmpty()) {
            System.out.println("Venda sem itens.");
            return false;
        }

        for (ItensVenda item : itensVenda) {
            item.setVendas(venda);

            if (!validarItemVenda(item, false)) {
                return false;
            }
        }

        for (ItensVenda item : itensVenda) {
            boolean itemSalvo = itensVendaDAO.salvarItensVenda(item);

            if (!itemSalvo) {
                System.out.println("Erro ao salvar item da venda.");
                return false;
            }
        }

        return true;
    }

    // Alterar itens de venda

    public boolean alterarItemVenda(ItensVenda itemVenda) {
        if (!validarItemVenda(itemVenda, true)) {
            return false;
        }

        ItensVenda itemAntigo = new ItensVenda();
        itemAntigo.setId_item(itemVenda.getId_item());

        return itensVendaDAO.alterarItensVenda(itemAntigo, itemVenda);
    }

    // Excluir itens de venda

    public boolean excluirItemVenda(Integer idItem) {
        if (!validarId(idItem, "Item da venda invalido.")) {
            return false;
        }

        return itensVendaDAO.excluirItemVenda(idItem);
    }

    // Pesquisar itens de venda

    public boolean pesquisarItemVenda(Integer idItem) {
        if (!validarId(idItem, "Item da venda invalido.")) {
            return false;
        }

        return itensVendaDAO.pesquisarItemVenda(idItem);
    }

    private boolean validarItemVenda(ItensVenda item, boolean validarId) {
        if (item == null) {
            System.out.println("Item da venda invalido.");
            return false;
        }

        if (validarId && !validarId(item.getId_item(), "Item da venda invalido.")) {
            return false;
        }

        if (item.getVendas() == null || item.getVendas().getId_venda() == null) {
            System.out.println("Venda invalida no item da venda.");
            return false;
        }

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
