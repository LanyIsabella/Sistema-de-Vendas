CREATE TABLE IF NOT EXISTS fornecedor (
    id_fornecedor SERIAL PRIMARY KEY,
    nome_fantasia VARCHAR(100) NOT NULL,
    razao_social VARCHAR(100) NOT NULL,
    cnpj VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS categoria (
    id_categoria SERIAL PRIMARY KEY,
    nome_categoria VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS produto (
    id_produto SERIAL PRIMARY KEY,
    nome_produto VARCHAR(150) NOT NULL,
    preco_produto NUMERIC(10,2) NOT NULL,
    qtde_estoque INT NOT NULL,
    -- valor_ultima_compra NUMERIC(10,2) NOT NULL,
    -- valor_ultima_venda NUMERIC(10,2) NOT NULL,
    id_categoria INT NOT NULL REFERENCES categoria(id_categoria)
);

CREATE TABLE IF NOT EXISTS cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome_cliente VARCHAR(150) NOT NULL,
    cpf_cliente VARCHAR(20) NOT NULL UNIQUE,
    rg_cliente VARCHAR(20) NOT NULL UNIQUE,
    endereco VARCHAR(150) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS vendas (
    id_venda SERIAL PRIMARY KEY,
    data_venda DATE NOT NULL,
    valor_total NUMERIC(10,2) NOT NULL,
    id_cliente INT NOT NULL REFERENCES cliente(id_cliente)
);

CREATE TABLE IF NOT EXISTS produto_fornecedor (
    id_produto INT NOT NULL REFERENCES produto(id_produto),
    id_fornecedor INT NOT NULL REFERENCES fornecedor(id_fornecedor),
    PRIMARY KEY (id_produto, id_fornecedor)
);

CREATE TABLE IF NOT EXISTS itens_venda (
    id_item SERIAL PRIMARY KEY,
    id_venda INT NOT NULL REFERENCES vendas(id_venda) ON DELETE CASCADE ON UPDATE CASCADE,
    id_produto INT NOT NULL REFERENCES produto(id_produto),
    quantidade INT NOT NULL
);

CREATE TABLE IF NOT EXISTS compra (
    id_compra SERIAL PRIMARY KEY,
    data_compra DATE NOT NULL,
    valor_total NUMERIC(10,2) NOT NULL,
    id_fornecedor INT NOT NULL REFERENCES fornecedor(id_fornecedor)
);

CREATE TABLE IF NOT EXISTS itens_compra (
    id_item SERIAL PRIMARY KEY,
    id_compra INT NOT NULL REFERENCES compra(id_compra) ON DELETE CASCADE ON UPDATE CASCADE,
    id_produto INT NOT NULL REFERENCES produto(id_produto),
    quantidade INT NOT NULL
);

INSERT INTO cliente (nome_cliente, cpf_cliente, rg_cliente, endereco, telefone)
VALUES
('Joao Silva', '12345678900', 'MG1234567', 'Rua A, 123', '64999999999'),
('Maria Souza', '98765432100', 'MG7654321', 'Rua B, 456', '64988888888'),
('Carlos Pereira', '11122233344', 'MG1112223', 'Rua C, 789', '64977777777');

INSERT INTO categoria (nome_categoria)
VALUES
('Eletronicos'),
('Alimentos');

-- INSERT INTO produto (nome_produto, preco_produto, qtde_estoque, valor_ultima_compra, valor_ultima_venda, id_categoria)
INSERT INTO produto (nome_produto, preco_produto, qtde_estoque, id_categoria)
VALUES
('Notebook', 3500.00, 10, 1),
('Mouse', 50.00, 30, 1),
('Teclado', 120.00, 20, 1),
('Arroz', 25.00, 100, 2),
('Feijao', 8.50, 80, 2);

SELECT * FROM cliente;
SELECT * FROM produto;
