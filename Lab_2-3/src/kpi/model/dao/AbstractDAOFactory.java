package kpi.model.dao;

import java.sql.Connection;

public class AbstractDAOFactory {
    private enum Tables {clients,accounts,payments,administrators,unblockquery};


    public static AbstractDAO getDAO(String table){
        Tables tables = Tables.valueOf(table.toLowerCase());
        return switch (tables) {
            case payments -> new DAOPayment(ConnectionPool.getConnection());
            case clients -> new DAOClient(ConnectionPool.getConnection());
            case accounts -> new DAOAccount(ConnectionPool.getConnection());
            case administrators -> new DAOAdmin(ConnectionPool.getConnection());
            case unblockquery -> new DAOUnblockQuery(ConnectionPool.getConnection());
            default -> throw new RuntimeException("Enum not found");
        };
    }

    public static AbstractDAO getDAO(String table, Connection con){
        Tables tables = Tables.valueOf(table.toLowerCase());
        return switch (tables) {
            case payments -> new DAOPayment(con);
            case clients -> new DAOClient(con);
            case accounts -> new DAOAccount(con);
            case administrators -> new DAOAdmin(con);
            case unblockquery -> new DAOUnblockQuery(con);
            default -> throw new RuntimeException("Enum not found");
        };
    }

}
