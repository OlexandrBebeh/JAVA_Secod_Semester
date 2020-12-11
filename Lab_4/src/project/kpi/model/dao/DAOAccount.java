package project.kpi.model.dao;

import project.kpi.model.dao.entities.Account;

import java.sql.*;
import java.util.ArrayList;

public class DAOAccount extends AbstractDAO<Account>{

    private static final String SELECT_COMMAND=" select * from Accounts ";

    DAOAccount(Connection connection) {
        super(connection);
    }

    @Override
    public Account getByID(int id) {
        Account account = null;
        Statement statement = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
             statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_COMMAND+" where Account_id = " + id);
            resultSet.next();
            account = new Account.AccountBuilder()
                    .setAccountID(resultSet.getInt(1))
                    .setClientID(resultSet.getInt(2))
                    .setAccountName(resultSet.getString(3))
                    .setScore(resultSet.getDouble(4))
                    .setBlocked(!resultSet.getString(5).equals("N"))
                     .setCreditCardNumber(resultSet.getString(6))
                    .build();
        }
        catch (SQLException e){

        }
        finally {
            closeStatement(statement);
            return account;
        }
    }

    @Override
    public void insert(Account obj) {

            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String sql = "INSERT INTO Accounts(client_id,accountName ,score ,blocked,CreditCardNumber) " +
                        "VALUES(?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,((Account) obj).getClientID());
                preparedStatement.setString(2,((Account) obj).getAccountName());
                preparedStatement.setDouble(3,((Account) obj).getScore());
                preparedStatement.setString(4,(!((Account) obj).getBlocked())?"N":"Y");
                preparedStatement.setString(5,((Account) obj).getCreditCardNumber());
                preparedStatement.executeUpdate();
            }
            catch (Exception e){

            }
    }

    @Override
    public void update(Account obj) {
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String sql = "UPDATE Accounts " +
                        "SET client_id = ?," +
                        "accountName = ?," +
                        "score = ?," +
                        "blocked = ?" +
                        "WHERE account_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,((Account) obj).getClientID());
                preparedStatement.setString(2,((Account) obj).getAccountName());
                preparedStatement.setDouble(3,((Account) obj).getScore());
                preparedStatement.setString(4,(!((Account) obj).getBlocked())?"N":"Y");
                preparedStatement.setInt(5,((Account) obj).getAccountID());
                preparedStatement.executeUpdate();
            }
            catch (Exception e){

            }
    }

    @Override
    public ArrayList<Account> getAll() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        Statement statement = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_COMMAND);

            while (resultSet.next()) {
                accounts.add(new Account.AccountBuilder()
                        .setAccountID(resultSet.getInt(1))
                        .setClientID(resultSet.getInt(2))
                        .setAccountName(resultSet.getString(3))
                        .setScore(resultSet.getDouble(4))
                        .setBlocked(!resultSet.getString(5).equals("N"))
                        .setCreditCardNumber(resultSet.getString(6))
                        .build());
            }
        }
        catch (SQLException e){

        }
        finally {
            closeStatement(statement);
            return accounts;
        }
    }

    @Override
    public ArrayList<Account> getWhere(String str) {
        ArrayList<Account> accounts = new ArrayList<Account>();
        Statement statement = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_COMMAND + str);

            while (resultSet.next()) {
                accounts.add(new Account.AccountBuilder()
                        .setAccountID(resultSet.getInt(1))
                        .setClientID(resultSet.getInt(2))
                        .setAccountName(resultSet.getString(3))
                        .setScore(resultSet.getDouble(4))
                        .setBlocked(!resultSet.getString(5).equals("N"))
                        .setCreditCardNumber(resultSet.getString(6))
                        .build());
            }
            return accounts;
        }
        catch (SQLException e){

        }
        finally {
            closeStatement(statement);
            return accounts;
        }
    }

    @Override
    public Account getWhereOne(String str) {
        Account account = null;
        Statement statement = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_COMMAND + str);
            resultSet.next();
            account = new Account.AccountBuilder()
                    .setAccountID(resultSet.getInt(1))
                    .setClientID(resultSet.getInt(2))
                    .setAccountName(resultSet.getString(3))
                    .setScore(resultSet.getDouble(4))
                    .setBlocked(!resultSet.getString(5).equals("N"))
                    .setCreditCardNumber(resultSet.getString(6))
                    .build();
        }
        catch (SQLException e){

        }
        finally {
            closeStatement(statement);
            return account;
        }
    }

}
