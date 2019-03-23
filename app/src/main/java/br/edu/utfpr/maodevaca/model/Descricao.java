package br.edu.utfpr.maodevaca.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "descricao")
public class Descricao implements Serializable {

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private int _id;
    @DatabaseField
    private String descricao;

    public Descricao() {}

    public Descricao(String descricao) {
        this.descricao = descricao;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
