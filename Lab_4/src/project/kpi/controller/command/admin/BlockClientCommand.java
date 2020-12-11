package project.kpi.controller.command.admin;

import project.kpi.controller.command.Command;
import project.kpi.model.services.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockClientCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        if(req.getParameter("unblock")!=null){
            ClientService clientService = new ClientService();
            int client_id = Integer.parseInt(req.getParameter("unblock"));
            clientService.unblockClient(client_id);
        }
        if(req.getParameter("block")!=null){
            ClientService clientService = new ClientService();
            int client_id = Integer.parseInt(req.getParameter("block"));
            clientService.blockClient(client_id);
        }
        return null;
    }
}
