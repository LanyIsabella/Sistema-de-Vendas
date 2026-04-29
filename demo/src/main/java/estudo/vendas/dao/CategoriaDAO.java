package estudo.vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import estudo.vendas.model.Categoria;
import estudo.vendas.model.Conexao;

public class CategoriaDAO {

    public boolean salvarCategoria(Categoria categoria) {

        String query = "INSERT INTO categoria (nome_categoria) VALUES (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, categoria.getNome_categoria());

            int linhas_afetadas = stmt.executeUpdate();

            if (linhas_afetadas > 0) {
                rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    categoria.setId_categoria(rs.getInt(1));
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


    public boolean alterarCategoria(Categoria categoria_antiga, Categoria categoria_nova) {
        String query = "UPDATE categoria SET (nome_categoria) = (?) WHERE (nome_categoria) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setString(1, categoria_nova.getNome_categoria());
            stmt.setString(2, categoria_antiga.getNome_categoria());

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


    public boolean excluirCategoria(String nome_categoria) {
        String query = "DELETE FROM categoria WHERE (nome_categoria) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setString(1, nome_categoria);

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


    public boolean pesquisarCategoria(String nome_categoria) {
        String query = "SELECT 1 FROM categoria WHERE (nome_categoria) = (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setString(1, nome_categoria);

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
