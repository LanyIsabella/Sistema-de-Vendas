package estudo.vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import estudo.vendas.model.Conexao;
import estudo.vendas.model.ProdutoFornecedor;

public class ProdutoFornecedorDAO {
    public boolean salvarProdutoFornecedor(ProdutoFornecedor produto_fornecedor) {

        String query = "INSERT INTO produto_fornecedor (id_produto, id_fornecedor) VALUES (?, ?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, produto_fornecedor.getId_produto().getId_produto());
            stmt.setInt(2, produto_fornecedor.getId_fornecedor().getId_fornecedor());

            int linhas_afetadas = stmt.executeUpdate();

            return linhas_afetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean alterarProdutoFornecedor(ProdutoFornecedor produto_fornecedor_antigo, ProdutoFornecedor produto_fornecedor_novo) {
        String query = "UPDATE produto_fornecedor SET (id_produto, id_fornecedor) = (?, ?) WHERE (id_produto, id_fornecedor) = (?, ?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, produto_fornecedor_novo.getId_produto().getId_produto());
            stmt.setInt(2, produto_fornecedor_novo.getId_fornecedor().getId_fornecedor());
            stmt.setInt(3, produto_fornecedor_antigo.getId_produto().getId_produto());
            stmt.setInt(4, produto_fornecedor_antigo.getId_fornecedor().getId_fornecedor());

            int linhas_afetadas = stmt.executeUpdate();

            return linhas_afetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean excluirProdutoFornecedor(int id_produto, int id_fornecedor) {
        String query = "DELETE FROM produto_fornecedor WHERE (id_produto, id_fornecedor) = (?, ?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, id_produto);
            stmt.setInt(2, id_fornecedor);

            int linhas_afetadas = stmt.executeUpdate();

            return linhas_afetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean pesquisarProdutoFornecedor(int id_produto, int id_fornecedor) {
        String query = "SELECT 1 FROM produto_fornecedor WHERE (id_produto, id_fornecedor) = (?, ?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, id_produto);
            stmt.setInt(2, id_fornecedor);

            rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
