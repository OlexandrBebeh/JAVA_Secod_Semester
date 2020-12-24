package test.daotest;
import classes.kpi.model.dao.AbstractDAOFactory;
import classes.kpi.model.dao.DAOClient;
import classes.kpi.model.dao.entities.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DAOTest {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Before
    public void init(){
        connection = mock(Connection.class);
        statement = mock(Statement.class);
        resultSet = mock(ResultSet.class);
    }

    @Test
    public void getAllTest() throws SQLException {
        DAOClient daoClient = (DAOClient) AbstractDAOFactory.getDAO("CLIENTS",connection);

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(" select * from clients ")).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);

        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4);
        when(resultSet.getString(2)).thenReturn("Client1").thenReturn("Client2").thenReturn("Client3").thenReturn("Client4");
        when(resultSet.getString(3)).thenReturn("Client1").thenReturn("Client2").thenReturn("Client3").thenReturn("Client4");
        when(resultSet.getString(4)).thenReturn("Client1").thenReturn("Client2").thenReturn("Client3").thenReturn("Client4");
        when(resultSet.getString(5)).thenReturn("Client1").thenReturn("Client2").thenReturn("Client3").thenReturn("Client4");
        when(resultSet.getString(6)).thenReturn("Client1").thenReturn("Client2").thenReturn("Client3").thenReturn("Client4");
        when(resultSet.getString(7)).thenReturn("N").thenReturn("N").thenReturn("Y").thenReturn("N");

        ArrayList<Client> clients = daoClient.getAll();

        assertEquals(4,clients.size());
        assertEquals(1,clients.get(0).getClientID());
        assertEquals("Client2",clients.get(1).getClientName());
        assertEquals("Client3",clients.get(2).getFirstName());
        assertEquals("Client4",clients.get(3).getSecondName());
        assertEquals(false,clients.get(0).getBlocked());

    }

    @Test
    public void getByIDTest() throws SQLException {
        DAOClient daoClient = (DAOClient) AbstractDAOFactory.getDAO("CLIENTS",connection);

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery("select * from Clients where Client_id = 1")).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);

        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("Client1");
        when(resultSet.getString(3)).thenReturn("Client1");
        when(resultSet.getString(4)).thenReturn("Client1");
        when(resultSet.getString(5)).thenReturn("Client1");
        when(resultSet.getString(6)).thenReturn("Client1");
        when(resultSet.getString(7)).thenReturn("N");

        Client client = daoClient.getByID(1);

        assertEquals(1,client.getClientID());
        assertEquals("Client1",client.getClientName());
        assertEquals("Client1",client.getFirstName());
        assertEquals("Client1",client.getSecondName());
        assertEquals(false,client.getBlocked());
    }

    @Test
    public void insertTest() throws SQLException {
        DAOClient daoClient = (DAOClient) AbstractDAOFactory.getDAO("CLIENTS",connection);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Client client =  new Client.ClientBuilder().setClientID(1)
                .setClientName("Name")
                .setFirstName("FirstName")
                .setSecondName("SecondName")
                .setEmail("mail@mail.com")
                .setPassword("password")
                .setBlocked(false).build();
        daoClient.insert(client);

        verify(preparedStatement).executeUpdate();
    }
}
