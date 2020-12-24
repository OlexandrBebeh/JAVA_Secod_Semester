package classes.kpi.controller.command.admin;

import classes.kpi.controller.command.Command;
import classes.kpi.model.services.AccountService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockAccountCommand extends Command {
    public static Logger logger = Logger.getLogger(BlockAccountCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        AccountService accountService = new AccountService();
        try {
            if (req.getParameter("unblock") != null) {

                int account_id = Integer.parseInt(req.getParameter("unblock"));
                accountService.setUnblocked(account_id, 0, true);
            }
            if (req.getParameter("block") != null) {
                int account_id = Integer.parseInt(req.getParameter("block"));
                accountService.setBlocked(account_id, 0, true);

            }
        }catch (Exception e) {
            logger.debug(e.getMessage());
        }
        accountService.close();
        return null;
    }
}
