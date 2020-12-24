package classes.kpi.model.services;


import classes.kpi.model.dao.AbstractDAO;
import classes.kpi.model.dao.AbstractDAOFactory;
import classes.kpi.model.dao.DAOUnblockQuery;

import java.util.ArrayList;

public class UnblockQueryService {
    DAOUnblockQuery daoUnblockQuery = null;

    public UnblockQueryService() {
        daoUnblockQuery = (DAOUnblockQuery) AbstractDAOFactory.getDAO("unblockquery");
    }


    public UnblockQueryService(AbstractDAO dao) {
        daoUnblockQuery = (DAOUnblockQuery)dao;
    }

    public void close(){
        daoUnblockQuery.close();
    }
    public ArrayList<Integer> getAll(){
        return daoUnblockQuery.getAll();
    }

    public void insert(int i){
        daoUnblockQuery.insert(i);
    }

    public void delete(int i){
        daoUnblockQuery.delete(i);
    }
}
