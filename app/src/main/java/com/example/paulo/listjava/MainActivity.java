package com.example.paulo.listjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.paulo.listjava.Entities.Lista;
import com.example.paulo.listjava.db.Banco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdapterPers meuAdp;

    @Override
    protected void onResume() {
        super.onResume();

        atualiza();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Lista> valores = carregaValores();

        ListView minhaLista = (ListView) findViewById(R.id.lista);
        this.meuAdp = new AdapterPers(this,valores);
        minhaLista.setAdapter(this.meuAdp);




        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, cadastro.class);
                startActivity(i);
            }
        });


    }




    public ArrayList<Lista> carregaValores(){

        Banco bc = Banco.getInstance(this);
        SQLiteDatabase db = bc.getReadableDatabase();

        ArrayList<Lista> valores = new ArrayList<Lista>();


        Cursor cursor = db.rawQuery("select * from lista",null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String id = cursor.getString(cursor.getColumnIndex("id"));
                Lista result = new Lista();
                result.setNome(nome);
                result.setId(Integer.parseInt(id));
                valores.add(result);
                cursor.moveToNext();
            }

        }


        return valores;


    }

    public void atualiza(){
        meuAdp.content = carregaValores();
        meuAdp.notifyDataSetChanged();
    }


    public class AdapterPers extends BaseAdapter{

        private Activity activity;
        private ArrayList<Lista> content;

        public AdapterPers(Activity act, ArrayList<Lista> valores) {

            this.activity = act;
            this.content = valores;
        }

        @Override
        public int getCount() {
            return content.size();
        }

        @Override
        public Object getItem(int i) {

            return i;
        }

        @Override
        public long getItemId(int i) {

            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            View minhaView = activity.getLayoutInflater().inflate(R.layout.linha, viewGroup, false);

            if (minhaView == null) {
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                minhaView = inflater.inflate(R.layout.linha, viewGroup, false);
            }



            TextView texto = (TextView) minhaView.findViewById(R.id.texto);
            texto.setText(content.get(i).getNome());

            Button btnAlterar = (Button) minhaView.findViewById(R.id.btnAlterar);
            btnAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("clicou",String.valueOf(content.get(i).getId()));
                    Intent telaAlterar = new Intent(MainActivity.this, Alterar.class);
                    telaAlterar.putExtra("nome",content.get(i).getNome());
                    telaAlterar.putExtra("id",content.get(i).getId());
                    startActivity(telaAlterar);

                }
            });


            return minhaView;
        }
    }
}
