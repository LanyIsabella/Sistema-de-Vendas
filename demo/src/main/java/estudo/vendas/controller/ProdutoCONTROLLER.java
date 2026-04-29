package estudo.vendas.controller;

import estudo.vendas.dao.ProdutoDAO;
import estudo.vendas.dao.ProdutoFornecedorDAO;
import estudo.vendas.model.Produto;
import estudo.vendas.model.ProdutoFornecedor;

public class ProdutoCONTROLLER {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private ProdutoFornecedorDAO produtoFornecedorDAO = new ProdutoFornecedorDAO();

    // Metodo de salvar produto com fornecedor

    public boolean salvarProduto(Produto produto, ProdutoFornecedor produtoFornecedor) {
        if (!validarProduto(produto, false)) {
            return false;
        }

        if (!validarFornecedorDoProduto(produtoFornecedor)) {
            return false;
        }

        boolean produtoSalvo = produtoDAO.salvarProduto(produto);

        if (!produtoSalvo || produto.getId_produto() == null) {
            System.out.println("Erro ao salvar produto.");
            return false;
        }

        produtoFornecedor.setId_produto(produto);

        boolean produtoFornecedorSalvo = produtoFornecedorDAO.salvarProdutoFornecedor(produtoFornecedor);

        if (!produtoFornecedorSalvo) {
            System.out.println("Erro ao salvar fornecedor do produto.");
            return false;
        }

        return true;
    }

    // Metodo de alterar produto

    public boolean alterarProduto(Produto produto) {
        if (!validarProduto(produto, true)) {
            return false;
        }

        Produto produtoAntigo = new Produto();
        produtoAntigo.setId_produto(produto.getId_produto());

        return produtoDAO.alterarProduto(produtoAntigo, produto);
    }

    // Metodo de excluir produto

    public boolean excluirProduto(Integer idProduto, Integer idFornecedor) {
        if (!validarId(idProduto, "Produto invalido.")) {
            return false;
        }

        if (!validarId(idFornecedor, "Fornecedor invalido.")) {
            return false;
        }

        boolean produtoFornecedorExcluido = produtoFornecedorDAO.excluirProdutoFornecedor(idProduto, idFornecedor);

        if (!produtoFornecedorExcluido) {
            System.out.println("Erro ao excluir fornecedor do produto.");
            return false;
        }

        return produtoDAO.excluirProduto(idProduto);
    }

    // Metodo de pesquisar produto

    public boolean pesquisarProduto(String nomeProduto) {
        if (nomeProduto == null || nomeProduto.isBlank()) {
            System.out.println("Nome do produto invalido.");
            return false;
        }

        return produtoDAO.pesquisarProduto(nomeProduto);
    }

    // Metodo de salvar relacionamento produto-fornecedor

    public boolean salvarProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
        if (!validarProdutoFornecedor(produtoFornecedor)) {
            return false;
        }

        return produtoFornecedorDAO.salvarProdutoFornecedor(produtoFornecedor);
    }

    // Metodo de alterar relacionamento produto-fornecedor

    public boolean alterarProdutoFornecedor(ProdutoFornecedor produtoFornecedorAntigo, ProdutoFornecedor produtoFornecedorNovo) {
        if (!validarProdutoFornecedor(produtoFornecedorAntigo)) {
            return false;
        }

        if (!validarProdutoFornecedor(produtoFornecedorNovo)) {
            return false;
        }

        return produtoFornecedorDAO.alterarProdutoFornecedor(produtoFornecedorAntigo, produtoFornecedorNovo);
    }

    // Metodo de excluir relacionamento produto-fornecedor

    public boolean excluirProdutoFornecedor(Integer idProduto, Integer idFornecedor) {
        if (!validarId(idProduto, "Produto invalido.")) {
            return false;
        }

        if (!validarId(idFornecedor, "Fornecedor invalido.")) {
            return false;
        }

        return produtoFornecedorDAO.excluirProdutoFornecedor(idProduto, idFornecedor);
    }

    // Metodo de pesquisar relacionamento produto-fornecedor

    public boolean pesquisarProdutoFornecedor(Integer idProduto, Integer idFornecedor) {
        if (!validarId(idProduto, "Produto invalido.")) {
            return false;
        }

        if (!validarId(idFornecedor, "Fornecedor invalido.")) {
            return false;
        }

        return produtoFornecedorDAO.pesquisarProdutoFornecedor(idProduto, idFornecedor);
    }

    private boolean validarProduto(Produto produto, boolean validarId) {
        if (produto == null) {
            System.out.println("Produto invalido.");
            return false;
        }

        if (validarId && !validarId(produto.getId_produto(), "Produto invalido.")) {
            return false;
        }

        if (produto.getNome_produto() == null || produto.getNome_produto().isBlank()) {
            System.out.println("Nome do produto invalido.");
            return false;
        }

        if (produto.getPreco_medio() == null || produto.getPreco_medio() < 0) {
            System.out.println("Preco medio do produto invalido.");
            return false;
        }

        if (produto.getQtde_estoque() == null || produto.getQtde_estoque() < 0) {
            System.out.println("Quantidade em estoque invalida.");
            return false;
        }

        if (produto.getCategoria() == null || produto.getCategoria().getId_categoria() == null) {
            System.out.println("Categoria invalida no produto.");
            return false;
        }

        if (produto.getValor_ultima_compra() == null || produto.getValor_ultima_compra() < 0) {
            System.out.println("Valor da ultima compra invalido.");
            return false;
        }

        if (produto.getValor_ultima_venda() == null || produto.getValor_ultima_venda() < 0) {
            System.out.println("Valor da ultima venda invalido.");
            return false;
        }

        return true;
    }

    private boolean validarFornecedorDoProduto(ProdutoFornecedor produtoFornecedor) {
        if (produtoFornecedor == null) {
            System.out.println("Fornecedor do produto invalido.");
            return false;
        }

        if (produtoFornecedor.getId_fornecedor() == null
                || !validarId(produtoFornecedor.getId_fornecedor().getId_fornecedor(), "Fornecedor invalido.")) {
            return false;
        }

        return true;
    }

    private boolean validarProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
        if (produtoFornecedor == null) {
            System.out.println("Relacionamento produto-fornecedor invalido.");
            return false;
        }

        if (produtoFornecedor.getId_produto() == null
                || !validarId(produtoFornecedor.getId_produto().getId_produto(), "Produto invalido.")) {
            return false;
        }

        if (produtoFornecedor.getId_fornecedor() == null
                || !validarId(produtoFornecedor.getId_fornecedor().getId_fornecedor(), "Fornecedor invalido.")) {
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
