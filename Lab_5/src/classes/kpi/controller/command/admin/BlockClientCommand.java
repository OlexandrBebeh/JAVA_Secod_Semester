package classes.kpi.controller.command.admin;

import classes.kpi.controller.command.Command;
import classes.kpi.model.services.ClientService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockClientCommand extends Command {
    public static Logger logger = Logger.getLogger(BlockAccountCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        ClientService clientService = new ClientService();
        try {
            if (req.getParameter("unblock") != null) {
                int client_id = Integer.parseInt(req.getParameter("unblock"));
                clientService.unblockClient(client_id);
            }
            if (req.getParameter("block") != null) {
                int client_id = Integer.parseInt(req.getParameter("block"));
                clientService.blockClient(client_id);

            }
        }catch (RuntimeException e){
            logger.debug(e.getMessage());
        }
        clientService.close();
        return null;
    }
}
