package estudo.vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import estudo.vendas.model.Conexao;
import estudo.vendas.model.Fornecedor;

public class FornecedorDAO {
    public boolean salvarFornecedor(Fornecedor fornecedor) {

        String query = "INSERT INTO fornecedor (nome_fantasia, razao_social, cnpj) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, fornecedor.getNome_fantasia());
            stmt.setString(2, fornecedor.getRazao_social());
            stmt.setString(3, fornecedor.getCnpj());

            int linhas_afetadas = stmt.executeUpdate();

            if (linhas_afetadas > 0) {
                rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    fornecedor.setId_fornecedor(rs.getInt(1));
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


    public boolean alterarFornecedor(Fornecedor fornecedor_antigo, Fornecedor fornecedor_novo) {
        String query = "UPDATE fornecedor SET (nome_fantasia, razao_social, cnpj) = (?, ?, ?) WHERE (cnpj) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setString(1, fornecedor_novo.getNome_fantasia());
            stmt.setString(2, fornecedor_novo.getRazao_social());
            stmt.setString(3, fornecedor_novo.getCnpj());
            stmt.setString(4, fornecedor_antigo.getCnpj());

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


    public boolean excluirFornecedor(String cnpj) {
        String query = "DELETE FROM fornecedor WHERE (cnpj) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setString(1, cnpj);

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


    public boolean pesquisarFornecedor(String cnpj) {
        String query = "SELECT 1 FROM fornecedor WHERE (cnpj) = (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setString(1, cnpj);

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
