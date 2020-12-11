package project.kpi.model.dao;

import project.kpi.model.dao.entities.Admin;


import java.sql.*;
import java.util.ArrayList;

public class DAOAdmin extends AbstractDAO<Admin>{

    private final String SELECT_QUERY = " select * from Administrators ";
    DAOAdmin(Connection connection) {
        super(connection);
    }

    @Override
    public Admin getByID(int id) {
        Statement statement=null;
        Admin admin = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY + " where Admin_id = " + id);
            admin = new Admin();
            admin.setAdminID(resultSet.getInt(1));
            admin.setAdminName(resultSet.getString(2));
            admin.setFirstName(resultSet.getString(3));
            admin.setSecondName(resultSet.getString(4));
            admin.setPassword(resultSet.getString(5));
        }
        catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        finally {
            closeStatement(statement);
            return admin;
        }
    }

    @Override
    public void insert(Admin admin) {
        PreparedStatement preparedStatement = null;
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String sql = "UPDATE Administrators " +
                        "SET adminName = ?," +
                        "firstName = ?," +
                        "secondName = ?," +
                        "password = ?" +
                        "WHERE admin_id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, admin.getAdminName());
                preparedStatement.setString(2, admin.getFirstName());
                preparedStatement.setString(3, admin.getSecondName());
                preparedStatement.setString(4, admin.getPassword());
                preparedStatement.setInt(5, admin.getAdminID());
                preparedStatement.executeUpdate();
            }
            catch (SQLException | ClassNotFoundException e){
                throw new RuntimeException(e.getMessage());
            }finally {
                closeStatement(preparedStatement);
            }

    }

    @Override
    public void update(Admin admin) {
        PreparedStatement preparedStatement = null;
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String sql = "INSERT INTO Administrators(adminName ,firstName  ,secondName  ,password ) " +
                        "VALUES(?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, ((Admin) admin).getAdminName());
                preparedStatement.setString(2, ((Admin) admin).getFirstName());
                preparedStatement.setString(3, ((Admin) admin).getSecondName());
                preparedStatement.setString(4, ((Admin) admin).getPassword());
                preparedStatement.executeUpdate();
            }
            catch (Exception e){
            }finally {
                closeStatement(preparedStatement);
            }
    }

    @Override
    public ArrayList<Admin> getAll() {
        Statement statement = null;
        ArrayList<Admin> admins = new ArrayList<Admin>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY);

            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminID(resultSet.getInt(1));
                admin.setAdminName(resultSet.getString(2));
                admin.setFirstName(resultSet.getString(3));
                admin.setSecondName(resultSet.getString(4));
                admin.setPassword(resultSet.getString(5));
                admins.add(admin);
            }
            return admins;
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            closeStatement(statement);
            return admins;
        }
    }

    @Override
    public ArrayList<Admin> getWhere(String str) {
        Statement statement = null;
        ArrayList<Admin> admins = new ArrayList<Admin>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY + str);

            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminID(resultSet.getInt(1));
                admin.setAdminName(resultSet.getString(2));
                admin.setFirstName(resultSet.getString(3));
                admin.setSecondName(resultSet.getString(4));
                admin.setPassword(resultSet.getString(5));
                admins.add(admin);
            }
            return admins;
        }
        catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        finally {
            closeStatement(statement);
            return admins;
        }
    }

    @Override
    public Admin getWhereOne(String str) {
        Statement statement = null;
        Admin admin = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY + str);
            if(resultSet.next()) {
                admin = new Admin();
                admin.setAdminID(resultSet.getInt(1));
                admin.setAdminName(resultSet.getString(2));
                admin.setFirstName(resultSet.getString(3));
                admin.setSecondName(resultSet.getString(4));
                admin.setPassword(resultSet.getString(5));
                statement.close();
            }
        }
        catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }finally {
            closeStatement(statement);
            return admin;
        }
    }

}
