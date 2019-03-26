package br.edu.utfpr.maodevaca.dao;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.util.List;

import br.edu.utfpr.maodevaca.model.Descricao;

public class DescricaoDAO  {

    private Context context;
    private GenericDAO<Descricao> dao;

    public DescricaoDAO(Context context) {
        this.context = context;
        this.dao = new GenericDAO<Descricao>(context, Descricao.class);
    }

    public boolean insert(Descricao descricao) throws Exception {
        String descricaoUpper = descricao.getDescricao().toUpperCase();
        descricao.setDescricao(descricaoUpper);
        if (findByDescricao(descricaoUpper) == null){
            return dao.insert(descricao);
        }
        return false;

    }

    public Descricao findByDescricao(String descricao) {
        QueryBuilder<Descricao, Integer> qb = dao.getDao().queryBuilder();
        try {
            return qb.where().eq("descricao", descricao).queryForFirst();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<Descricao> findAll() {
        return dao.findAll();
    }
}
