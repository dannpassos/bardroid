package com.example.danilo.projetoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarPedidoActivity extends ActionBarActivity {

    private EditText numMesa;
    private EditText idPedido;
    private EditText qtde;
    private ProdutosBD pBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        numMesa = (EditText) findViewById(R.id.etNumMesa);
        idPedido = (EditText) findViewById(R.id.etIDProduto);
        qtde = (EditText) findViewById(R.id.etQuantidade);
        pBanco = new ProdutosBD(this);
    }

    public void LimparPedido(View view) {
        numMesa.setText("");
        idPedido.setText("");
        qtde.setText("");
        numMesa.requestFocus();
    }

    public void onClickConfPedido(View view) {
        String num = numMesa.getText().toString();
        String idPed = idPedido.getText().toString();
        String qtd = qtde.getText().toString();
        Button bt = (Button) findViewById(R.id.btConfPedido);

        if (num == null || num.equals("") || idPed == null || idPed.equals("") || qtd == null || qtd.equals("")) {
            Toast.makeText(getApplication(), "Por favor, preencha todos os campos", Toast.LENGTH_LONG).show();

        } else {
            Produtos produtos = pBanco.pesqProduto(Integer.parseInt(idPedido.getText().toString()));
            if (produtos == null) {
                Toast.makeText(getApplication(), "Produto inexistente.", Toast.LENGTH_SHORT).show();

            } else {
                Pedidos pedidos = new Pedidos();
                pedidos.setNumMesa(Integer.parseInt(num));
                pedidos.setId_prod(Integer.parseInt(idPed));
                pedidos.setQtde(Integer.parseInt(qtd));
                pBanco.addPedido(pedidos);
                Toast.makeText(getApplication(), "Pedido realizado com sucesso", Toast.LENGTH_LONG).show();
                LimparPedido(bt);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(getApplicationContext(), TelaPrincipalActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent it = new Intent(getApplicationContext(), TelaPrincipalActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
