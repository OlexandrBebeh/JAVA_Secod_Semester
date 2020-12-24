package classes.kpi.controller.command.client;

import classes.kpi.controller.command.Command;
import classes.kpi.model.dao.entities.Client;
import classes.kpi.model.services.AccountService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeCommand extends Command {

    public static Logger logger = Logger.getLogger(HomeCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        AccountService accountService = new AccountService();
        Client client = ((Client)req.getSession().getAttribute("client"));

        if(req.getParameter("sort")!=null)
            req.getSession().setAttribute("accounts",
                    accountService.sort(
                            accountService.getClientsAccount(client.getClientID())
                            ,Integer.parseInt(req.getParameter("sort"))));

        else req.getSession().setAttribute("accounts",
                accountService.getClientsAccount(client.getClientID()));
        accountService.close();

        logger.info("User "+ client.getClientName() + " enter to home page.");
        return "/jsp/home.jsp";
    }
}
