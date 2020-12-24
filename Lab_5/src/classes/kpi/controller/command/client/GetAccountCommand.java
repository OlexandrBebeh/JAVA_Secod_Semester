package classes.kpi.controller.command.client;

import classes.kpi.controller.command.Command;
import classes.kpi.model.dao.entities.Account;
import classes.kpi.model.services.AccountService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAccountCommand extends Command {
    public static Logger logger = Logger.getLogger(GetAccountCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        AccountService accountService = new AccountService();

        Account account = null;

        account = accountService.getAccount(" WHERE account_id = " +  Integer.parseInt(req.getParameter("account_id")));
        accountService.close();
        req.getSession().setAttribute("account",account);
        logger.info("User get account " + account.getAccountName());
        return null;
    }
}
