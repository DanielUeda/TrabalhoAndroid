package br.edu.utfpr.maodevaca;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ListarActivity extends AppCompatActivity {

    private ListView lvProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lvProdutos = findViewById(R.id.lvProdutos);
    }

    public void btCalcularOnClick(View view) {
        //todo exibir o menor preço

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("O mais barato");
        alert.setNeutralButton("OK",null);

        alert.show();

        //Toast.makeText(this,"O menor preço é:",Toast.LENGTH_LONG).show();

    }
}
