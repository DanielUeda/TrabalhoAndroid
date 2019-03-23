package br.edu.utfpr.maodevaca.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.util.List;

import br.edu.utfpr.maodevaca.helper.DatabaseHelper;

public class GenericDAO<E> extends DatabaseHelper {

    protected Dao<E, Integer> dao;

    public GenericDAO(Context context, Class<E> type) {
        super(context);
        try {
            dao = DaoManager.createDao(getConnectionSource(), type);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean insert(E obj) {
        try {
            return dao.create(obj) > 0;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean update(E obj) {
        try {
            return dao.update(obj) > 0;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(E obj) {
        try {
            return dao.delete(obj) > 0;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(Integer id){
        try {
            return dao.deleteById(id) > 0;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public E findById(Integer id) {
        try {
            return dao.queryForId(id);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<E> findAll(){
        try {
            return dao.queryForAll();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
