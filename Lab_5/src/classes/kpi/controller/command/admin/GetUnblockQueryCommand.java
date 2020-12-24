package classes.kpi.controller.command.admin;

import classes.kpi.controller.command.Command;
import classes.kpi.model.dao.entities.Account;
import classes.kpi.model.services.AccountService;
import classes.kpi.controller.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class GetUnblockQueryCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        AccountService accountService = new AccountService();
        ArrayList<Account> unblockQuery = accountService.getBlocked();
        if(req.getParameter("sort")!=null)
            accountService.sort(unblockQuery,Integer.parseInt(req.getParameter("sort")));
        accountService.close();
        Pagination.pagination(req,unblockQuery,"unblockQuery",5);
        return "/jsp/admin/unblock-query.jsp";
    }
}
