package project.kpi.controller.command.admin;

import project.kpi.controller.command.Command;
import project.kpi.model.services.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockAccountCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        if(req.getParameter("unblock")!=null){
            AccountService accountService = new AccountService();
            int account_id = Integer.parseInt(req.getParameter("unblock"));
            accountService.setUnblocked(account_id,0,true);
        }
        if(req.getParameter("block")!=null){
            AccountService accountService = new AccountService();
            int account_id = Integer.parseInt(req.getParameter("block"));
            accountService.setBlocked(account_id,0,true);
        }
        return null;
    }
}
