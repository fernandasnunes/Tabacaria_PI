package br.senac.sp.narguile.pi3.model;

public class Usuario {
    private Long id;
    private String login;
    private String nome;
    private String senha;
    private String ativo;    
    public Usuario() {
    }
    
    public Usuario(String login, String nome, String senha, String ativo) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.ativo = ativo;
    }
    public Usuario(Long id, String login, String nome, String senha, String ativo) {
        this.id = id;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.ativo = ativo;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }   
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getAtivo() {
        return ativo;
    }
    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }    
}