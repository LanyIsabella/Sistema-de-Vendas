package estudo.vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import estudo.vendas.model.Cliente;
import estudo.vendas.model.Conexao;

public class ClienteDAO {
    public boolean salvarCliente(Cliente cliente) {

        String query = "INSERT INTO cliente (nome_cliente, cpf_cliente, rg_cliente, endereco, telefone) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setString(1, cliente.getNome_cliente());
            stmt.setString(2, cliente.getCpf_cliente());
            stmt.setString(3, cliente.getRg_cliente());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getTelefone());

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


    public boolean alterarCliente(Cliente cliente_antigo, Cliente cliente_novo) {
        String query = "UPDATE cliente SET (nome_cliente, cpf_cliente, rg_cliente, endereco, telefone) = (?, ?, ?, ?, ?) WHERE (cpf_cliente) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setString(1, cliente_novo.getNome_cliente());
            stmt.setString(2, cliente_novo.getCpf_cliente());
            stmt.setString(3, cliente_novo.getRg_cliente());
            stmt.setString(4, cliente_novo.getEndereco());
            stmt.setString(5, cliente_novo.getTelefone());
            stmt.setString(6, cliente_antigo.getCpf_cliente());

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


    public boolean excluirCliente(String cpf) {
        String query = "DELETE FROM cliente WHERE (cpf_cliente) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setString(1, cpf);

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


    public boolean pesquisarCliente(String cpf) {
        String query = "SELECT 1 FROM cliente WHERE (cpf_cliente) = (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setString(1, cpf);

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
