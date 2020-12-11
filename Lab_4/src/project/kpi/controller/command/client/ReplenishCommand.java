package project.kpi.controller.command.client;

import project.kpi.controller.command.Command;
import project.kpi.controller.validator.Validator;
import project.kpi.model.dao.entities.Account;
import project.kpi.model.dao.entities.Client;
import project.kpi.model.services.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplenishCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        AccountService accountService = new AccountService();
        String score = req.getParameter("score");
        try{
            Validator.isDouble(score);
            double s = Double.parseDouble(score);
            accountService.replenishAccount(
                    ((Account)req.getSession().getAttribute("account")).getAccountID(),
                    s,
                    ((Client)req.getSession().getAttribute("client")).getClientID());
            req.setAttribute("error","Replenish succeed!");
        }catch (Exception e){
            req.setAttribute("error","Replenish failure!");
        }
        return null;
    }
}
