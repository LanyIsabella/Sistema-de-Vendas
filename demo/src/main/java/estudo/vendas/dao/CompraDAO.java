package estudo.vendas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import estudo.vendas.model.Compra;
import estudo.vendas.model.Conexao;

public class CompraDAO {
    public boolean salvarCompra(Compra compra) {

        String query = "INSERT INTO compra (data_compra, valor_total, id_fornecedor) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setDate(1, new Date(compra.getData_compra().getTime()));
            stmt.setFloat(2, compra.getValor_total());
            stmt.setInt(3, compra.getFornecedor().getId_fornecedor());

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


    public boolean alterarCompra(Compra compra_antiga, Compra compra_nova) {
        String query = "UPDATE compra SET (data_compra, valor_total, id_fornecedor) = (?, ?, ?) WHERE (id_compra) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setDate(1, new Date(compra_nova.getData_compra().getTime()));
            stmt.setFloat(2, compra_nova.getValor_total());
            stmt.setInt(3, compra_nova.getFornecedor().getId_fornecedor());
            stmt.setInt(4, compra_antiga.getId_compra());

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


    public boolean excluirCompra(int id_compra) {
        String query = "DELETE FROM compra WHERE (id_compra) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, id_compra);

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


    public boolean pesquisarCompra(int id_compra) {
        String query = "SELECT 1 FROM compra WHERE (id_compra) = (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, id_compra);

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
