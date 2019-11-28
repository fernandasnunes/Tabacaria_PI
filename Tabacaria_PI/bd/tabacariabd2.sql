CREATE DATABASE TABACARIA;

CREATE TABLE TABACARIA.FILIAL(
	ID	BIGINT NOT NULL AUTO_INCREMENT,
    LOCALIDADE		VARCHAR(255),
    RESPONSAVEL		VARCHAR(255),
    DATACADASTRO 	VARCHAR(100),
    CONSTRAINT PK_FILIAL PRIMARY KEY (ID)
    );

CREATE TABLE TABACARIA.PRODUTO (
  ID           	BIGINT        NOT NULL AUTO_INCREMENT,
  NOME         	VARCHAR(100)  NOT NULL,
  MARCA        	VARCHAR(100)  NOT NULL,
  DESCRICAO    	VARCHAR(1000) NULL,
  PRECO_COMPRA	DECIMAL(9,2)  NOT NULL,
  PRECO_VENDA  	DECIMAL(9,2)  NOT NULL,
  QUANTIDADE	INT           NOT NULL DEFAULT 0,
  DATACADASTRO 	VARCHAR(100),
  ID_FILIAL		BIGINT NOT NULL,
  CONSTRAINT PK_PRODUTO PRIMARY KEY (ID)
); 


CREATE TABLE TABACARIA.CLIENTE (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(100) NOT NULL,
  ENDERECO VARCHAR(80),
  SEXO VARCHAR(10),
  CPF VARCHAR(14),
  EMAIL VARCHAR(80),
  TELEFONE VARCHAR(14),
  ID_FILIAL BIGINT NOT NULL,
  DATANASCIMENTO VARCHAR(10),
  DATACADASTRO VARCHAR(10),
  CONSTRAINT PK_CLIENTE PRIMARY KEY (ID),
  CONSTRAINT FK_CLIENTE_ID_FILIAL FOREIGN KEY (ID_FILIAL) REFERENCES TABACARIA.FILIAL(ID)
);

CREATE TABLE TABACARIA.FUNCIONARIO (
  ID BIGINT  NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(100), 
  CARGO VARCHAR(80),
  ENDERECO VARCHAR(80),
  SEXO VARCHAR(10),
  TELEFONE VARCHAR(14),
  DATACADASTRO VARCHAR(10),
  LOGIN VARCHAR(20),
  SENHA VARCHAR(10),
  ID_FILIAL BIGINT NOT NULL,
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
select * from produto;


SELECT P.*,  F.LOCALIDADE, F.RESPONSAVEL , PC.ID_CATEGORIA, C.NOME FROM TABACARIA.PRODUTO P
INNER JOIN TABACARIA.FILIAL F ON F.ID = P.ID_FILIAL
INNER JOIN TABACARIA.PRODUTO_CATEGORIA PC ON P.ID = PC.ID_PRODUTO
INNER JOIN TABACARIA.CATEGORIA C ON C.ID = PC.ID_CATEGORIA;

SELECT C.*, F.LOCALIDADE, F.RESPONSAVEL FROM TABACARIA.CLIENTE C
INNER JOIN TABACARIA.FILIAL F ON C.ID_FILIAL = F.ID;

SELECT FUNC.*, F.LOCALIDADE, F.RESPONSAVEL FROM TABACARIA.FUNCIONARIO FUNC
INNER JOIN TABACARIA.FILIAL F ON FUNC.ID_FILIAL = F.ID;

SELECT  V.ID, V.ID_CLIENTE, V.ID_FUNCIONARIO, V.ID_PRODUTO, V.QUANTIDADE, V.DT_VENDA, C.NOME, C.ENDERECO, C.SEXO, C.CPF, C.EMAIL, C.TELEFONE, C.ID_FILIAL, C.DATANASCIMENTO, 
C.DATACADASTRO, F.LOCALIDADE, F.RESPONSAVEL FROM TABACARIA.VENDA V
INNER JOIN TABACARIA.CLIENTE C ON V.ID_CLIENTE = C.ID
INNER JOIN TABACARIA.FILIAL F ON C.ID_FILIAL = F.ID;

SELECT V.ID, V.ID_CLIENTE, V.ID_FUNCIONARIO, V.ID_PRODUTO, V.QUANTIDADE, V.DT_VENDA, P.NOME, P.MARCA, P.DESCRICAO, P.PRECO_COMPRA, P.PRECO_VENDA, P.QUANTIDADE, P.DT_CADASTRO, P.ID_FILIAL, F.LOCALIDADE, F.RESPONSAVEL, PC.ID_CATEGORIA, C.NOME
FROM TABACARIA.VENDA V
INNER JOIN TABACARIA.PRODUTO P ON V.ID_PRODUTO = P.ID
INNER JOIN TABACARIA.FILIAL F ON P.ID_FILIAL = F.ID
INNER JOIN TABACARIA.PRODUTO_CATEGORIA PC ON P.ID = PC.ID_PRODUTO
INNER JOIN TABACARIA.CATEGORIA C ON C.ID = PC.ID_CATEGORIA;

SELECT  V.ID, V.ID_CLIENTE, V.ID_FUNCIONARIO, V.ID_PRODUTO, V.QUANTIDADE, V.DT_VENDA, 
FUNC.NOME, FUNC.CARGO, FUNC.ENDERECO, FUNC.SEXO, FUNC.TELEFONE, FUNC.DATACADASTRO, FUNC.LOGIN, 
FUNC.SENHA, FUNC.ATIVO, FUNC.ID_FILIAL, F.LOCALIDADE, F.RESPONSAVEL FROM TABACARIA.VENDA V
INNER JOIN TABACARIA.FUNCIONARIO FUNC ON V.ID_FUNCIONARIO = FUNC.ID
INNER JOIN TABACARIA.FILIAL F ON FUNC.ID_FILIAL = F.ID;