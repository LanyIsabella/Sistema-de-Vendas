package estudo.vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import estudo.vendas.model.Categoria;
import estudo.vendas.model.Conexao;
import estudo.vendas.model.Produto;

public class ProdutoDAO {
    public boolean salvarProduto(Produto produto) {

        String query = "INSERT INTO produto (nome_produto, preco_medio, qtde_estoque, id_categoria, valor_ultima_compra, valor_ultima_venda) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setString(1, produto.getNome_produto());
            stmt.setFloat(2, produto.getPreco_medio());
            stmt.setInt(3, produto.getQtde_estoque());
            stmt.setInt(4, produto.getCategoria().getId_categoria());
            stmt.setFloat(5, produto.getValor_ultima_compra());
            stmt.setFloat(6, produto.getValor_ultima_venda());

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


    public boolean alterarProduto(Produto produto_antigo, Produto produto_novo) {
        String query = "UPDATE produto SET (nome_produto, preco_medio, qtde_estoque, id_categoria, valor_ultima_compra, valor_ultima_venda) = (?, ?, ?, ?, ?, ?) WHERE (id_produto) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setString(1, produto_novo.getNome_produto());
            stmt.setFloat(2, produto_novo.getPreco_medio());
            stmt.setInt(3, produto_novo.getQtde_estoque());
            stmt.setInt(4, produto_novo.getCategoria().getId_categoria());
            stmt.setFloat(5, produto_novo.getValor_ultima_compra());
            stmt.setFloat(6, produto_novo.getValor_ultima_venda());
            stmt.setInt(7, produto_antigo.getId_produto());

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


    public boolean excluirProduto(int id_produto) {
        String query = "DELETE FROM produto WHERE (id_produto) = (?)";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, id_produto);

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


    public boolean pesquisarProduto(String nome_produto) {
        String query = "SELECT 1 FROM produto WHERE (nome_produto) = (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setString(1, nome_produto);

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

    public Produto buscarProdutoID(int id_produto) {
        String query = "SELECT * FROM produto WHERE (id_produto) = (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection(); 
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, id_produto);

            rs = stmt.executeQuery();

            if (rs.next()) {
                Produto produtos = new Produto();
                produtos.setId_produto(rs.getInt("id_produto"));
                produtos.setNome_produto(rs.getString("nome_produto"));
                produtos.setPreco_medio(rs.getFloat("preco_medio"));
                produtos.setQtde_estoque(rs.getInt("qtde_estoque"));
                produtos.setValor_ultima_compra(rs.getFloat("valor_ultima_compra"));
                produtos.setValor_ultima_venda(rs.getFloat("valor_ultima_venda"));


                Categoria categoria = new Categoria();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                produtos.setCategoria(categoria);

                return produtos;
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
        return null;
    }

    public int verificarEstoque(int idProduto) {
        String query = "SELECT qtde_estoque FROM produto WHERE id_produto = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, idProduto);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("qtde_estoque");
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

        return 0;
    }

    public boolean atualizarEstoqueProduto(int idProduto, int novoEstoque) {
        String query = "UPDATE produto SET qtde_estoque = ? WHERE id_produto = ?";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, novoEstoque);
            stmt.setInt(2, idProduto);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;

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


    public boolean atualizarUltimoValorVenda(int idProduto, float preco_unitario) {
        String query = "UPDATE produto SET valor_ultima_venda = ? WHERE id_produto = ?";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setFloat(1, preco_unitario);
            stmt.setInt(2, idProduto);

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


    public boolean atualizarUltimoValorCompra(int idProduto, float preco_unitario) {
        String query = "UPDATE produto SET valor_ultima_compra = ? WHERE id_produto = ?";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setFloat(1, preco_unitario);
            stmt.setInt(2, idProduto);

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


    public boolean atualizarPrecoMedioProduto(int idProduto, float precoMedio) {
        String query = "UPDATE produto SET preco_medio = ? WHERE id_produto = ?";
        PreparedStatement stmt = null;

        try {
            Connection conn = Conexao.getConnection();
            stmt = conn.prepareStatement(query);

            stmt.setFloat(1, precoMedio);
            stmt.setInt(2, idProduto);

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


    

}
