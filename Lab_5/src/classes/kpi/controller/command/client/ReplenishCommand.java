package classes.kpi.controller.command.client;

import org.apache.log4j.Logger;
import classes.kpi.controller.command.Command;
import classes.kpi.controller.validator.Validator;
import classes.kpi.model.dao.entities.Account;
import classes.kpi.model.dao.entities.Client;
import classes.kpi.model.services.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplenishCommand extends Command {

    public static Logger logger = Logger.getLogger(ReplenishCommand.class);

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
            logger.info("Replenish succeed. For score " + score);
        }catch (Exception e){
            logger.info("Replenish fail. For score " + score);
            req.setAttribute("error","Replenish failure!");
        }
        accountService.close();
        return null;
    }
}
