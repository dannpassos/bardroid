package com.example.danilo.projetoandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ListaProdutosActivity extends ActionBarActivity {

    private ListView lvProdutos;
    private ProdutosBD pBanco;
    private long itemProdutos;
    private String ID, produto, preco;
    DecimalFormat df = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvProdutos = (ListView) findViewById(R.id.lvProdutos);
        pBanco = new ProdutosBD(this);
        listarProdutos();
        registerForContextMenu(lvProdutos);
    }

    private void listarProdutos(){
        Cursor reg = pBanco.listaProdutos();
        String[] tabela = new String[] {"_id", "nome_produto", "preco_produto"};
        int[] colunaTabela = new int[] {R.id.text1, R.id.text2, R.id.text3};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.produtos_lista, reg, tabela, colunaTabela, 1);
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (columnIndex == 2){
                    double preco = cursor.getDouble(columnIndex);
                    TextView label = (TextView) view;
                    label.setText(df.format(preco));
                    return true;
                }
                return false;
            }
        });
        lvProdutos.setAdapter(adapter);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
        itemProdutos = ((AdapterView.AdapterContextMenuInfo) menuInfo).id;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.act_delete){
            pBanco.delProduto((int) itemProdutos);
            Toast.makeText(getApplication(), "Produto Excluído com sucesso", Toast.LENGTH_LONG).show();
            Intent it = new Intent(getApplicationContext(), ListaProdutosActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(it);
            return true;
        }else if (item.getItemId() == R.id.act_editar){
            Produtos produtos = pBanco.pesqProduto((int) itemProdutos);
            if (produtos != null) {

                ID = String.valueOf(produtos.get_id());
                produto = String.valueOf(produtos.getNome());
                preco = String.valueOf(produtos.getPreco());
                Intent it = new Intent(getApplicationContext(), EditarProdutoActivity.class);
                it.putExtra("ID", ID);
                it.putExtra("Nome", produto);
                it.putExtra("Preco", preco);
                startActivity(it);
            }
        }

        return super.onContextItemSelected(item);

    }
}
