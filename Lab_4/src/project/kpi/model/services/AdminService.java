package project.kpi.model.services;

import project.kpi.model.dao.AbstractDAOFactory;
import project.kpi.model.dao.DAOAdmin;
import project.kpi.model.dao.entities.Admin;

public class AdminService {


    public Admin getAdmin(String username, String password){
        DAOAdmin daoAdmin = (DAOAdmin) AbstractDAOFactory.getDAO("ADMINISTRATORS");
        Admin admin
                =
                daoAdmin.getWhereOne
                        (" WHERE adminName = '"
                        + username + "' AND password = '"
                        + password + "' ");
        daoAdmin.close();
        return admin;
    }
}
