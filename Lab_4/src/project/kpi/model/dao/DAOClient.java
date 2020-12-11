package project.kpi.model.dao;

import project.kpi.model.dao.entities.Client;

import java.sql.*;
import java.util.ArrayList;

public class DAOClient extends AbstractDAO<Client>{

    DAOClient(Connection connection) {
        super(connection);
    }

    @Override
    public Client getByID(int id) {
        Statement statement=null;
        Client client =null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from Clients where Client_id = " + id);
            if(resultSet.next()) {
                client = new Client.ClientBuilder()
                        .setClientID(resultSet.getInt(1))
                        .setClientName(resultSet.getString(2))
                        .setFirstName(resultSet.getString(3))
                        .setSecondName(resultSet.getString(4))
                        .setPassword(resultSet.getString(5))
                        .setEmail(resultSet.getString(6))
                        .setBlocked(!resultSet.getString(7).equals("N"))
                        .build();

            }
        }
        catch (SQLException e){
            System.err.println(e);
        }
        finally {
            closeStatement(statement);
            return client;
        }
    }

    @Override
    public void insert(Client client) {
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String sql = "INSERT INTO Clients(clientName," +
                        "firstName," +
                        "secondName," +
                        "password," +
                        "email) " +
                        "VALUES( ? , ? , ? , ? , ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,client.getClientName());
                preparedStatement.setString(2,client.getFirstName());
                preparedStatement.setString(3,client.getSecondName());
                preparedStatement.setString(4,client.getPassword());
                preparedStatement.setString(5,client.getEmail());
                preparedStatement.executeUpdate();
            }
            catch (Exception e) {
                System.err.println(e);
            }
    }

    @Override
    public void update(Client obj) {
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String sql = "UPDATE Clients " +
                        "SET clientName = ?," +
                        "firstName = ?," +
                        "secondName = ?," +
                        "password = ?," +
                        "email = ?," +
                        "blocked = ?" +
                        "WHERE client_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,((Client) obj).getClientName());
                preparedStatement.setString(2,((Client) obj).getFirstName());
                preparedStatement.setString(3,((Client) obj).getSecondName());
                preparedStatement.setString(4,((Client) obj).getPassword());
                preparedStatement.setString(5,((Client) obj).getEmail());
                preparedStatement.setString(6,(!((Client) obj).getBlocked())?"N":"Y");
                preparedStatement.setInt(7,((Client) obj).getClientID());
                preparedStatement.executeUpdate();
            }
            catch (Exception e){
                System.err.println(e);
            }
    }

    @Override
    public ArrayList<Client> getAll() {
        Statement statement =  null;
        ArrayList<Client> clients = new ArrayList<Client>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement =connection.createStatement();
            ResultSet resultSet=statement.executeQuery(" select * from clients ");

            while (resultSet.next()) {
                clients.add(new Client.ClientBuilder()
                        .setClientID(resultSet.getInt(1))
                        .setClientName(resultSet.getString(2))
                        .setFirstName(resultSet.getString(3))
                        .setSecondName(resultSet.getString(4))
                        .setPassword(resultSet.getString(5))
                        .setEmail(resultSet.getString(6))
                        .setBlocked(!resultSet.getString(7).equals("N"))
                        .build());
            }
        }
        catch (SQLException e){
            System.err.println(e);
        }
        finally {
            closeStatement(statement);
            return clients;
        }
    }

    @Override
    public ArrayList<Client> getWhere(String str) {
        Statement statement =  null;
        ArrayList<Client> clients = new ArrayList<Client>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from Clients " + str);

            while (resultSet.next()) {
                clients.add(new Client.ClientBuilder()
                        .setClientID(resultSet.getInt(1))
                        .setClientName(resultSet.getString(2))
                        .setFirstName(resultSet.getString(3))
                        .setSecondName(resultSet.getString(4))
                        .setPassword(resultSet.getString(5))
                        .setEmail(resultSet.getString(6))
                        .setBlocked(!resultSet.getString(7).equals("N"))
                        .build());
            }
        }
        catch (SQLException e){
            System.err.println(e);
        }
        finally {
            closeStatement(statement);
            return clients;
        }
    }

    @Override
    public Client getWhereOne(String str) {
        Statement statement= null;
        Client client = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from Clients " + str);

            if(resultSet.next()) {
                client = new Client.ClientBuilder()
                        .setClientID(resultSet.getInt(1))
                        .setClientName(resultSet.getString(2))
                        .setFirstName(resultSet.getString(3))
                        .setSecondName(resultSet.getString(4))
                        .setPassword(resultSet.getString(5))
                        .setEmail(resultSet.getString(6))
                        .setBlocked(!resultSet.getString(7).equals("N"))
                        .build();
            }
        }
        catch (SQLException e){
            System.err.println(e);
        }
        finally {
            closeStatement(statement);
            return client;
        }
    }

}
