package estudo.vendas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import estudo.vendas.model.Conexao;
import estudo.vendas.model.Vendas;

public class VendasDAO {
    public boolean salvarVenda(Vendas vendas) {

        String query = "INSERT INTO vendas (data_venda, valor_total, id_cliente) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setDate(1, new Date(vendas.getData_venda().getTime()));
            stmt.setFloat(2, vendas.getValor_total());
            stmt.setInt(3, vendas.getCliente().getId_cliente());

            int linhas_afetadas = stmt.executeUpdate();

            if (linhas_afetadas > 0) {
                rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    vendas.setId_venda(rs.getInt(1));
                }

                return true;
            }

            return false;
        
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


    public boolean alterarVenda(Vendas venda_antiga, Vendas venda_nova) {
        String query = "UPDATE vendas SET (data_venda, valor_total, id_cliente) = (?, ?, ?) WHERE (id_venda) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setDate(1, new Date(venda_nova.getData_venda().getTime()));
            stmt.setFloat(2, venda_nova.getValor_total());
            stmt.setInt(3, venda_nova.getCliente().getId_cliente());
            stmt.setInt(4, venda_antiga.getId_venda());

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


    public boolean excluirVenda(int id_venda) {
        String query = "DELETE FROM vendas WHERE (id_venda) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, id_venda);

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


    public boolean pesquisarVenda(int id_venda) {
        String query = "SELECT * FROM vendas WHERE (id_venda) = (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, id_venda);

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

    public int listarVendasCliente(int id_cliente) {
        String query = "SELECT COUNT(*) FROM vendas WHERE (id_cliente) = (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
        Connection conn = Conexao.getConnection();
        stmt = conn.prepareStatement(query);

        stmt.setInt(1, id_cliente);

        rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
}
