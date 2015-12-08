package com.example.danilo.projetoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditarProdutoActivity extends ActionBarActivity {

    private EditText idProduto;
    private EditText nomeProduto;
    private EditText precoProduto;
    private ProdutosBD pBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        idProduto = (EditText) findViewById(R.id.etIDEditar);
        nomeProduto = (EditText) findViewById(R.id.etProdutoEditar);
        precoProduto = (EditText) findViewById(R.id.etPrecoEditar);
        pBanco = new ProdutosBD(this);

        idProduto.setText(getIntent().getStringExtra("ID"));
        nomeProduto.setText(getIntent().getStringExtra("Nome"));
        precoProduto.setText(getIntent().getStringExtra("Preco"));
        nomeProduto.requestFocus();
    }

    public void EditarProduto(View view) {

        int id = Integer.parseInt(idProduto.getText().toString());
        Produtos produtos = new Produtos();
        produtos.set_id(id);
        produtos.setNome(nomeProduto.getText().toString());
        produtos.setPreco(Double.parseDouble(precoProduto.getText().toString()));
        pBanco.updtProduto(produtos);
        Toast.makeText(getApplication(), "Produto alterado com sucesso", Toast.LENGTH_LONG).show();
        Intent it = new Intent(getApplicationContext(), ListaProdutosActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);

    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(getApplicationContext(), ListaProdutosActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent it = new Intent(getApplicationContext(), ListaProdutosActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(it);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
