package project.kpi.model.dao;

import java.sql.Connection;

public class AbstractDAOFactory {
    private enum Tables {clients,accounts,payments,administrators,unblockquery};


    public static AbstractDAO getDAO(String table){
        Tables table_en = Tables.valueOf(table.toLowerCase());
        if(table_en==Tables.payments)
            return new DAOPayment(ConnectionPool.getConnection());
        else if(table_en==Tables.clients)
            return new DAOClient(ConnectionPool.getConnection());
        else if(table_en==Tables.accounts)
            return new DAOAccount(ConnectionPool.getConnection());
        else if(table_en==Tables.administrators)
            return new DAOAdmin(ConnectionPool.getConnection());
        else if(table_en==Tables.unblockquery)
            return new DAOUnblockQuery(ConnectionPool.getConnection());
        else throw new RuntimeException("Enum not found");
    }

    public static AbstractDAO getDAO(String table, Connection con){
        Tables table_en = Tables.valueOf(table.toLowerCase());
        if(table_en==Tables.payments)
            return new DAOPayment(con);
        else if(table_en==Tables.clients)
            return new DAOClient(con);
        else if(table_en==Tables.accounts)
            return new DAOAccount(con);
        else if(table_en==Tables.administrators)
            return new DAOAdmin(con);
        else if(table_en==Tables.unblockquery)
            return new DAOUnblockQuery(con);
        else throw new RuntimeException("Enum not found");
    }

}
