package com.example.danilo.projetoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CadastrarProdutoActivity extends ActionBarActivity {

    private EditText etNomeProduto;
    private EditText etPrecoProduto;
    private ProdutosBD pBanco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etNomeProduto = (EditText) findViewById(R.id.etNomeProduto);
        etPrecoProduto = (EditText) findViewById(R.id.etPrecoProduto);
        pBanco = new ProdutosBD(this);
    }


    public void onClickIncluirProduto(View view) {
        String nome = etNomeProduto.getText().toString();
        String preco = etPrecoProduto.getText().toString();

        if (nome == null || nome.equals("") || preco == null || preco.equals("")) {
            Toast.makeText(getApplication(), "Preencha os campos Nome e Preço do produto!! ", Toast.LENGTH_LONG).show();

        } else {

            Produtos produtos = new Produtos();
            produtos.setNome(nome);
            produtos.setPreco((Double.parseDouble(preco)));
            if (pBanco.addProduto(produtos)) {
                Toast.makeText(getApplication(), "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                Intent it = new Intent(getApplicationContext(), TelaPrincipalActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
            }else {
                Toast.makeText(getApplication(), "Esse Produto já foi cadastrado!", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    public void onBackPressed() {
        Intent it = new Intent(getApplicationContext(), TelaPrincipalActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent it = new Intent(this, TelaPrincipalActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(it);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void LimparProduto(View view) {
        etNomeProduto.setText("");
        etPrecoProduto.setText("");
        etNomeProduto.requestFocus();
    }
}
