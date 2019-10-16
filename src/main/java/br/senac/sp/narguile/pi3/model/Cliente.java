/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.narguile.pi3.model;

import java.sql.Date;


public class Cliente {
    
    private Long id;
    private String nome;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String sexo;
    private String telefone;
    private String celular;
    private Date cadastroData;
    private String ativo;
    
    public Cliente() {
    }
    
    public Cliente(String nome,String endereco,String bairro,String cidade,String estado,String cep, String sexo,String telefone,String celular){
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.sexo = sexo;
        this.telefone = telefone;
        this.celular = celular;
          
    }
    
    public Long getId(){return id;}
    
    public void setId(Long id){
        
        this.id = id;
    }
    
    public String getNome(){return nome;}
    
    public void setNome(String nome){
        
        this.nome = nome;
        
    }
    
    public String getEndereco(){return endereco;}
    
    public void setEndereco(String endereco){
        
        this.endereco = endereco;
        
    }
    
    public String getBairro(){return bairro;}
    
    public void setBairro(String bairro){
        
        this.bairro = bairro;
        
    }
    
    public String getCidade(){return cidade;}
    
    public void setCidade(String cidade){
        
    this.cidade = cidade;
    
    }    
    
    public String getEstado(){return estado;}
    
    public void setEstado(String estado){
        
        this.estado = estado;
        
    }
    
    public String getCep(){return cep;}
    
    public void setCep(String cep){
        
        this.cep = cep;
        
    }
    
    public String getSexo(){return sexo;}
    
    
    public void setSexo(String sexo){
    
        this.sexo = sexo;
        
    }
   
    public String getTelefone(){return telefone;}
    
    public void setTelefone(String telefone){
        
        this.telefone = telefone;
        
    }
    
    
    public String getCelular(){return celular;}
    
    public void setCelular(String celular){
    
        this.celular = celular;
    
    }
    
    public Date getCadastroData(){return cadastroData;}
        
    public void setCadastroData(Date cadastroData) {
        
        this.cadastroData = cadastroData;
        
    }
}
