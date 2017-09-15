package com.example.paulo.listjava;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.paulo.listjava.db.Banco;

/**
 * Created by Paulo on 15/09/2017.
 */

public class cadastro extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cadastro);
        Banco bc = Banco.getInstance(getBaseContext());
         db = bc.getWritableDatabase();

        final EditText edtNome = (EditText) findViewById(R.id.editNome);
        Button btnIncluir = (Button) findViewById(R.id.btnIncluir);
        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues valores = new ContentValues();
                valores.put("nome",edtNome.getText().toString());

            db.insert("lista",null,valores);

            finish();


            }
        });
    }
}
