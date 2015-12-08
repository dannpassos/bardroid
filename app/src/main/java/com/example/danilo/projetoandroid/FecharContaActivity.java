package com.example.danilo.projetoandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FecharContaActivity extends ActionBarActivity {

    private ListView lvFecharConta;
    private EditText etMesa;
    private ProdutosBD pBanco;
    private TextView totValor;
    DecimalFormat df = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechar_conta);
        etMesa = (EditText) findViewById(R.id.etMesaFecharConta);
        lvFecharConta = (ListView) findViewById(R.id.lvFecharConta);
        totValor = (TextView) findViewById(R.id.txtTotal);
        pBanco = new ProdutosBD(this);


    }


    public void onClickPesquisarMesa(View view) {
        String num = etMesa.getText().toString();

        if (num == null || num.equals("")) {
            Toast.makeText(getApplication(), "Informe o número da mesa", Toast.LENGTH_SHORT).show();
        } else {
            Pedidos pedidos = pBanco.pesqMesa(Integer.parseInt(etMesa.getText().toString()));
            if (pedidos == null) {
                Toast.makeText(getApplication(), "Mesa não encontrada", Toast.LENGTH_SHORT).show();
            }else {
                int nMesa = Integer.parseInt(etMesa.getText().toString());
                Cursor conta = pBanco.listaConta(nMesa);
                double totalValor = 0;
                while (conta.moveToNext()) {
                    double valor = conta.getDouble(conta.getColumnIndex("total_preco"));
                    totalValor += valor;


                }
                totValor.setText("Total: R$  "+ df.format(totalValor));
                String[] tabela = new String[] {"_id", "nome_produto", "preco_produto", "total_quantidade", "total_preco"};
                int[] colunaTabela = new int[] {R.id.text4, R.id.text5, R.id.text8, R.id.text6, R.id.text7};
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.fecharconta_lista, conta, tabela, colunaTabela, 1);
                adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
                    @Override
                    public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                        if (columnIndex == 2 || columnIndex == 5){
                            double preco = cursor.getDouble(columnIndex);
                            TextView label = (TextView) view;
                            label.setText(df.format(preco));
                            return true;
                        }
                        return false;
                    }
                });
                lvFecharConta.setAdapter(adapter);
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


    public void onClickConfPgto(View view) {
        String num2 = etMesa.getText().toString();
        if (num2 == null || num2.equals("")) {
            Toast.makeText(getApplication(), "Informe o número da mesa", Toast.LENGTH_SHORT).show();
        } else {
            Pedidos pedidos = pBanco.pesqMesa(Integer.parseInt(etMesa.getText().toString()));

            if (pedidos == null) {
                Toast.makeText(getApplication(), "Impossível confirmar pagamento, a mesa não foi aberta.", Toast.LENGTH_LONG).show();
            } else {
                int nMesa = Integer.parseInt(etMesa.getText().toString());
                pBanco.delMesa(nMesa);
                Toast.makeText(getApplication(), "Pagamento Confirmado! A mesa foi fechada.", Toast.LENGTH_LONG).show();
                Intent it = new Intent(getApplicationContext(), TelaPrincipalActivity.class);
                it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
            }
        }
    }
}
