package br.edu.utfpr.maodevaca;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.dao.GenericRawResults;

import java.util.List;

import br.edu.utfpr.maodevaca.dao.ProdutoDAO;
import br.edu.utfpr.maodevaca.model.Produto;

public class ListarActivity extends AppCompatActivity {

    private ListView lvProdutos;
    private Button btMaisBarato;
    private ArrayAdapter<Produto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        recuperarViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        popularListView();
    }

    private void popularListView() {
        adapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1,
                new ProdutoDAO(this).findAll());
        this.lvProdutos.setAdapter(adapter);
    }

    private void recuperarViews() {
        this.lvProdutos = findViewById(R.id.lvProdutos);
        this.btMaisBarato = findViewById(R.id.btMaisBarato);
        this.btMaisBarato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularOMaisBarato();
            }
        });
    }

    private void calcularOMaisBarato() {
        ProdutoDAO dao = new ProdutoDAO(this);

        String[] result = dao.retornaMaisBarato();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("O produto mais barato é " + result[1].toString() + " que custa R$" + result[0].toString() + " por unidade.");
        alert.setNeutralButton("OK",null);

        alert.show();

        //Toast.makeText(this,"O menor preço é:",Toast.LENGTH_LONG).show();

    }
}
