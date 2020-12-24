package classes.kpi.controller.command.client;

import classes.kpi.controller.command.Command;
import classes.kpi.controller.validator.Validator;
import classes.kpi.model.dao.entities.Client;
import classes.kpi.model.services.ClientService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends Command {
    public static Logger logger = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        try{
        ClientService clientService = new ClientService();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Validator.isName(username);
        Client client =clientService.getClient(username,password);
        clientService.close();
        if(client==null){
            req.setAttribute("error","Wrong username or password");
            return null;
        } else {
            if (!client.getBlocked()) {

                logger.info("Succeed enter of user "+ client.getClientName());
                req.getSession().setAttribute("client", client);
                return "Client/Home";
            } else {
                req.setAttribute("error", "This client is blocked!");
                return null;
            }
        }
        }catch (Exception e){
            logger.info("Failed enter.");
            req.setAttribute("error", e.getMessage() );
        }
        return null;
    }
}
