package br.edu.utfpr.maodevaca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView acProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acProduto = findViewById(R.id.acProduto);

        /*ArrayAdapter meuAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,cidade);

        acProduto.setAdapter(meuAdapter);*/
        acProduto.setThreshold(2);
    }

    public void btIniciarOnClick(View view) {
        //todo delete

        Toast.makeText(this,"Iniciando novo comparativo",Toast.LENGTH_LONG).show();
    }

    public void btListarOnClick(View view) {
        Intent i = new Intent(this, ListarActivity.class);
        startActivity(i);
    }

    public void btAddProdutoOnClick(View view) {
        //todo add produto

        Toast.makeText(this,"Produto adicionado com sucesso",Toast.LENGTH_LONG).show();
    }
}
