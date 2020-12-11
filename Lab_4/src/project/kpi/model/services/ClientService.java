package project.kpi.model.services;

import project.kpi.model.dao.AbstractDAOFactory;
import project.kpi.model.dao.DAOClient;
import project.kpi.model.dao.entities.Client;

import java.util.ArrayList;

public class ClientService {


    public Client getClient(String username, String password){
        DAOClient daoClient = (DAOClient) AbstractDAOFactory.getDAO("CLIENTS");
        Client client =
                daoClient.getWhereOne(" WHERE clientName  = '"
                        + username + "' AND password = '"
                        + password + "' ");
        daoClient.close();
        return client;
    }

    public void blockClient(int id){
        DAOClient daoClient = (DAOClient) AbstractDAOFactory.getDAO("CLIENTS");
        Client client = daoClient.getByID(id);

        if(client==null){
            throw new RuntimeException("Client doesn't exist");
        }

        client.setBlocked(true);

        daoClient.update(client);
        daoClient.close();
    }
    public void unblockClient(int id){
        DAOClient daoClient = (DAOClient) AbstractDAOFactory.getDAO("CLIENTS");
        Client client = daoClient.getByID(id);

        if(client==null){
            throw new RuntimeException("Client doesn't exist");
        }

        client.setBlocked(false);

        daoClient.update(client);
        daoClient.close();
    }
    public void addNewClient(Client client){
        DAOClient daoClient = (DAOClient) AbstractDAOFactory.getDAO("CLIENTS");
        daoClient.insert(client);
        daoClient.close();
    }

    public ArrayList<Client> getAll(){
        DAOClient daoClient = (DAOClient) AbstractDAOFactory.getDAO("CLIENTS");
        ArrayList<Client> clients = daoClient.getAll();
        daoClient.close();
        return clients;
    }

}
