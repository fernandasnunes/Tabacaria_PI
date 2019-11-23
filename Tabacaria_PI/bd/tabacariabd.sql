CREATE DATABASE TABACARIA;

CREATE TABLE TABACARIA.PRODUTO (
  ID           BIGINT        NOT NULL AUTO_INCREMENT,
  NOME         VARCHAR(100)  NOT NULL,
  MARCA        VARCHAR(100)  NOT NULL,
  DESCRICAO    VARCHAR(1000) NULL,
  PRECO_COMPRA DECIMAL(9,2)  NOT NULL,
  PRECO_VENDA  DECIMAL(9,2)  NOT NULL,
  QUANTIDADE   INT           NOT NULL DEFAULT 0,
  DT_CADASTRO  TIMESTAMP     NOT NULL,
  CONSTRAINT PK_PRODUTO PRIMARY KEY (ID)
);

CREATE TABLE TABACARIA.CATEGORIA (
  ID   INT          NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(100) NOT NULL,
  CONSTRAINT PK_CATEGORIA PRIMARY KEY (ID),
  CONSTRAINT UC_NOME UNIQUE (NOME)
);

CREATE TABLE TABACARIA.PRODUTO_CATEGORIA (
    ID_PRODUTO   BIGINT NOT NULL,
    ID_CATEGORIA INT    NOT NULL,
    CONSTRAINT FK_PRODUTO FOREIGN KEY (ID_PRODUTO) REFERENCES TABACARIA.PRODUTO(ID),
    CONSTRAINT FK_CATEGORIA FOREIGN KEY (ID_CATEGORIA) REFERENCES TABACARIA.CATEGORIA(ID)
);

CREATE TABLE TABACARIA.FILIAL(
	ID	BIGINT NOT NULL,
    NOME		VARCHAR(255),
    LOCALIDADE	VARCHAR(255),
    RESPOSAVEL	VARCHAR(255),
    CONSTRAINT PK_FILIAL PRIMARY KEY (ID)
    );


CREATE TABLE TABACARIA.CLIENTE (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(100) NOT NULL,
  ENDERECO VARCHAR(80),
  SEXO VARCHAR(10),
  CPF VARCHAR(14),
  EMAIL VARCHAR(80),
  TELEFONE VARCHAR(14),
  ID_FILIAL BIGINT,
  DATANASCIMENTO VARCHAR(10),
  DATACADASTRO VARCHAR(10),  
  ATIVO BOOLEAN,  
  CONSTRAINT PK_CLIENTE PRIMARY KEY (ID),
  CONSTRAINT FK_CLIENTE_ID_FILIAL FOREIGN KEY (ID_FILIAL) REFERENCES TABACARIA.FILIAL(ID)
);

CREATE TABLE TABACARIA.FUNCIONARIO (
  ID BIGINT  NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(100), 
  CARGO VARCHAR(80),
  ENDERECO VARCHAR(80),
  SEXO VARCHAR(1),
  TELEFONE VARCHAR(14),
  DATACADASTRO VARCHAR(10),
  LOGIN VARCHAR(20),
  SENHA VARCHAR(10),
  ID_FILIAL BIGINT,
  ATIVO BOOLEAN,  
  CONSTRAINT PK_VENDEDOR PRIMARY KEY (ID),
  CONSTRAINT FK_FUNCIONARIO_ID_FILIAL FOREIGN KEY (ID_FILIAL) REFERENCES TABACARIA.FILIAL(ID)
);


CREATE TABLE TABACARIA.VENDA (
  ID BIGINT  NOT NULL AUTO_INCREMENT,
  ID_CLIENTE BIGINT NOT NULL,
  ID_FUNCIONARIO BIGINT NOT NULL,
  ID_PRODUTO BIGINT NOT NULL,
  ID_FILIAL BIGINT NOT NULL,
  QUANTIDADE INT NOT NULL,
  DT_VENDA TIMESTAMP NOT NULL,
  CONSTRAINT PK_VENDA PRIMARY KEY (ID),
  CONSTRAINT FK_VENDA_ID_CLIENTE FOREIGN KEY (ID_CLIENTE) REFERENCES TABACARIA.CLIENTE(ID),
  CONSTRAINT FK_VENDA_ID_FUNCIONARIO FOREIGN KEY (ID_FUNCIONARIO) REFERENCES TABACARIA.FUNCIONARIO(ID),
  CONSTRAINT FK_VENDA_ID_PRODUTO FOREIGN KEY (ID_PRODUTO) REFERENCES TABACARIA.PRODUTO(ID),
  CONSTRAINT FK_VENDA_ID_FILIAL FOREIGN KEY (ID_FILIAL) REFERENCES TABACARIA.FILIAL(ID)
);

use tabacaria;
select * from cliente;