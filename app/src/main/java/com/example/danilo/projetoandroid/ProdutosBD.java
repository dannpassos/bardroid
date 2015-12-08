package com.example.danilo.projetoandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANILO on 23/11/2015.
 */
public class ProdutosBD extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pBanco";
    DecimalFormat df = new DecimalFormat("0.00");

    public ProdutosBD (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE produtos (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome_produto TEXT NOT NULL UNIQUE, preco_produto DECIMAL NOT NULL)");
        db.execSQL("CREATE TABLE pedidos (_id_mesa INTEGER  NOT NULL, mesa_produto INTEGER  NOT NULL, quantidade INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS produtos");
        db.execSQL("DROP TABLE IF EXISTS pedidos");
        onCreate(db);

    }
    //Método que adiciona produtos no banco de dados
    public boolean addProduto(Produtos produtos){
        SQLiteDatabase pBanco = this.getWritableDatabase();
        try{
            ContentValues inc = new ContentValues();
            inc.put("nome_produto", produtos.getNome());
            inc.put("preco_produto", produtos.getPreco());
            long verifica = pBanco.insert("produtos", null, inc);
            if (verifica == -1){
                return false;
            }
            return true;
        }finally {
            pBanco.close();
        }
    }

    public void updtProduto(Produtos produtos){
        SQLiteDatabase pBanco = this.getWritableDatabase();
        try {
            ContentValues inc = new ContentValues();
            inc.put("nome_produto", produtos.getNome());
            inc.put("preco_produto", produtos.getPreco());
            pBanco.update("produtos", inc, "_id= " + produtos.get_id(), null);


        }finally {
            pBanco.close();
        }
    }

    public void delProduto(int id){
        SQLiteDatabase pBanco = this.getWritableDatabase();
        try {
            pBanco.delete("produtos", "_id = " + id, null);
        }finally {
            pBanco.close();
        }
    }

    public Produtos pesqProduto(int id){
        SQLiteDatabase pBanco = this.getWritableDatabase();
        Cursor reg = pBanco.query("produtos", null, "_id =" + id, null, null, null, null, null);


        if (reg.moveToNext()){
            String nomeProduto = reg.getString(reg.getColumnIndex("nome_produto"));
            double preco = reg.getDouble(reg.getColumnIndex("preco_produto"));

            Produtos produtos = new Produtos();
            produtos.set_id(id);
            produtos.setNome(nomeProduto);
            produtos.setPreco(preco);

            return produtos;
        }else {
            return null;
        }
    }

    public Cursor listaProdutos(){
        SQLiteDatabase pBanco = this.getWritableDatabase();
        Cursor reg = pBanco.query("produtos",null, null, null, null, null, null);
        return reg;
    }

    public void addPedido(Pedidos pedidos){
        SQLiteDatabase pBanco = this.getWritableDatabase();
        try {
            ContentValues inc = new ContentValues();
            inc.put("_id_mesa", pedidos.getNumMesa());
            inc.put("mesa_produto", pedidos.getId_prod());
            inc.put("quantidade", pedidos.getQtde());
            pBanco.insert("pedidos", null, inc);
        }finally {
            pBanco.close();
        }
    }

    public Pedidos pesqMesa(int id){
        SQLiteDatabase pBanco = this.getWritableDatabase();
        Cursor mesa = pBanco.query("pedidos", null, "_id_mesa =" + id, null, null, null, null, null);

        if (mesa.moveToNext()){
            int numMesa = mesa.getInt(mesa.getColumnIndex("_id_mesa"));
            int idProd = mesa.getInt(mesa.getColumnIndex("mesa_produto"));
            int qtde = mesa.getInt(mesa.getColumnIndex("quantidade"));
            Pedidos pedidos = new Pedidos();
            pedidos.setNumMesa(numMesa);
            pedidos.setId_prod(idProd);
            pedidos.setQtde(qtde);
            return pedidos;
        }else {
            return null;
        }
    }

    public void delMesa(int id){
        SQLiteDatabase pBanco = this.getWritableDatabase();
        try {
            pBanco.delete("pedidos", "_id_mesa = " + id, null);
        }finally {
            pBanco.close();
        }
    }

    public Cursor listaConta(int id){
        SQLiteDatabase pBanco = this.getWritableDatabase();
        Cursor lista = pBanco.rawQuery("SELECT d._id, d.nome_produto, d.preco_produto, p.quantidade, SUM (p.quantidade) total_quantidade, SUM(p.quantidade * d.preco_produto) total_preco FROM pedidos p " +
                "INNER JOIN produtos d ON p.mesa_produto = d._id" +
                " WHERE p._id_mesa = ? " +
                "GROUP BY d.nome_produto " +
                "ORDER BY d._id", new String[]{String.valueOf(id)});
        return lista;

    }

}
