package br.edu.utfpr.maodevaca.dao;

import android.content.Context;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import br.edu.utfpr.maodevaca.model.Descricao;
import br.edu.utfpr.maodevaca.model.Produto;

public class ProdutoDAO {

    private Context context;
    private GenericDAO<Produto> dao;

    public ProdutoDAO(Context context) {
        this.context = context;
        dao = new GenericDAO<Produto>(context, Produto.class);
    }

    public boolean insert(Produto produto) throws Exception {
        produto.setDescricao(produto.getDescricao().toUpperCase());
        DescricaoDAO descricaoDAO = new DescricaoDAO(context);
        descricaoDAO.insert(new Descricao(produto.getDescricao()));
        return dao.insert(produto);
    }

    public List<Produto> findAll(){
        return dao.findAll();
    }

    public boolean deleteAll(){
        try {
            return dao.getDao().executeRaw("DELETE FROM produto") > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public String[] retornaMaisBarato(){
        String[] value = null;
        GenericRawResults results = null;
        try{
            QueryBuilder qb = dao.getDao().queryBuilder();
            qb.selectRaw("MIN(valorPorUnidade)","descricao");
            results = dao.getDao().queryRaw(qb.prepareStatementString());
            value = (String[]) results.getFirstResult();
            return value;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return value;
    }
}
