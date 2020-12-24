package test.servicetest;


import classes.kpi.model.dao.DAOClient;
import classes.kpi.model.dao.entities.Client;
import classes.kpi.model.services.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    DAOClient daoClient;


    @Before
    public void init(){
        daoClient = mock(DAOClient.class);
    }


    @Test
    public void getAllTest0(){
        ClientService clientService = new ClientService(daoClient);

        ArrayList<Client> arr =  new ArrayList<Client>();

        when(daoClient.getAll()).thenReturn(arr);

        ArrayList<Client> query = clientService.getAll();
        assertEquals(arr, query);
        assertEquals(0, query.size());
        assertTrue(query.isEmpty());
        verify(daoClient).getAll();

    }

    @Test
    public void getAllTest1(){
        ClientService clientService = new ClientService(daoClient);

        ArrayList<Client> arr =  new ArrayList<Client>();
        for(int i = 0 ;i < 10;i++){
            arr.add(
                    new Client.ClientBuilder()
                            .setClientID(i)
                            .setClientName("Name" + i)
                            .setFirstName("FirstName" + i)
                            .setSecondName("SecondName" + i)
                    .setEmail(i+"@mail.com")
                    .setPassword("password" + i)
                    .setBlocked(i%3 == 1 )
                    .build()
            );
        }
        when(daoClient.getAll()).thenReturn(arr);

        ArrayList<Client> query = clientService.getAll();
        assertEquals(arr.size(), query.size());
        assertEquals(arr.get(4).getClientID(), query.get(4).getClientID());
        assertEquals(arr.get(7).getClientName(), query.get(7).getClientName());

        verify(daoClient).getAll();

    }

    @Test
    public void getClientTest0(){
        ClientService clientService = new ClientService(daoClient);

        when(daoClient.getWhereOne(anyString())).thenReturn(null);

        assertNull(clientService.getClient("",""));
        verify(daoClient).getWhereOne(anyString());

    }

    @Test
    public void getClientTest1(){
        ClientService clientService = new ClientService(daoClient);

        Client obj =  new Client.ClientBuilder().setClientID(1)
                .setClientName("Name")
                .setFirstName("FirstName")
                .setSecondName("SecondName")
                .setEmail("mail@mail.com")
                .setPassword("password")
                .setBlocked(false).build();

        when(daoClient.getWhereOne(" WHERE clientName  = 'Name' AND password = 'password' ")).thenReturn(obj);


        Client res =  clientService.getClient("Name","password");
        assertEquals(res.getClientName(), obj.getClientName());

        assertEquals(res.getPassword(), obj.getPassword());
        verify(daoClient).getWhereOne(" WHERE clientName  = 'Name' AND password = 'password' ");

    }

    @Test(expected =  RuntimeException.class)
    public void getClientTest2(){
        ClientService clientService = new ClientService(daoClient);

        doThrow(new RuntimeException()).when(daoClient).getWhereOne(" WHERE clientName  = 'Name' AND password = 'password' ");

        clientService.getClient("Name","password");

        verify(daoClient).getWhereOne(" WHERE clientName  = 'Name' AND password = 'password' ");

    }


    @Test
    public void blockClientTest0(){
        ClientService clientService = new ClientService(daoClient);

        Client obj =  new Client.ClientBuilder().setClientID(1)
                .setClientName("Name")
                .setFirstName("FirstName")
                .setSecondName("SecondName")
                .setEmail("mail@mail.com")
                .setPassword("password")
                .setBlocked(false).build();

        when(daoClient.getByID(1)).thenReturn(obj);


        clientService.blockClient(1);

        assertEquals(true,obj.getBlocked());

        verify(daoClient).getByID(1);
        verify(daoClient).update(any());

    }

    @Test
    public void unblockClientTest0(){
        ClientService clientService = new ClientService(daoClient);

        Client obj =  new Client.ClientBuilder().setClientID(1)
                .setClientName("Name")
                .setFirstName("FirstName")
                .setSecondName("SecondName")
                .setEmail("mail@mail.com")
                .setPassword("password")
                .setBlocked(true).build();

        when(daoClient.getByID(1)).thenReturn(obj);


        clientService.unblockClient(1);

        assertEquals(false,obj.getBlocked());

        verify(daoClient).getByID(1);
        verify(daoClient).update(any());

    }
}
