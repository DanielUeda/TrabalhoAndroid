package br.edu.utfpr.maodevaca.dao;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import br.edu.utfpr.maodevaca.model.Descricao;

public class DescricaoDAO extends GenericDAO<Descricao> {

    public DescricaoDAO(Context context) {
        super(context, Descricao.class);
    }

    @Override
    public boolean insert(Descricao obj) {
        String descricaoUpper = obj.getDescricao().toUpperCase();
        obj.setDescricao(descricaoUpper);
        if (findByDescricao(descricaoUpper) == null) {
            return super.insert(obj);
        }
        return false;
    }

    public Descricao findByDescricao(String descricao) {
        QueryBuilder<Descricao, Integer> qb = dao.queryBuilder();
        try {
            return qb.where().eq("descricao", descricao).queryForFirst();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
