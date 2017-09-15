package com.example.paulo.listjava;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.paulo.listjava.db.Banco;

/**
 * Created by Paulo on 15/09/2017.
 */

public class Alterar extends AppCompatActivity {

    private Bundle extras;
    private Banco bc = Banco.getInstance(getBaseContext());
    private SQLiteDatabase db = bc.getWritableDatabase();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alterar);

        final Intent telaAlterar = getIntent();
        extras = telaAlterar.getExtras();

        if(extras != null) {
            EditText edtNome = (EditText) findViewById(R.id.editNomeA);
            edtNome.setText((String)extras.get("nome"));

        }



        Button btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = (Integer)extras.get("id");
                db.delete("lista","id=?",new String[]{id.toString()});
                finish();

            }
        });
    }
}
