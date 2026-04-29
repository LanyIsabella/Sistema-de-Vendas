package estudo.vendas.controller;

import estudo.vendas.dao.FornecedorDAO;
import estudo.vendas.model.Fornecedor;

public class FornecedorCONTROLLER {
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();

    // Metodo de salvar fornecedor

    public boolean salvarFornecedor(Fornecedor fornecedor) {
        if (!validarFornecedor(fornecedor)) {
            return false;
        }

        return fornecedorDAO.salvarFornecedor(fornecedor);
    }

    // Metodo de alterar fornecedor

    public boolean alterarFornecedor(Fornecedor fornecedorAntigo, Fornecedor fornecedorNovo) {
        if (!validarFornecedor(fornecedorAntigo)) {
            return false;
        }

        if (!validarFornecedor(fornecedorNovo)) {
            return false;
        }

        return fornecedorDAO.alterarFornecedor(fornecedorAntigo, fornecedorNovo);
    }

    // Metodo de excluir fornecedor

    public boolean excluirFornecedor(String cnpj) {
        if (!validarTexto(cnpj, "CNPJ invalido.")) {
            return false;
        }

        return fornecedorDAO.excluirFornecedor(cnpj);
    }

    // Metodo de pesquisar fornecedor

    public boolean pesquisarFornecedor(String cnpj) {
        if (!validarTexto(cnpj, "CNPJ invalido.")) {
            return false;
        }

        return fornecedorDAO.pesquisarFornecedor(cnpj);
    }

    private boolean validarFornecedor(Fornecedor fornecedor) {
        if (fornecedor == null) {
            System.out.println("Fornecedor invalido.");
            return false;
        }

        if (!validarTexto(fornecedor.getNome_fantasia(), "Nome fantasia invalido.")) {
            return false;
        }

        if (!validarTexto(fornecedor.getRazao_social(), "Razao social invalida.")) {
            return false;
        }

        if (!validarTexto(fornecedor.getCnpj(), "CNPJ invalido.")) {
            return false;
        }

        return true;
    }

    private boolean validarTexto(String texto, String mensagem) {
        if (texto == null || texto.isBlank()) {
            System.out.println(mensagem);
            return false;
        }

        return true;
    }

}
