package project.kpi.controller.command.client;

import project.kpi.controller.command.Command;
import project.kpi.model.dao.entities.Client;
import project.kpi.model.services.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeCommand extends Command {
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

        return "/jsp/home.jsp";
    }
}
