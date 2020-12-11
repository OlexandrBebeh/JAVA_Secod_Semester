package kpi.model.dao;

import java.sql.*;
import java.util.ArrayList;

public class DAOUnblockQuery extends AbstractDAO<Integer>{
    private String SELECT_QUERY = " SELECT * FROM UnblockQuery ";
    DAOUnblockQuery(Connection connection) {
        super(connection);
    }

    @Override
    public Integer getByID(int id) {
        Statement statement=null;
        Integer integer = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY + " where account_id = " + id);
            integer = resultSet.getInt(1);
        }
        catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        finally {
            closeStatement(statement);
            return integer;
        }
    }

    @Override
    public void insert(Integer integer) {
        PreparedStatement preparedStatement = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String sql = "INSERT INTO UnblockQuery(account_id) " +
                    "VALUES(?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, integer);
            preparedStatement.executeUpdate();
        }
        catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }finally {
            closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Integer integer) {
    }


    @Override
    public ArrayList<Integer> getAll() {
        Statement statement=null;
        ArrayList<Integer> integers = new ArrayList<Integer>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY);
            while (resultSet.next()){
                integers.add(resultSet.getInt(1));
            }
        }
        catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        finally {
            closeStatement(statement);
            return integers;
        }
    }

    @Override
    public ArrayList<Integer> getWhere(String str) {
        Statement statement=null;
        ArrayList<Integer> integers = new ArrayList<>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY + str);
            while (resultSet.next()){
                integers.add(resultSet.getInt(1));
            }
        }
        catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        finally {
            closeStatement(statement);
            return integers;
        }
    }

    @Override
    public Integer getWhereOne(String str) {
        Statement statement=null;
        Integer integer = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY + str);
            integer = resultSet.getInt(1);
        }
        catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        finally {
            closeStatement(statement);
            return integer;
        }
    }

    public void delete(Integer integer) {
        PreparedStatement preparedStatement = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String sql = "DELETE FROM UnblockQuery " +
                    " WHERE account_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, integer);
            preparedStatement.executeUpdate();
        }
        catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }finally {
            closeStatement(preparedStatement);
        }
    }


}
