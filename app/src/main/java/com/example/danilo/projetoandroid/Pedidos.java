package com.example.danilo.projetoandroid;

/**
 * Created by DANILO on 26/11/2015.
 */
public class Pedidos {

    private int numMesa;
    private int id_prod;
    private int qtde;

    public Pedidos(){}

    public Pedidos(int numMesa, int id_prod, int qtde){
        this.numMesa = numMesa;
        this.id_prod = id_prod;
        this.qtde = qtde;
    }


    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
}
