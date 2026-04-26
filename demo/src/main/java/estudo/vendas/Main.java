package estudo.vendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import estudo.vendas.dao.CategoriaDAO;
import estudo.vendas.dao.ClienteDAO;
import estudo.vendas.dao.FornecedorDAO;
import estudo.vendas.dao.ProdutoDAO;
import estudo.vendas.model.Categoria;
import estudo.vendas.model.Cliente;
import estudo.vendas.model.Conexao;
import estudo.vendas.model.Fornecedor;
import estudo.vendas.model.Produto;

public class Main {
    public static void main(String[] args) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();

        long identificador = System.currentTimeMillis() % 10_000_000_000L;

        String nomeCategoria = "Categoria Teste " + identificador;
        String nomeProduto = "Produto Teste " + identificador;
        String cpfCliente = String.format("%011d", identificador);
        String rgCliente = "RG" + identificador;
        String cnpjFornecedor = String.format("%014d", identificador);

        try {
            Categoria categoria = new Categoria();
            categoria.setNome_categoria(nomeCategoria);

            boolean categoriaInserida = categoriaDAO.salvarCategoria(categoria);
            imprimirResultado("CategoriaDAO.salvarCategoria", categoriaInserida);

            Integer idCategoria = buscarIdCategoriaPorNome(nomeCategoria);
            if (idCategoria == null) {
                System.out.println("ProdutoDAO.salvarProduto: NAO EXECUTADO - categoria de teste nao encontrada.");
            } else {
                categoria.setId_categoria(idCategoria);

                Produto produto = new Produto();
                produto.setNome_produto(nomeProduto);
                produto.setPreco_produto(49.90f);
                produto.setQtde_estoque(25);
                produto.setCategoria(categoria);
                produto.setValor_ultima_compra(30.00f);
                produto.setValor_ultima_venda(49.90f);

                boolean produtoInserido = produtoDAO.salvarProduto(produto);
                imprimirResultado("ProdutoDAO.salvarProduto", produtoInserido);
            }

            Cliente cliente = new Cliente();
            cliente.setNome_cliente("Cliente Teste " + identificador);
            cliente.setCpf_cliente(cpfCliente);
            cliente.setRg_cliente(rgCliente);
            cliente.setEndereco("Rua de Teste, 123");
            cliente.setTelefone("64999999999");

            boolean clienteInserido = clienteDAO.salvarCliente(cliente);
            imprimirResultado("ClienteDAO.salvarCliente", clienteInserido);

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome_fantasia("Fornecedor Teste " + identificador);
            fornecedor.setRazao_social("Fornecedor Teste LTDA " + identificador);
            fornecedor.setCnpj(cnpjFornecedor);

            boolean fornecedorInserido = fornecedorDAO.salvarFornecedor(fornecedor);
            imprimirResultado("FornecedorDAO.salvarFornecedor", fornecedorInserido);

        } catch (SQLException e) {
            System.out.println("Erro ao executar teste de insercao.");
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao();
        }
    }

    private static Integer buscarIdCategoriaPorNome(String nomeCategoria) throws SQLException {
        String query = "SELECT id_categoria FROM categoria WHERE nome_categoria = ? ORDER BY id_categoria DESC LIMIT 1";

        Connection conn = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nomeCategoria);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_categoria");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return null;
    }

    private static void imprimirResultado(String teste, boolean sucesso) {
        if (sucesso) {
            System.out.println(teste + ": OK");
        } else {
            System.out.println(teste + ": FALHOU");
        }
    }
}
