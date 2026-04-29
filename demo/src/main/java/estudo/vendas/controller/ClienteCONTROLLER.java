package estudo.vendas.controller;

import estudo.vendas.dao.ClienteDAO;
import estudo.vendas.model.Cliente;

public class ClienteCONTROLLER {
    private ClienteDAO clienteDAO = new ClienteDAO();

    // Metodo de salvar cliente

    public boolean salvarCliente(Cliente cliente) {
        if (!validarCliente(cliente)) {
            return false;
        }

        return clienteDAO.salvarCliente(cliente);
    }

    // Metodo de alterar cliente

    public boolean alterarCliente(Cliente clienteAntigo, Cliente clienteNovo) {
        if (!validarCliente(clienteAntigo)) {
            return false;
        }

        if (!validarCliente(clienteNovo)) {
            return false;
        }

        return clienteDAO.alterarCliente(clienteAntigo, clienteNovo);
    }

    // Metodo de excluir cliente

    public boolean excluirCliente(String cpf) {
        if (!validarTexto(cpf, "CPF invalido.")) {
            return false;
        }

        return clienteDAO.excluirCliente(cpf);
    }

    // Metodo de pesquisar cliente

    public boolean pesquisarCliente(String cpf) {
        if (!validarTexto(cpf, "CPF invalido.")) {
            return false;
        }

        return clienteDAO.pesquisarCliente(cpf);
    }

    private boolean validarCliente(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Cliente invalido.");
            return false;
        }

        if (!validarTexto(cliente.getNome_cliente(), "Nome do cliente invalido.")) {
            return false;
        }

        if (!validarTexto(cliente.getCpf_cliente(), "CPF invalido.")) {
            return false;
        }

        if (!validarTexto(cliente.getRg_cliente(), "RG invalido.")) {
            return false;
        }

        if (!validarTexto(cliente.getEndereco(), "Endereco invalido.")) {
            return false;
        }

        if (!validarTexto(cliente.getTelefone(), "Telefone invalido.")) {
            return false;
        }

        return true;
    }

    private boolean validarTexto(String texto, String mensagem) {
        if (texto == null || texto.isBlank()) {
            System.out.println(mensagem);
            return false;
        }

        return true;
    }

}
