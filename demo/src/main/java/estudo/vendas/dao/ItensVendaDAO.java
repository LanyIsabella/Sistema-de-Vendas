package estudo.vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import estudo.vendas.model.Conexao;
import estudo.vendas.model.ItensVenda;
import estudo.vendas.model.Produto;
import estudo.vendas.model.Vendas;

public class ItensVendaDAO {
    public boolean salvarItensVenda(ItensVenda itens_venda) {

        String query = "INSERT INTO itens_venda (id_venda, id_produto, preco_unitario, quantidade) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, itens_venda.getVendas().getId_venda());
            stmt.setInt(2, itens_venda.getProduto().getId_produto());
            stmt.setFloat(3, itens_venda.getPreco_unitario());
            stmt.setInt(4, itens_venda.getQuantidade());

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


    public boolean alterarItensVenda(ItensVenda item_antigo, ItensVenda item_novo) {
        String query = "UPDATE itens_venda SET (id_venda, id_produto, preco_unitario, quantidade) = (?, ?, ?, ?) WHERE (id_item) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, item_novo.getVendas().getId_venda());
            stmt.setInt(2, item_novo.getProduto().getId_produto());
            stmt.setFloat(3, item_novo.getPreco_unitario());
            stmt.setInt(4, item_novo.getQuantidade());
            stmt.setInt(5, item_antigo.getId_item());

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


    public boolean excluirItemVenda(int id_item) {
        String query = "DELETE FROM itens_venda WHERE (id_item) = (?)";
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


    public boolean pesquisarItemVenda(int id_item) {
        String query = "SELECT 1 FROM itens_venda WHERE (id_item) = (?)";
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

    public List<ItensVenda> listarItensVendaPorVenda(Vendas vendas) {
        String query = "SELECT * FROM itens_venda WHERE (id_venda) = (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ItensVenda> listaItens = new ArrayList<>();

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, vendas.getId_venda());

            rs = stmt.executeQuery();

            while (rs.next()) {
                ItensVenda item = new ItensVenda();

                item.setId_item(rs.getInt("id_item"));
                item.setPreco_unitario(rs.getFloat("preco_unitario"));
                item.setQuantidade(rs.getInt("quantidade"));

                Vendas venda = new Vendas();
                venda.setId_venda(rs.getInt("id_venda"));
                item.setVendas(venda);

                Produto produto = new Produto();
                produto.setId_produto(rs.getInt("id_produto"));
                item.setProduto(produto);

                listaItens.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();

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

        return listaItens;
    }

}
