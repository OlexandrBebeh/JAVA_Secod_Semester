package classes.kpi.model.services;

import classes.kpi.model.dao.AbstractDAO;
import classes.kpi.model.dao.AbstractDAOFactory;
import classes.kpi.model.dao.DAOAdmin;
import classes.kpi.model.dao.entities.Admin;

public class AdminService {
    DAOAdmin daoAdmin = null;

    public AdminService() {
        daoAdmin = (DAOAdmin) AbstractDAOFactory.getDAO("ADMINISTRATORS");
    }


    public AdminService(AbstractDAO dao) {
        daoAdmin = (DAOAdmin)dao;
    }

    public void close(){
        daoAdmin.close();
    }

    public Admin getAdmin(String username, String password){
        Admin admin
                =
                daoAdmin.getWhereOne
                        (" WHERE adminName = '"
                        + username + "' AND password = '"
                        + password + "' ");
        return admin;
    }
}
