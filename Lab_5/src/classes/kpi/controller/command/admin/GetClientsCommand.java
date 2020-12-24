package classes.kpi.controller.command.admin;

import classes.kpi.controller.command.Command;
import classes.kpi.model.services.ClientService;
import classes.kpi.controller.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetClientsCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        ClientService clientService = new ClientService();
        clientService.close();
        Pagination.pagination(req,clientService.getAll(),"clients",5);
        return "/jsp/admin/clients.jsp";
    }
}
