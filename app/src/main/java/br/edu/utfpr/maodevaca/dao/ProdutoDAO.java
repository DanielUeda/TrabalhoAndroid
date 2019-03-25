package br.edu.utfpr.maodevaca.dao;

import android.content.Context;

import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

import br.edu.utfpr.maodevaca.model.Descricao;
import br.edu.utfpr.maodevaca.model.Produto;

public class ProdutoDAO extends GenericDAO<Produto> {

    private Context context;

    public ProdutoDAO(Context context) {
        super(context, Produto.class);
        this.context = context;
    }

    @Override
    public boolean insert(Produto obj) throws Exception {
        obj.setDescricao(obj.getDescricao().toUpperCase());
        DescricaoDAO descricaoDAO = new DescricaoDAO(context);
        descricaoDAO.insert(new Descricao(obj.getDescricao()));
        return super.insert(obj);
    }

    public boolean deleteAll(){
        try {
            return dao.executeRaw("DELETE FROM produto") > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
