package project.kpi.controller.command.admin;

import project.kpi.controller.command.Command;
import project.kpi.model.dao.entities.Account;
import project.kpi.model.services.AccountService;
import project.kpi.model.services.PaginationService;

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
        PaginationService.pagination(req,unblockQuery,"unblockQuery",5);
        return "/jsp/admin/unblock-query.jsp";
    }
}
