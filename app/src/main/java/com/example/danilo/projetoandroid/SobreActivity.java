package com.example.danilo.projetoandroid;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;

public class SobreActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void Instagram(View view) {
        Uri uri = Uri.parse("http://instagram.com/_u/ludospub");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/ludospub")));
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
            Intent it = new Intent(getApplicationContext(), TelaPrincipalActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(it);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void GoogleMap(View view) {
        Intent it = new Intent(getApplicationContext(), LudosMapsActivity.class);
        startActivity(it);
    }
}
