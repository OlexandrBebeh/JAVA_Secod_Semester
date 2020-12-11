package kpi.model.services;

import kpi.model.dao.AbstractDAOFactory;
import kpi.model.dao.DAOClient;
import kpi.model.dao.Entities.Client;

import java.util.ArrayList;

public class ClientService {
    private DAOClient daoClient;

    public ClientService(){
        daoClient = (DAOClient) AbstractDAOFactory.getDAO("CLIENTS");
    }

    public Client getClient(UserHolder userHolder){

        Client client =
                daoClient.getWhereOne(" WHERE clientName  = '"
                        + userHolder.getUserName() + "' AND password = '"
                        + userHolder.getPassword() + "' ");

        return client;
    }

    public void blockClient(int id){
        Client client = daoClient.getByID(id);

        if(client==null){
            throw new RuntimeException("Client doesn't exist");
        }

        client.setBlocked(true);

        daoClient.update(client);
    }
    public void unblockClient(int id){
        Client client = daoClient.getByID(id);

        if(client==null){
            throw new RuntimeException("Client doesn't exist");
        }

        client.setBlocked(false);

        daoClient.update(client);
    }
    public void addNewClient(Client client){
        daoClient.insert(client);
    }

    public ArrayList<Client> getAll(){
        return daoClient.getAll();
    }

}
