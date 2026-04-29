package estudo.vendas.controller;

import estudo.vendas.dao.CategoriaDAO;
import estudo.vendas.model.Categoria;

public class CategoriaCONTROLLER {
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    // Metodo de salvar categoria

    public boolean salvarCategoria(Categoria categoria) {
        if (!validarCategoria(categoria)) {
            return false;
        }

        return categoriaDAO.salvarCategoria(categoria);
    }

    // Metodo de alterar categoria

    public boolean alterarCategoria(Categoria categoriaAntiga, Categoria categoriaNova) {
        if (!validarCategoria(categoriaAntiga)) {
            return false;
        }

        if (!validarCategoria(categoriaNova)) {
            return false;
        }

        return categoriaDAO.alterarCategoria(categoriaAntiga, categoriaNova);
    }

    // Metodo de excluir categoria

    public boolean excluirCategoria(String nomeCategoria) {
        if (!validarTexto(nomeCategoria, "Nome da categoria invalido.")) {
            return false;
        }

        return categoriaDAO.excluirCategoria(nomeCategoria);
    }

    // Metodo de pesquisar categoria

    public boolean pesquisarCategoria(String nomeCategoria) {
        if (!validarTexto(nomeCategoria, "Nome da categoria invalido.")) {
            return false;
        }

        return categoriaDAO.pesquisarCategoria(nomeCategoria);
    }

    private boolean validarCategoria(Categoria categoria) {
        if (categoria == null) {
            System.out.println("Categoria invalida.");
            return false;
        }

        if (!validarTexto(categoria.getNome_categoria(), "Nome da categoria invalido.")) {
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
