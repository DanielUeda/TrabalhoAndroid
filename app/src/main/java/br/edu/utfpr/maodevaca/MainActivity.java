package br.edu.utfpr.maodevaca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.edu.utfpr.maodevaca.dao.DescricaoDAO;
import br.edu.utfpr.maodevaca.dao.ProdutoDAO;
import br.edu.utfpr.maodevaca.model.Descricao;
import br.edu.utfpr.maodevaca.model.Produto;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView acProduto;
    private Button btAdicionar, btListar, btIniciar;
    private EditText etQuantidade, etValor;
    private ArrayAdapter<Descricao> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recuperarViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarAutoComplete();
    }

    private void recuperarViews(){
        this.acProduto = findViewById(R.id.acProduto);

        this.etQuantidade = findViewById(R.id.etQtd);
        this.etValor = findViewById(R.id.etValor);

        this.btAdicionar = findViewById(R.id.btAdicionar);
        this.btListar = findViewById(R.id.btListar);
        this.btIniciar = findViewById(R.id.btIniciar);

        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarProduto();
            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarProdutos();
            }
        });

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarBD();
            }
        });
    }

    private void iniciarBD() {
        ProdutoDAO dao = new ProdutoDAO(this);
        if (dao.deleteAll()) {
            Toast.makeText(this, "Sucesso! Banco de dados pronto para inserções.", Toast.LENGTH_LONG).show();
        }
    }

    private void listarProdutos() {
        Intent i = new Intent(this, ListarActivity.class);
        startActivity(i);
    }

    private void adicionarProduto() {
        if (!validarAntesDeSalvar()) {
            return;
        }

        Produto produto = new Produto();
        produto.setDescricao(acProduto.getText().toString());
        produto.setQuantidade(Double.parseDouble(etQuantidade.getText().toString()));
        produto.setValor(Double.parseDouble(etValor.getText().toString()));
        try {
            ProdutoDAO dao = new ProdutoDAO(this);
            if (dao.insert(produto)) {
                Toast.makeText(this, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
                atualizarAutoComplete();
                zerarCampos();
            } else {
                Toast.makeText(this, "Não foi possível salvar o produto!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean validarAntesDeSalvar() {
        if (acProduto.getText().toString().isEmpty()) {
            acProduto.setError("Informe a descrição do produto");
            return false;
        } else if (etQuantidade.getText().toString().isEmpty()) {
            etQuantidade.setError("Informe a quantidade do produto");
            return false;
        } else if (etValor.getText().toString().isEmpty()){
            etValor.setError("Informe o valor do produto");
            return false;
        }

        return true;
    }

    private void atualizarAutoComplete() {
        adapter = new ArrayAdapter<Descricao>(this, android.R.layout.simple_list_item_1,
                new DescricaoDAO(this).findAll());
        this.acProduto.setAdapter(adapter);
        acProduto.setThreshold(2);
    }

    private void zerarCampos() {
        this.acProduto.setText("");
        this.etQuantidade.setText("");
        this.etValor.setText("");
        this.acProduto.requestFocus();
    }
}
