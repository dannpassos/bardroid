package com.example.danilo.projetoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TelaPrincipalActivity extends ActionBarActivity implements ExitDialog.ExitListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        getSupportActionBar().hide();
    }



    public void CadastrarProduto(View view) {
        Intent it = new Intent(getApplicationContext(), CadastrarProdutoActivity.class);
        startActivity(it);
    }

    public void CadastrarPedido(View view) {
        Intent it = new Intent(getApplicationContext(), CadastrarPedidoActivity.class);
        startActivity(it);
    }


    public void FecharConta(View view) {
        Intent it = new Intent(getApplicationContext(), FecharContaActivity.class);
        startActivity(it);
    }

    public void Sobre(View view) {
        Intent it = new Intent(getApplicationContext(), SobreActivity.class);
        startActivity(it);
    }

    public void Sair(View view) {
        onBackPressed();
    }


    public void ListaProdutos(View view) {
        Intent it = new Intent(getApplicationContext(), ListaProdutosActivity.class);
        startActivity(it);
    }

    @Override
    public void onBackPressed() {
        ExitDialog dialog = new ExitDialog();
        dialog.show(getFragmentManager(), "exiT");
    }

    @Override
    public void onExit() {
        finish();
    }
}
