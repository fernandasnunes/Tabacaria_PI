/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.model;



/**
 *
 * @author serafim
 */
public class Funcionario {

    private Long id;
    private String nome;
    private int idCargo;
    private String endereco;
    private String sexo;
    private String telefone;
    private String datacadastro;
    private String login;
    private String senha;
    private int idFilial;
    
  

    public Funcionario() {

    }

    public Funcionario(Long id, String nome, int idCargo, String endereco, String sexo, String telefone, String datacadastro, String login, String senha, int idFilial) {
        this.id = id;
        this.nome = nome;
        this.idCargo = idCargo;
        this.endereco = endereco;
        this.sexo = sexo;
        this.telefone = telefone;
        this.datacadastro = datacadastro;
        this.login = login;
        this.senha = senha;
        this.idFilial = idFilial;
    }

    public Funcionario(String nome, int idCargo, String endereco, String sexo, String telefone, String datacadastro, String login, String senha, int idFilial) {
        this.nome = nome;
        this.idCargo = idCargo;
        this.endereco = endereco;
        this.sexo = sexo;
        this.telefone = telefone;
        this.datacadastro = datacadastro;
        this.login = login;
        this.senha = senha;
        this.idFilial = idFilial;
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
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(String datacadastro) {
        this.datacadastro = datacadastro;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }
    

   
    
    
}
