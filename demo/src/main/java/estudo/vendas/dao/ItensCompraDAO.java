package estudo.vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import estudo.vendas.model.Conexao;
import estudo.vendas.model.ItensCompra;

public class ItensCompraDAO {
    public boolean salvarItensCompra(ItensCompra itens_compra) {

        String query = "INSERT INTO itens_compra (id_compra, id_produto, quantidade) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, itens_compra.getCompra().getId_compra());
            stmt.setInt(2, itens_compra.getProduto().getId_produto());
            stmt.setInt(3, itens_compra.getQuantidade());

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


    public boolean alterarItensCompra(ItensCompra item_antigo, ItensCompra item_novo) {
        String query = "UPDATE itens_compra SET (id_compra, id_produto, quantidade) = (?, ?, ?) WHERE (id_item) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, item_novo.getCompra().getId_compra());
            stmt.setInt(2, item_novo.getProduto().getId_produto());
            stmt.setInt(3, item_novo.getQuantidade());
            stmt.setInt(4, item_antigo.getId_item());

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


    public boolean excluirItemCompra(int id_item) {
        String query = "DELETE FROM itens_compra WHERE (id_item) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, id_item);

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


    public boolean pesquisarItemCompra(int id_item) {
        String query = "SELECT 1 FROM itens_compra WHERE (id_item) = (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, id_item);

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
