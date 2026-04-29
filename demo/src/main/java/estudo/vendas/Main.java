package estudo.vendas;

import java.util.Date;
import java.util.List;

import estudo.vendas.controller.CategoriaCONTROLLER;
import estudo.vendas.controller.ClienteCONTROLLER;
import estudo.vendas.controller.ComprasCONTROLLER;
import estudo.vendas.controller.FornecedorCONTROLLER;
import estudo.vendas.controller.ItensCompraCONTROLLER;
import estudo.vendas.controller.ItensVendaCONTROLLER;
import estudo.vendas.controller.ProdutoCONTROLLER;
import estudo.vendas.controller.VendasCONTROLLER;
import estudo.vendas.model.Categoria;
import estudo.vendas.model.Cliente;
import estudo.vendas.model.Compra;
import estudo.vendas.model.Conexao;
import estudo.vendas.model.Fornecedor;
import estudo.vendas.model.ItensCompra;
import estudo.vendas.model.ItensVenda;
import estudo.vendas.model.Produto;
import estudo.vendas.model.ProdutoFornecedor;
import estudo.vendas.model.Vendas;

public class Main {
    public static void main(String[] args) {
        CategoriaCONTROLLER categoriaCONTROLLER = new CategoriaCONTROLLER();
        ClienteCONTROLLER clienteCONTROLLER = new ClienteCONTROLLER();
        FornecedorCONTROLLER fornecedorCONTROLLER = new FornecedorCONTROLLER();
        ProdutoCONTROLLER produtoCONTROLLER = new ProdutoCONTROLLER();
        ComprasCONTROLLER comprasCONTROLLER = new ComprasCONTROLLER();
        ItensCompraCONTROLLER itensCompraCONTROLLER = new ItensCompraCONTROLLER();
        VendasCONTROLLER vendasCONTROLLER = new VendasCONTROLLER();
        ItensVendaCONTROLLER itensVendaCONTROLLER = new ItensVendaCONTROLLER();

        long identificador = System.currentTimeMillis() % 10_000_000_000L;

        try {
            Categoria categoria = criarCategoria(identificador);
            boolean categoriaSalva = categoriaCONTROLLER.salvarCategoria(categoria);
            imprimirResultado("CategoriaCONTROLLER.salvarCategoria", categoriaSalva);

            Cliente cliente = criarCliente(identificador);
            boolean clienteSalvo = clienteCONTROLLER.salvarCliente(cliente);
            imprimirResultado("ClienteCONTROLLER.salvarCliente", clienteSalvo);

            Fornecedor fornecedor = criarFornecedor(identificador);
            boolean fornecedorSalvo = fornecedorCONTROLLER.salvarFornecedor(fornecedor);
            imprimirResultado("FornecedorCONTROLLER.salvarFornecedor", fornecedorSalvo);

            Produto produto = criarProduto(identificador, categoria);
            ProdutoFornecedor produtoFornecedor = new ProdutoFornecedor();
            produtoFornecedor.setId_fornecedor(fornecedor);

            boolean produtoSalvo = produtoCONTROLLER.salvarProduto(produto, produtoFornecedor);
            imprimirResultado("ProdutoCONTROLLER.salvarProduto", produtoSalvo);

            Compra compra = criarCompra(fornecedor);
            ItensCompra itemCompra = criarItemCompra(produto, 5, 32.50f);
            List<ItensCompra> itensCompra = List.of(itemCompra);
            compra.setValor_total(calcularValorTotalCompra(itensCompra));

            boolean compraSalva = comprasCONTROLLER.salvarCompra(compra, itensCompra);
            imprimirResultado("ComprasCONTROLLER.salvarCompra", compraSalva);

            ItensCompra itemCompraAvulso = criarItemCompra(produto, 1, 33.00f);
            boolean itemCompraSalvo = itensCompraCONTROLLER.salvarItensCompra(compra, List.of(itemCompraAvulso));
            imprimirResultado("ItensCompraCONTROLLER.salvarItensCompra", itemCompraSalvo);

            Vendas venda = criarVenda(cliente);
            ItensVenda itemVenda = criarItemVenda(produto, 2, 49.90f);
            boolean vendaSalva = vendasCONTROLLER.salvarVenda(venda, List.of(itemVenda));
            imprimirResultado("VendasCONTROLLER.salvarVenda", vendaSalva);

            ItensVenda itemVendaAvulso = criarItemVenda(produto, 1, 49.90f);
            boolean itemVendaSalvo = itensVendaCONTROLLER.salvarItensVenda(venda, List.of(itemVendaAvulso));
            imprimirResultado("ItensVendaCONTROLLER.salvarItensVenda", itemVendaSalvo);

        } catch (Exception e) {
            System.out.println("Erro ao executar testes de salvamento.");
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao();
        }
    }

    private static Categoria criarCategoria(long identificador) {
        Categoria categoria = new Categoria();
        categoria.setNome_categoria("Categoria Teste " + identificador);
        return categoria;
    }

    private static Cliente criarCliente(long identificador) {
        Cliente cliente = new Cliente();
        cliente.setNome_cliente("Cliente Teste " + identificador);
        cliente.setCpf_cliente(String.format("%011d", identificador));
        cliente.setRg_cliente("RG" + identificador);
        cliente.setEndereco("Rua de Teste, 123");
        cliente.setTelefone("64999999999");
        return cliente;
    }

    private static Fornecedor criarFornecedor(long identificador) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome_fantasia("Fornecedor Teste " + identificador);
        fornecedor.setRazao_social("Fornecedor Teste LTDA " + identificador);
        fornecedor.setCnpj(String.format("%014d", identificador));
        return fornecedor;
    }

    private static Produto criarProduto(long identificador, Categoria categoria) {
        Produto produto = new Produto();
        produto.setNome_produto("Produto Teste " + identificador);
        produto.setPreco_medio(30.00f);
        produto.setQtde_estoque(50);
        produto.setCategoria(categoria);
        produto.setValor_ultima_compra(30.00f);
        produto.setValor_ultima_venda(49.90f);
        return produto;
    }

    private static Compra criarCompra(Fornecedor fornecedor) {
        Compra compra = new Compra();
        compra.setData_compra(new Date());
        compra.setFornecedor(fornecedor);
        return compra;
    }

    private static ItensCompra criarItemCompra(Produto produto, int quantidade, float precoUnitario) {
        ItensCompra item = new ItensCompra();
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPreco_unitario(precoUnitario);
        return item;
    }

    private static Vendas criarVenda(Cliente cliente) {
        Vendas venda = new Vendas();
        venda.setData_venda(new Date());
        venda.setCliente(cliente);
        return venda;
    }

    private static ItensVenda criarItemVenda(Produto produto, int quantidade, float precoUnitario) {
        ItensVenda item = new ItensVenda();
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPreco_unitario(precoUnitario);
        return item;
    }

    private static float calcularValorTotalCompra(List<ItensCompra> itensCompra) {
        float total = 0;

        for (ItensCompra item : itensCompra) {
            total += item.getPreco_unitario() * item.getQuantidade();
        }

        return total;
    }

    private static void imprimirResultado(String teste, boolean sucesso) {
        if (sucesso) {
            System.out.println(teste + ": OK");
        } else {
            System.out.println(teste + ": FALHOU");
        }
    }
}
