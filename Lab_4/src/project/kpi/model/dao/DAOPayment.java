package project.kpi.model.dao;

import project.kpi.model.dao.entities.Payment;

import java.util.ArrayList;
import java.sql.*;

public class DAOPayment extends AbstractDAO<Payment> {

    private final String SELECT_QUERY = "select * from Payments ";

    DAOPayment(Connection connection) {
        super(connection);
    }

    @Override
    public Payment getByID(int id) {


        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY + " where Payment_id = " + id);
            if(resultSet.next()) {
                Payment payment = new Payment.PaymentBuilder()
                        .setPaymentID(resultSet.getInt(1))
                        .setAccountID(resultSet.getInt(2))
                        .setScore(resultSet.getDouble(3))
                        .setRecipientAccount(resultSet.getString(4))
                        .setPaymentState(resultSet.getString(5))
                        .setPaymentDate(resultSet.getDate(6))
                        .build();
                return payment;
            }
        }
            catch (SQLException e){
            System.err.println(e);
        }
        finally {
            return null;
        }
    }
    @Override
    public void insert(Payment payment) {
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String sql = "INSERT INTO Payments(account_id,score ,recipientAccount ,paymentState) " +
                        " VALUES(?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,((Payment) payment).getAccountID());
                preparedStatement.setDouble(2,((Payment) payment).getScore());
                preparedStatement.setString(3,((Payment) payment).getRecipientAccount());
                preparedStatement.setString(4,((Payment) payment).getPaymentState());
                preparedStatement.executeUpdate();
            }
            catch (Exception e){
                System.err.println(e);
            }
    }

    @Override
    public void update(Payment payment) {
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String sql = "UPDATE Payments " +
                        "SET account_id = ?," +
                        "score = ?," +
                        "recipientAccount = ?," +
                        "paymentState = ?" +
                        "WHERE payment_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,((Payment) payment).getAccountID());
                preparedStatement.setDouble(2,((Payment) payment).getScore());
                preparedStatement.setString(3,((Payment) payment).getRecipientAccount());
                preparedStatement.setString(4,((Payment) payment).getPaymentState());
                preparedStatement.setInt(5,((Payment) payment).getPaymentID());
                preparedStatement.executeUpdate();
            }
            catch (Exception e){
                System.err.println(e);
            }
    }

    @Override
    public ArrayList<Payment> getAll() {
        Statement statement=null;
        ArrayList<Payment> payments = new ArrayList<Payment>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY);

            while(resultSet.next()) {
               payments.add(new Payment.PaymentBuilder()
                       .setPaymentID(resultSet.getInt(1))
                       .setAccountID(resultSet.getInt(2))
                       .setScore(resultSet.getDouble(3))
                       .setRecipientAccount(resultSet.getString(4))
                       .setPaymentState(resultSet.getString(5))
                       .setPaymentDate(resultSet.getDate(6))
                       .build()) ;
            }
            return payments;
        }
        catch (SQLException e){
            System.err.println(e);
        }
        finally {
            closeStatement(statement);
            return payments;
        }
    }


    @Override
    public ArrayList<Payment> getWhere(String str) {
        Statement statement=null;
        ArrayList<Payment> payments = new ArrayList<Payment>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY + str);

            while(resultSet.next()) {
                payments.add(new Payment.PaymentBuilder()
                        .setPaymentID(resultSet.getInt(1))
                        .setAccountID(resultSet.getInt(2))
                        .setScore(resultSet.getDouble(3))
                        .setRecipientAccount(resultSet.getString(4))
                        .setPaymentState(resultSet.getString(5))
                        .setPaymentDate(resultSet.getDate(6))
                        .build()) ;
            }

        }
        catch (SQLException e){
            System.err.println(e);
        }
        finally {
            closeStatement(statement);
            return payments;
        }
    }

    @Override
    public Payment getWhereOne(String str) {
        Statement statement=null;
        Payment payment = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY + str);
            payment = new Payment.PaymentBuilder()
                    .setPaymentID(resultSet.getInt(1))
                    .setAccountID(resultSet.getInt(2))
                    .setScore(resultSet.getDouble(3))
                    .setRecipientAccount(resultSet.getString(4))
                    .setPaymentState(resultSet.getString(5))
                    .setPaymentDate(resultSet.getDate(6))
                    .build();

        }
        catch (SQLException e){

        }
        finally {
            closeStatement(statement);
            return payment;
        }
    }

}
