
package br.senac.pg.tabacaria.model;


/**
 *
 * @author rafael
 */
public class Produto {
    private Long id;
    private String nome;
    private String marca;
    private String descricao;
    private Double precoCompra;
    private Double precoVenda;
    private int quantidade;       
    private String dataCadastro; 
    
   

    public Produto() {
    }

    public Produto(Long id, String nome, String marca, String descricao, Double precoCompra, Double precoVenda, int quantidade, String dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.descricao = descricao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
        this.dataCadastro = dataCadastro;
    }

    

    public Produto(String nome, String marca, String descricao, Double precoCompra, Double precoVenda, int quantidade, String dataCadastro) {
        this.nome = nome;
        this.marca = marca;
        this.descricao = descricao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
        this.dataCadastro = dataCadastro;
    }

    
    
    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    

    
}
