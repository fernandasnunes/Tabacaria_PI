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
    private String cargo;
    private String endereco;
    private String sexo;
    private String telefone;
  

    public Funcionario() {

    }

    public Funcionario(String nome, String cargo, String endereco, String sexo, String telefone) {
        this.nome = nome;
        this.cargo = cargo;
        this.endereco = endereco;
        this.sexo = sexo;
        this.telefone = telefone;
    }
    
    

    public Funcionario(Long id, String nome, String cargo, String endereco, String sexo, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.endereco = endereco;
        this.sexo = sexo;
        this.telefone = telefone;
       
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

   
    
    
}
