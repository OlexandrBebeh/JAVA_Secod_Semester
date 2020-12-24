package classes.kpi.model.services;

import classes.kpi.model.dao.AbstractDAO;
import classes.kpi.model.dao.AbstractDAOFactory;
import classes.kpi.model.dao.DAOClient;
import classes.kpi.model.dao.entities.Client;

import java.util.ArrayList;

public class ClientService {
    DAOClient daoClient = null;

    public ClientService() {
        daoClient = (DAOClient) AbstractDAOFactory.getDAO("CLIENTS");
    }


    public ClientService(AbstractDAO dao) {
        daoClient = (DAOClient)dao;
    }

    public void close(){
        daoClient.close();
    }

    public Client getClient(String username, String password){

        Client client =
                daoClient.getWhereOne(" WHERE clientName  = '"
                        + username + "' AND password = '"
                        + password + "' ");

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
