package project.kpi.controller.command.admin;

import project.kpi.controller.command.Command;
import project.kpi.model.services.ClientService;
import project.kpi.model.services.PaginationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetClientsCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        ClientService clientService = new ClientService();
        PaginationService.pagination(req,clientService.getAll(),"clients",5);
        return "/jsp/admin/clients.jsp";
    }
}
