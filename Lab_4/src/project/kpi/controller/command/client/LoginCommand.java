package project.kpi.controller.command.client;

import project.kpi.controller.command.Command;
import project.kpi.model.dao.entities.Client;
import project.kpi.model.services.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        ClientService clientService = new ClientService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Client client =clientService.getClient(username,password);
        if(client==null){
            req.setAttribute("error","Wrong username or password");
            return null;
        } else{
            if(!client.getBlocked()) {
                req.getSession().setAttribute("client", client);
                return "Client/Home";
            }else{
                req.setAttribute("error","This client is blocked!");
                return null;
            }
        }
    }
}
