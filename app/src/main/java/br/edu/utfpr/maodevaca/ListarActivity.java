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

import java.text.DecimalFormat;
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
        try {
            Produto produtoMaisBarato = new ProdutoDAO(this).retornaMaisBarato();
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setCancelable(false);
            alert.setTitle("Produto mais barato");
            alert.setMessage("O produto mais barato é " + produtoMaisBarato.getDescricao() +
                    ", que custa R$" + new DecimalFormat("0.00").format(produtoMaisBarato.getValorPorUnidade())
                    + " por unidade.");
            alert.setNeutralButton("OK",null);
            alert.show();
        } catch (Exception ex){
            Toast.makeText(this, "Erro ao consultar o produto mais barato: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}