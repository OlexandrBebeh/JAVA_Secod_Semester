package classes.kpi.controller.command.admin;

import classes.kpi.controller.command.Command;
import classes.kpi.controller.command.client.GetAccountCommand;
import classes.kpi.model.dao.entities.Account;
import classes.kpi.model.services.AccountService;
import classes.kpi.controller.pagination.Pagination;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class GetAccountsCommand extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        AccountService accountService = new AccountService();
        ArrayList<Account> accounts = accountService.getAll();
        if(req.getParameter("sort")!=null)
            accountService.sort(accounts,Integer.parseInt(req.getParameter("sort")));
        accountService.close();
        Pagination.pagination(req,accounts,"accounts",5);
        return "/jsp/admin/accounts.jsp";
    }
}
