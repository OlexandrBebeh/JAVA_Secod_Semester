package project.kpi.model.services;


import project.kpi.model.dao.AbstractDAOFactory;
import project.kpi.model.dao.DAOUnblockQuery;

import java.util.ArrayList;

public class UnblockQueryService {

    public ArrayList<Integer> getAll(){
        DAOUnblockQuery daoUnblockQuery = (DAOUnblockQuery) AbstractDAOFactory.getDAO("unblockquery");
        ArrayList<Integer> query = daoUnblockQuery.getAll();
        daoUnblockQuery.close();
        return query;
    }

    public void insert(int i){
        DAOUnblockQuery daoUnblockQuery = (DAOUnblockQuery) AbstractDAOFactory.getDAO("unblockquery");
        daoUnblockQuery.insert(i);
        daoUnblockQuery.close();
    }

    public void delete(int i){
        DAOUnblockQuery daoUnblockQuery = (DAOUnblockQuery) AbstractDAOFactory.getDAO("unblockquery");
        daoUnblockQuery.delete(i);
        daoUnblockQuery.close();
    }
}
