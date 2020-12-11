package kpi.model.services;

import kpi.model.dao.AbstractDAOFactory;
import kpi.model.dao.DAOAdmin;
import kpi.model.dao.Entities.Admin;

public class AdminService {
    private DAOAdmin daoAdmin;

    AdminService(){
        daoAdmin = (DAOAdmin) AbstractDAOFactory.getDAO("ADMINISTRATORS");
    }


    public Admin getAdmin(UserHolder userHolder){

        Admin admin
                =
                daoAdmin.getWhereOne
                        (" WHERE adminName = '"
                        + userHolder.getUserName() + "' AND password = '"
                        + userHolder.getPassword() + "' ");
        return admin;
    }
}
