package br.edu.utfpr.maodevaca.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@DatabaseTable(tableName = "produto")
public class Produto implements Serializable {

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private Integer _id;
    @DatabaseField
    private String descricao;
    @DatabaseField
    private double quantidade;
    @DatabaseField
    private double valor;
    @DatabaseField
    private double valorPorUnidade;

    public Produto(){}

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
        calcularValorPorUnidade();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
        calcularValorPorUnidade();
    }

    public double getValorPorUnidade() {
        return valorPorUnidade;
    }

    public void setValorPorUnidade(double valorPorUnidade){
        this.valorPorUnidade = valorPorUnidade;
    }

    private void calcularValorPorUnidade(){
        valorPorUnidade = new BigDecimal(valor / quantidade)
                .setScale(2, RoundingMode.HALF_EVEN)
                .doubleValue();
    }

    @Override
    public String toString() {
        return this._id + " - " + this.descricao;
    }
}
