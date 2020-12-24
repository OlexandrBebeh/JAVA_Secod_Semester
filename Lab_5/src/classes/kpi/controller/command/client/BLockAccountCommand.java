package classes.kpi.controller.command.client;

import classes.kpi.controller.command.Command;
import classes.kpi.controller.command.admin.BlockAccountCommand;
import classes.kpi.model.dao.entities.Client;
import classes.kpi.model.services.AccountService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BLockAccountCommand extends Command {
    public static Logger logger = Logger.getLogger(BlockAccountCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        AccountService accountService = new AccountService();
        try {
        if(req.getParameter("block")!=null) {
            int account_id = Integer.parseInt(req.getParameter("block"));
            accountService.setBlocked(account_id, ((Client) req.getSession().getAttribute("client")).getClientID(), false);

        }
        }catch (Exception e){
            logger.info("Try to block blocked account_id.");
            req.setAttribute("error","Account is already blocked!");
        }
        try {
            if (req.getParameter("unblock") != null) {

                int account_id = Integer.parseInt(req.getParameter("unblock"));

                accountService.setUnblocked(account_id, ((Client) req.getSession().getAttribute("client")).getClientID(), false);
            }
        }catch (Exception e){
            logger.info("Try to block blocked account_id.");
            req.setAttribute("error","Account is already in query to unblock!");
        }

        accountService.close();
        return null;
    }
}
