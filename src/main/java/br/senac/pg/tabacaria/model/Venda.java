/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author fernanda
 */
public class Venda {
    private int id;
    private int quantidade;
    private String dt_venda;
    private Funcionario funcionario;
    private Cliente cliente;
    private List<Produto> produto;

    public Venda() {
    }

    public Venda(int id, int quantidade, String dt_venda, Funcionario funcionario, Cliente cliente, List<Produto> produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.dt_venda = dt_venda;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDt_venda() {
        return dt_venda;
    }

    public void setDt_venda(String dt_venda) {
        this.dt_venda = dt_venda;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }
    
    
}
