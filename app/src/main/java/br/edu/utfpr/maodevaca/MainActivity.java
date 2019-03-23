package br.edu.utfpr.maodevaca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView acProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acProduto = findViewById(R.id.acProduto);
    }

    public void btIniciarOnClick(View view) {
    }

    public void btListarOnClick(View view) {
    }

    public void btAddProdutoOnClick(View view) {
    }
}
