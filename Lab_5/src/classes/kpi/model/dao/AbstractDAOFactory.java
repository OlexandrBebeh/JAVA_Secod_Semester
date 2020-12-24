package classes.kpi.model.dao;

import java.sql.Connection;

public class AbstractDAOFactory {
    private enum Tables {CLIENTS,ACCOUNTS,PAYMENTS,ADMINISTRATORS,UNBLOCKQUERY};


    public static AbstractDAO getDAO(String table){
        Tables table_en = Tables.valueOf(table.toUpperCase());

        if(table_en==Tables.PAYMENTS)
            return new DAOPayment(ConnectionPool.getConnection());
        else if(table_en==Tables.CLIENTS)
            return new DAOClient(ConnectionPool.getConnection());
        else if(table_en==Tables.ACCOUNTS)
            return new DAOAccount(ConnectionPool.getConnection());
        else if(table_en==Tables.ADMINISTRATORS)
            return new DAOAdmin(ConnectionPool.getConnection());
        else if(table_en==Tables.UNBLOCKQUERY)
            return new DAOUnblockQuery(ConnectionPool.getConnection());

        else throw new RuntimeException("Enum not found");
    }

    public static AbstractDAO getDAO(String table, Connection con){
        Tables table_en = Tables.valueOf(table.toUpperCase());
        if(table_en==Tables.PAYMENTS)
            return new DAOPayment(con);
        else if(table_en==Tables.CLIENTS)
            return new DAOClient(con);
        else if(table_en==Tables.ACCOUNTS)
            return new DAOAccount(con);
        else if(table_en==Tables.ADMINISTRATORS)
            return new DAOAdmin(con);
        else if(table_en==Tables.UNBLOCKQUERY)
            return new DAOUnblockQuery(con);
        else throw new RuntimeException("Enum not found");
    }

}
