package com.example.danilo.projetoandroid;

/**
 * Created by DANILO on 23/11/2015.
 */
public class Produtos {

    private int _id;
    private String nome;
    private double preco;

    public Produtos(){}

    public Produtos(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public Produtos(int _id, String nome, double preco){
        this._id = _id;
        this.nome = nome;
        this.preco = preco;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
