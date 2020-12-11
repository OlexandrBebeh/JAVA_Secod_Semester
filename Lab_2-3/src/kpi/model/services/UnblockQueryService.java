package kpi.model.services;


import kpi.model.dao.AbstractDAOFactory;
import kpi.model.dao.DAOUnblockQuery;

import java.util.ArrayList;

public class UnblockQueryService {
    private DAOUnblockQuery daoUnblockQuery = (DAOUnblockQuery) AbstractDAOFactory.getDAO("unblockquery");

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
