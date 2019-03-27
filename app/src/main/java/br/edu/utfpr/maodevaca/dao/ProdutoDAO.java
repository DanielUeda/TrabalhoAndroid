package br.edu.utfpr.maodevaca.dao;

import android.content.Context;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;

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

    public Produto retornaMaisBarato() throws Exception{
        try{
            String sql = "SELECT _id, descricao, quantidade, valor, MIN(valorPorUnidade) FROM produto";

            GenericRawResults<Produto> resultado = dao.getDao().queryRaw(sql,
                    new RawRowMapper<Produto>(){
                        @Override
                        public Produto mapRow(String[] columnNames, String[] resultColumns) {
                            Produto produto = new Produto();
                            produto.set_id(Integer.parseInt(resultColumns[0]));
                            produto.setDescricao(resultColumns[1]);
                            produto.setQuantidade(Double.parseDouble(resultColumns[2]));
                            produto.setValor(Double.parseDouble(resultColumns[3]));
                            produto.setValorPorUnidade(Double.parseDouble(resultColumns[4]));
                            return produto;
                        }
            });

            return resultado.getFirstResult();
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
