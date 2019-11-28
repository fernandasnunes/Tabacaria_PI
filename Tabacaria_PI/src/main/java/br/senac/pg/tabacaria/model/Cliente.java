package br.senac.pg.tabacaria.model;

import java.time.LocalDate;




public class Cliente {
    
    private Long id;
    private String nome;
    private String sexo;
    private String dataNascimento;
    private String cpf;
    private String endereco;
    private String telefone;  
    private String email;
    private String datacadastro;
    private boolean ativo;
    private int idFilial;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String sexo, String dataNascimento, String cpf, String endereco, String telefone, String email, String datacadastro, boolean ativo, int idFilial) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.datacadastro = datacadastro;
        this.ativo = ativo;
        this.idFilial = idFilial;
    }

    public Cliente(String nome, String sexo, String dataNascimento, String cpf, String endereco, String telefone, String email, String datacadastro, boolean ativo, int idFilial) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.datacadastro = datacadastro;
        this.ativo = ativo;
        this.idFilial = idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    
    public int getIdFilial() {
        return idFilial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public String getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(String datacadastro) {
        this.datacadastro = datacadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
   
}