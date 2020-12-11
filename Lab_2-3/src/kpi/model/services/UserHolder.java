package kpi.model.services;

import kpi.model.dao.Entities.Admin;
import kpi.model.dao.Entities.Client;

public class UserHolder {
    private Users userEnum = Users.NONE;
    private int id;
    private String userName;
    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void userCheck(){
        ClientService clientService = new ClientService();
        Client client = clientService.getClient(this);
        if(client != null) {
            id = client.getClientID();
            userEnum = Users.CLIENT;
            return;
        }
        AdminService adminService = new AdminService();
        Admin admin = adminService.getAdmin(this);

        if(admin != null) {
            id = admin.getAdminID();
            userEnum = Users.ADMIN;
            return;
        }

        userEnum = Users.NONE;
    }

    public void registration(Client client){
        client.setClientName(userName);
        client.setPassword(password);
        ClientService clientService = new ClientService();
        clientService.addNewClient(client);
    }

    public Users getUserEnum() {
        return userEnum;
    }

    public void setUserEnum(Users userEnum) {
        this.userEnum = userEnum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
